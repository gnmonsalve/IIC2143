import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import Controller.*;
import View.*;
import Model.*;

import javax.swing.*;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ValidationException;


public class Application {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					runApp();
				} catch (IOException | ParserException | ValidationException | URISyntaxException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	private static void runApp() throws IOException, ParserException, ValidationException, URISyntaxException, ParseException {
		Model model = new Model();
		CalendarioICS c = new CalendarioICS();
		Xml xml = new Xml();
		xml.importarSesion(model);;
		View view = new View(model);
		Controller controller = new Controller(view, model);
	}

	private static void agregarDatosPrueba(Model model) {
		Proyecto p1 = new Proyecto(model.getId_proyectos(),"miscelaneo",Estado.activo);
		Contexto contexto = new Contexto("miscelaneo");
		model.agregarContexto(contexto);
		model.agregarProyecto(p1);

		/** DATOS DE EJEMPLO PARA EL COMIENZO */

		/**
		Tarea t1 = new Tarea(1,"i1 sistemas operativos",new Fecha(2,4,2015), new Fecha(4,4,2015), new Hora(), new Hora(12,0,0),
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
				, 0, new Contexto("Contexto"));
		Tarea t2 = new Tarea(1,"examen calculo 9",new Fecha(2,4,2015), new Fecha(3,3,2015), new Hora(), new Hora(12),"lim 2+2 = 4. e = mc..", 0, new Contexto("Contexto"));
		Tarea t3 = new Tarea(1,"terminar tesis",new Fecha(2,4,2015), new Fecha(6,4,2015), new Hora(), new Hora(12,20,3),"Terminar la bla bla bla", 0, new Contexto("Contexto"));
		 **/


		/** INGENIERIA DE SOFTWARE **/
		Proyecto p2 = new Proyecto(model.getId_proyectos(),"Ingenieria de Software",Estado.activo);
		Contexto c1 = new Contexto("Universidad");
		Tarea t5 = new Tarea(1,"Sprint 2",new Fecha(2,4,2015), new Fecha(18,5,2015), new Hora(), new Hora(23,59,59),"Terminar features originales", 0, c1);
		t5.setEstado(Estado.terminado);
		Tarea t6 = new Tarea(1,"Interrogacion 1",new Fecha(1,1,2015), new Fecha(15,4,2015), new Hora(), new Hora(18,30), "Ingenieria de Software", 0, c1);
		t6.setEstado(Estado.terminado);
		Tarea t7 = new Tarea(1,"Interrogacion 2" ,new Fecha(1,1,2015), new Fecha(8,5,2015), new Hora(), new Hora(18,30),"Ingenieria de Software", 0, c1);
		t7.setEstado(Estado.terminado);
		Tarea t8 = new Tarea(1,"Interrogacion 3",new Fecha(1,1,2015), new Fecha(4,6,2015), new Hora(), new Hora(18,30),"Ingenieria de Software", 0,c1);
		Tarea t9 = new Tarea(1,"Examen",new Fecha(1,1,2015), new Fecha(26,6,2015), new Hora(12,0,0), new Hora(8,30),"Ingenieria de Software", 0,c1);

		model.agregarProyecto(p2);
		model.agregarTarea(t5, p2.getId());
		model.agregarTarea(t6, p2.getId());
		model.agregarTarea(t7, p2.getId());
		model.agregarTarea(t8, p2.getId());
		model.agregarTarea(t9, p2.getId());

		/** SISTEMAS OPERATIVOS **/
		Proyecto p3 = new Proyecto(model.getId_proyectos(),"Sistemas Operativos",Estado.activo);
		Contexto c2 = new Contexto("Universidad");
		Tarea t10 = new Tarea(1,"Interrogacion 1",new Fecha(2,4,2015), new Fecha(8,4,2015), new Hora(), new Hora(18,30),"Sistemas Operativos", 0, c2);
		Tarea t11 = new Tarea(1,"Interrogacion 2",new Fecha(2,4,2015), new Fecha(4,5,2015), new Hora(), new Hora(18,30),"Sistemas Operativos", 0, c2);
		Tarea t12 = new Tarea(1,"Interrogacion 3",new Fecha(2,4,2015), new Fecha(29,5,2015), new Hora(), new Hora(18,30),"Sistemas Operativos", 0, c2);
		Tarea t13 = new Tarea(1,"Examen",new Fecha(2,4,2015), new Fecha(22,6,2015), new Hora(), new Hora(8,30),"Sistemas Operativos", 0, c2);
		t10.setEstado(Estado.terminado);
		t11.setEstado(Estado.terminado);

		model.agregarProyecto(p3);
		model.agregarContexto(c2);
		model.agregarTarea(t10, p3.getId());
		model.agregarTarea(t11, p3.getId());
		model.agregarTarea(t12, p3.getId());
		model.agregarTarea(t13, p3.getId());



	}
}
