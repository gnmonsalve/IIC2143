package Controller;
import java.io.FileWriter;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import Model.*;

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
			tarea.setAttribute(new Attribute("color", Integer.toString(t.getColor())));
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
			System.out.print("gg");
			return false;
		}
	}
}