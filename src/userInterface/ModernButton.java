package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModernButton extends JButton {

    public ModernButton(String text) {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(Color.BLACK);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(Theme.ACCENT_HOVER);
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                setBackground(Theme.ACCENT);
                repaint();
            }
        });

        setBackground(Theme.ACCENT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }
}