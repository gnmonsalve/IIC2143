package View;
import Model.*;
import javafx.scene.control.CheckBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TareaRapidaFrame extends JFrame implements ActionListener {
    /** abrir nueva ventana **/
    JPanel content;
    JTextField textField;
    ArrayList<ModificarTareaListener> modificarTareaListeners;
    JComboBox<Proyecto> listaProyectos;
    Model model;


    public TareaRapidaFrame(Model model) {
        this.model = model;
        content = new JPanel();
        content.setOpaque(false);
        add(content);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        modificarTareaListeners = new ArrayList<>();
        /** agregar botones y textos **/
        JLabel titulo = new JLabel("Agregar Tarea");
        int fontSize = 18;
        titulo.setFont(new Font(titulo.getFont().getName(), titulo.getFont().getStyle(), fontSize));
        content.add(titulo);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel XLayout1 = new JPanel();
        JLabel nombre = new JLabel("Nombre: ");
        textField = new JTextField(16);
        XLayout1.add(nombre);
        XLayout1.add(textField);

        JPanel XLayout2 = new JPanel();
        JLabel proyecto = new JLabel("Proyecto: ");
        listaProyectos = new JComboBox<>();
        ArrayList<Proyecto> arrayProyectos = new ArrayList<>();
        if (this.model.getProyectos() != null) {
            arrayProyectos = this.model.getProyectos();
        }
        for (Proyecto p : arrayProyectos) {
            listaProyectos.addItem(p);
        }

        XLayout2.add(proyecto);
        XLayout2.add(listaProyectos);

        JPanel XLayout3 = new JPanel();
        XLayout3.add(btnCancelar);
        XLayout3.add(btnAceptar);

        content.add(XLayout1);
        content.add(XLayout2);
        content.add(XLayout3);

        setSize(300, 200);
        setVisible(true);

        btnAceptar.addActionListener(this);
        btnCancelar.addActionListener(this);

    }

    public void addModificarTareaListener(ModificarTareaListener listener) {
        modificarTareaListeners.add(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Aceptar")) {
            for ( ModificarTareaListener listener : modificarTareaListeners) {
                ActionEvent AE = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "agregar");
                String nombre = textField.getText();
                listener.ModificarTarea(AE, new Tarea(0, nombre), model.getProyecto(listaProyectos.getSelectedIndex()));
            }

        }
        else if (e.getActionCommand().equals("Cancelar")) {
            textField.setText("");
            setVisible(false);
        }
    }
}
