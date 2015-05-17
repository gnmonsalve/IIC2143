package View;

import Model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreadorProyectos extends JFrame {
	
	private JTextField nombreText;
	private Model model;
	private ArrayList<ModificarTareaListener> modificarTareaListeners;

	public CreadorProyectos(Model model){
		this.model = model;
		modificarTareaListeners = new ArrayList<>();
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		add(panel);
		placeComponents(panel);
		setVisible(true);
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

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(10, 70, 100, 25);
		panel.add(cancelarButton);
		
		JButton crearButton = new JButton("Crear");
		crearButton.setBounds(170, 70, 100, 25);
		panel.add(crearButton);
		
		crearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nombreText.getText().isEmpty()) {
					System.out.println("creando nuevo proyecto");
					Proyecto p = new Proyecto(model.getId_proyectos(), nombreText.getText(), Estado.activo);
					for (ModificarTareaListener listener : modificarTareaListeners) {
						listener.ModificarTarea(e, new Tarea(-1), p);
					}
					setVisible(false);
				}
			}
		});
		crearButton.setActionCommand("agregarProyecto");
		
		cancelarButton.addActionListener(e -> setVisible(false));
		
		String n1 = nombreText.getText();
	}

	public void addModificarTareaListener(ModificarTareaListener listener) {
		for (ModificarTareaListener modificarTareaListener : modificarTareaListeners) {
			if (modificarTareaListener.equals(listener)) {
				return;
			}
		}
		modificarTareaListeners.add(listener);
	}

	
}
