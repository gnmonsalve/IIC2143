import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Resumen extends JFrame{
	
	tarea t;
	Resumen(tarea _t){
		t = _t;
		initUI(t);
	}
	
	
	private void initUI(tarea t){
		
		JFrame frame = new JFrame("Vista Resumen");
		frame.setSize(450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel,t);

		frame.setVisible(true);
		
		
	}
	

	private static void placeComponents(JPanel panel, tarea t) {

		panel.setLayout(null);

		JLabel nombreLabel = new JLabel("Nombre | Fecha inicio | Fecha Fin | Estado");
		nombreLabel.setBounds(140, 10, 250, 25);
		panel.add(nombreLabel);

		
		int largo = 1;
		for(int i = 0;i<largo;i++){
			
			
			
		//	JLabel auxLabel = new JLabel(t.nombre + " | " + t.fi + " | " + t.ff + " | " + t.estado);
			//auxLabel.setBounds(140, 40 + i*30, 250, 25);
			//panel.add(auxLabel);
			
		}

		
		
		
		
		//Botones
		JButton crearTareaButton = new JButton("Crear T");
		crearTareaButton.setBounds(10, 10, 100, 25);
		panel.add(crearTareaButton);
		
		JButton crearProyectoButton = new JButton("Crear P");
		crearProyectoButton.setBounds(10, 40, 100, 25);
		panel.add(crearProyectoButton);
		
		JButton vistaCalendarioButton = new JButton("Calendario");
		vistaCalendarioButton.setBounds(10, 70, 100, 25);
		panel.add(vistaCalendarioButton);
		
	
		crearTareaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreadorTareas g1 = new CreadorTareas();
				
			}
		});
		
		crearProyectoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CreadorProyectos p1 = new CreadorProyectos();
			}
		});
		
		
		vistaCalendarioButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("funciona !");
				
			}
		});
		
		
		
		
		
		
		
	
	}



}
