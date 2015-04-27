package View;

import Model.Estado;

import javax.swing.*;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransparentButton extends JButton {

    private Color bgColor;

    public TransparentButton(Estado estado) {
        setup();
        this.setText("   ");
        if (estado == Estado.activo) {
            bgColor = Color.GREEN;
        } else if (estado == Estado.pausado) {
            bgColor = Color.YELLOW;
        } else if (estado == Estado.terminado) {
            bgColor = Color.RED;
        }
        setBackground(bgColor);
    }

    public TransparentButton(String text) {
        bgColor = new Color(1,1,1,10);
        this.setText(text);
        setup();
    }

    private void setup() {
        Font font = new Font("Centhury Gothic",Font.PLAIN,16);
        setForeground(Color.white);
        setFont(font);
        setOpaque(false);

        setBackground(bgColor);

        setBorder(new LineBorder(Color.white, 0));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(88, 2, 20, 220));
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(bgColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
    }


}