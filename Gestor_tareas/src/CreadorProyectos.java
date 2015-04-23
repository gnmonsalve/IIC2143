import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class CreadorProyectos {
	
	JFrame frame;
	JTextField nombreText;
	Gestor g;
	
	CreadorProyectos(Gestor g){
		initUI();
		this.g = g;
		}
	

	private void initUI(){
		
		frame = new JFrame("Crear Proyecto");
		frame.setSize(300, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);

		//frame.setVisible(true);
					
	}
	

	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(10, 20, 80, 25);
		panel.add(nombreLabel);

		nombreText = new JTextField(20);
		nombreText.setBounds(100, 20, 160, 25);
		nombreText.setText("");
		panel.add(nombreText);

		
		//Botones
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 70, 100, 25);
		panel.add(cancelarButton);
		
		JButton crearButton = new JButton("Crear");
		crearButton.setBounds(170, 70, 100, 25);
		panel.add(crearButton);
		
		String asdf = nombreText.getText();
		
		crearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				proyecto p = new proyecto(g.getId_proyectos(),nombreText.getText(), estado.activo);
				g.agregarProyecto(p);
				
				System.out.println(p.getNombre());
				frame.setVisible(false);
			}
		});
		
		cancelarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				
			}
		});
		
		String n1 = nombreText.getText();
		System.out.println("text: "+n1);
				
		
	
	}


	
}
