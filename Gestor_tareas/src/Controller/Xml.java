package Controller;
import java.io.FileWriter;

import View.VentanaError;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Model.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.File;


public class Xml {
	
	Element Proyecto = new Element("Proyecto");
	
	public boolean crear(Proyecto p, String ruta){

		Element proyecto = new Element("proyecto");
		Document documento = new Document(proyecto);
		proyecto.setAttribute(new Attribute("id",Integer.toString(p.getId())));
		proyecto.setAttribute(new Attribute("nombre", p.getNombre()));
		proyecto.setAttribute(new Attribute("estado", p.getEstado().toString()));
		for (int i = 0; i < p.getTareas().size(); i++) {
			Element tarea = new Element("tarea");
			Tarea t = p.getTareas().get(i);
			tarea.setAttribute(new Attribute("id",Integer.toString(t.getId())));
			tarea.setAttribute(new Attribute("nombre", t.getNombre()));
			tarea.setAttribute(new Attribute("fi", t.getFi().toString()));
			tarea.setAttribute(new Attribute("ff", t.getFf().toString()));
			tarea.setAttribute(new Attribute("hi", t.getHi().toString()));
			tarea.setAttribute(new Attribute("hf", t.getHf().toString()));
			tarea.setAttribute(new Attribute("descripcion", t.getDescripcion()));
			tarea.setAttribute(new Attribute("estado", t.getEstado().toString()));
			tarea.setAttribute(new Attribute("contexto", t.getContexto().getNombre()));
			documento.getRootElement().addContent(tarea);	
		}
		
		try{

			FileWriter fw = new FileWriter(ruta);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(documento, fw);
			fw.close();
			
			System.out.print("archivo creado en "+ruta);
			return true;	
		}
		catch(Exception e)
		{
			VentanaError error = new VentanaError("Importacion fallida");
			return false;
		}
	}

	public void leer(String ruta, Model model)
	{
		Proyecto p;

		try{
	
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document documento = dBuilder.parse(ruta);
			org.w3c.dom.Element raiz = documento.getDocumentElement();
			String id = raiz.getAttribute("id");
			String nombre = raiz.getAttribute("nombre");
			String estado = raiz.getAttribute("estado");
			p = new Proyecto(Integer.parseInt(id), nombre, Estado.valueOf(estado));
			model.agregarProyecto(p);
			//System.out.println("id: "+id+", nombre: "+nombre+", estado: "+estado);
			NodeList tareas = raiz.getElementsByTagName("tarea");
			for (int i = 0; i < tareas.getLength(); i++) {
				//System.out.println("creando tarea en model");
				Node nodo = tareas.item(i);
				NamedNodeMap nnm = nodo.getAttributes();
				String nombre_tarea = nnm.getNamedItem("nombre").getNodeValue();
				String id_tarea = nnm.getNamedItem("id").getNodeValue();
				String fi = nnm.getNamedItem("fi").getNodeValue();
				String ff = nnm.getNamedItem("ff").getNodeValue();
				String hi = nnm.getNamedItem("hi").getNodeValue();
				String hf = nnm.getNamedItem("hf").getNodeValue();
				String descripcion = nnm.getNamedItem("descripcion").getNodeValue();
				String estado_tarea = nnm.getNamedItem("estado").getNodeValue();
				String color = nnm.getNamedItem("color").getNodeValue();
				String contexto = nnm.getNamedItem("contexto").getNodeValue();
				Tarea t = new Tarea(Integer.parseInt(id_tarea), nombre_tarea, new Fecha(fi), new Fecha(ff),
						new Hora(hi), new Hora(hf), descripcion, new Contexto(contexto));
				t.setEstado(Estado.valueOf(estado_tarea));
				model.agregarTarea(t, new Integer(id));
			}


			return;
		}
		catch(Exception e){
			VentanaError error = new VentanaError("Importacion fallida");
			return;
		}
	}
}
