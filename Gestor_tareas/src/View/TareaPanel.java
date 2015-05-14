package View;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TareaPanel extends JPanel implements ActionListener {

    private TransparentButton nombre;
    private JLabel horafinal;
    private TransparentButton delete;
    private TransparentButton estado;
    private Tarea tarea;
    private Model model;
    private ArrayList<ModificarTareaListener> modificarTareaListeners;

    public TareaPanel(Tarea tarea, Model model) {
        this.model = model;
        this.tarea = tarea;
        modificarTareaListeners = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        estado = new TransparentButton(tarea.getEstado());
        estado.setBorder(new LineBorder(Color.white,1));
        nombre = new TransparentButton(" "+tarea.getNombre());
        delete = new TransparentButton(" X ");
        add(delete);
        add(estado);
        add(nombre);
        setVisible(true);
        setOpaque(false);
        nombre.addActionListener(this);
        nombre.setActionCommand("detalle");
        delete.setActionCommand("eliminar");
        delete.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("detalle")) {
            TareaFrame tF = new TareaFrame(tarea);
            tF.setVisible(true);
        }
        else if (e.getActionCommand().equals("eliminar")) {
            if (modificarTareaListeners != null) {
                for (ModificarTareaListener listener : modificarTareaListeners) {
                    int id = tarea.getId();
                    ActionEvent AE = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "eliminar");
                    listener.ModificarTarea(AE, id);
                }
            }
            else {
                System.out.println("error, no existe ningun modificarTareaListeners subscrito a el evento");
            }
        }
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void addModificarTareaListener(ModificarTareaListener listener) {
        for (ModificarTareaListener listenerInList : modificarTareaListeners) {
            if (listener.equals(listenerInList)) {
                return;
            }
        }
        modificarTareaListeners.add(listener);
    }
    public void addListener(ActionListener listener) {
        estado.addActionListener(listener);
    }

}
