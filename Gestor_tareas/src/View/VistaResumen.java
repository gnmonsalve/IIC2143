package View;

import Model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class VistaResumen extends JFrame implements ModificarTareaListener {
	
	private Model model;
	private CalendarioFrame calendario;
	private ArrayList<Proyecto> proyectos;
	private CreadorProyectos creadorProyectos;
	private CreadorTareas creadorTareas;
	private ArrayList<ModificarTareaListener> modificarTareaListeners;
	private JPanel content;
	private ArrayList<Tarea> listaActualTareas;

	public VistaResumen(Model model) {
		this.model = model;
		this.proyectos = model.getProyectos();
		modificarTareaListeners = new ArrayList<>();
		modificarTareaListeners.add(this);
		calendario = new CalendarioFrame(model);
		setSize(500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		placeComponents();
	}

	private void placeComponents() {
		JPanel Horizontal = new JPanel();
		Horizontal.setLayout(new BoxLayout(Horizontal, BoxLayout.X_AXIS));
		Horizontal.setBackground(new Color(35, 35, 35,230));
		content = new JPanel();
		content.setBackground(new Color(20, 35, 20, 230));

		JMenuBar menubar = new JMenuBar();
		JButton btn1 = new JButton("Todas");
		JButton btn2 = new JButton("Ordenar");
		JButton btn3 = new JButton("3 dias");
		JPanel sidebar = new JPanel();

		content.setLayout(new GridLayout(0,1));
		sidebar.setLayout(new GridLayout(0, 1));
		sidebar.setMaximumSize(new Dimension(100, 100000));
		setJMenuBar(menubar);
		setContentPane(Horizontal);

		Horizontal.add(sidebar);
		Horizontal.add(content);
		sidebar.add(btn1);
		sidebar.add(btn2);
		sidebar.add(btn3);

		Font font = new Font("Centhury Gothic",Font.PLAIN,20);
		content.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Vista Resumen", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, font, Color.WHITE));

		/** BOTONES FILTROS / SORT **/
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaActualTareas = getListaCompleta();
				mostrarTareas(listaActualTareas);
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaActualTareas =  getTareasPorFechaFinal();
				mostrarTareas(listaActualTareas);
			}
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaActualTareas = getProximos3dias();
				mostrarTareas(listaActualTareas);
			}
		});
		/** Content **/
		JLabel nombreLabel = new JLabel("VISTA RESUMEN");
		content.add(nombreLabel);
		listaActualTareas = getListaCompleta();
		mostrarTareas(listaActualTareas);

		JButton crearTareaButton = new JButton("Crear T");
		menubar.add(crearTareaButton);
		
		JButton crearProyectoButton = new JButton("Crear P");
		menubar.add(crearProyectoButton);
		
		JButton vistaCalendarioButton = new JButton("Calendario");
		menubar.add(vistaCalendarioButton);

		crearTareaButton.addActionListener(e -> {
            creadorTareas = new CreadorTareas(model);
			for (ModificarTareaListener listener : modificarTareaListeners) {
				creadorTareas.addModificarTareaListener(listener);
			}
			creadorTareas.setVisible(true);
		});

		crearProyectoButton.addActionListener(e -> {
			creadorProyectos = new CreadorProyectos(model);

			creadorProyectos.setVisible(true);
		});

		vistaCalendarioButton.addActionListener(e -> calendario.setVisible(true));
	}

	public void mostrarTareas(ArrayList<Tarea> tareas) {
		content.removeAll();
		for (Tarea t : tareas) {
			TareaPanel TP = new TareaPanel(t);
			TP.mostrarTodo();
			for (ModificarTareaListener listener : modificarTareaListeners) {
				TP.addModificarTareaListener(listener);
			}
			content.add(TP);
		}
		content.updateUI();
	}

	public void setListener(ActionListener listener) {
		calendario.setListener(listener);
	}

	public ArrayList<Tarea> getListaCompleta() {
		ArrayList<Tarea> lista = new ArrayList<>();
		for(int i = 0; i< model.getContador_proyectos(); i++) {
			for (int j = 0; j < proyectos.get(i).getTareas().size(); j++) {
				Tarea t = proyectos.get(i).getTareas().get(j);
				lista.add(t);
			}
		}
		return lista;
	}

	public ArrayList<Tarea> getProximos3dias() {
		ArrayList<Tarea> lista = new ArrayList<>();
		for (int i = 0; i < model.getContador_proyectos(); i++) {
			for (int j = 0; j < proyectos.get(i).getTareas().size(); j++) {
				Tarea t = proyectos.get(i).getTareas().get(j);
				/** devolver solo tareas que vencen en los proximos dias **/
				if (t.getFf().getD() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH) <= 3) {
					lista.add(t);
				}
			}
		}
		return lista;
	}

	public ArrayList<Tarea> getTareasPorFechaFinal() {
		ArrayList<Tarea> lista = new ArrayList<>();
		for (int i = 0; i < model.getContador_proyectos(); i++) {
			for (int j = 0; j < proyectos.get(i).getTareas().size(); j++) {
				Tarea t = proyectos.get(i).getTareas().get(j);
				lista.add(t);
			}
		}
		/** ordenar lista **/
		Collections.sort(lista);
		/** **/
		return lista;
	}

	public void addModificarTareaListener(ModificarTareaListener listener) {
		calendario.addModificarTareaListener(listener);
		for (ModificarTareaListener modificarTareaListener : modificarTareaListeners) {
			if (listener.equals(modificarTareaListener)) {
				return;
			}
		}
		modificarTareaListeners.add(listener);
		mostrarTareas(listaActualTareas);
	}


	@Override
	public void ModificarTarea(ActionEvent e, Tarea tarea, Proyecto proyecto) {
		if (e.getActionCommand().equals("eliminar")) {
			/** eliminar de la lista **/

			/** creamos una copia de la lista (para evitar concurrent modification exception **/
			ArrayList<Tarea> listaNueva = new ArrayList<>();
			for (Tarea t : listaActualTareas) {
				listaNueva.add(t);
			}

			/** sacamos la tarea eliminada de la lista **/
			int n = 0;
			for (Tarea t : listaActualTareas) {
				if (tarea.equals(t)) {
					listaNueva.remove(n);
				}
				n++;
			}

			listaActualTareas = listaNueva;
			/** actualizamos graficamente **/
			calendario.updateUI();
			mostrarTareas(listaNueva);
		}
	}
}
