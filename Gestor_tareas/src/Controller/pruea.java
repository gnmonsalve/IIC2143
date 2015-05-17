package Controller;


import Model.*;

public class pruea {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Xml xml = new Xml();
		Proyecto p = new Proyecto(1, "miSuperProyecto", Estado.activo);
		System.out.print(p.getEstado().toString());
		Tarea t1 = new Tarea(0,"t1", new Fecha(01, 01, 01), new Fecha(02, 01, 01), new Hora(12, 23), new Hora(12,50), "hola, soy la t1", 234, new Contexto("c1"));
		Tarea t2 = new Tarea(1,"t2", new Fecha(01, 02, 01), new Fecha(02, 02, 01), new Hora(12, 23), new Hora(12,50), "hola, soy la t2", 234, new Contexto("c2"));
		p.getTareas().add(t1);
		p.getTareas().add(t2);		
		String path = "/home/gerinelda/workspace/" + p.getNombre()+".xml";
		boolean a = xml.crear(p,path);
	}

}