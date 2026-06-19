package userInterface;

import javax.swing.*; 
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class welcomePanel extends JPanel {

    private final Color CARD_COLOR = new Color(28, 36, 48, 220);
    private final Color ACCENT = new Color(31, 219, 147);
    private final Color TEXT_PRIMARY = new Color(235, 235, 235);
    private final Color TEXT_SECONDARY = new Color(140, 160, 180);

    public welcomePanel(mainFrame frame) 
    {
        setOpaque(false); // VERY IMPORTANT
        setLayout(new GridBagLayout());

        // -------------------------
        // Glass Card Container
        // -------------------------
        JPanel glassCard = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(CARD_COLOR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

                // subtle border highlight
                g2.setColor(new Color(255, 255, 255, 25));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 40, 40);

                g2.dispose();
            }
        };

        glassCard.setOpaque(false);
        glassCard.setLayout(new BoxLayout(glassCard, BoxLayout.Y_AXIS));
        glassCard.setBorder(new EmptyBorder(80, 120, 80, 120));

        // -------------------------
        // Title
        // -------------------------
        JLabel brand = new JLabel("SCHEDULIX");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 48));
        brand.setForeground(TEXT_PRIMARY);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);

        // -------------------------
        // Tagline
        // -------------------------
        JLabel tagline = new JLabel("Optimized Academic Scheduling Engine");
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tagline.setForeground(TEXT_SECONDARY);
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        // -------------------------
        // Modern Button
        // -------------------------
        JButton enterBtn = new JButton("ACCESS SYSTEM") {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.dispose();

                super.paintComponent(g);
            }
        };

        enterBtn.setFocusPainted(false);
        enterBtn.setBorderPainted(false);
        enterBtn.setContentAreaFilled(false);
        enterBtn.setPreferredSize(new Dimension(260, 55));
        enterBtn.setMaximumSize(new Dimension(260, 55));
        enterBtn.setBackground(ACCENT);
        enterBtn.setForeground(new Color(15, 20, 25));
        enterBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        enterBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        enterBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hover effect
        enterBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enterBtn.setBackground(new Color(25, 200, 135));
                enterBtn.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                enterBtn.setBackground(ACCENT);
                enterBtn.repaint();
            }
        });

        enterBtn.addActionListener(e -> frame.showScreen("ROLE_SELECTION"));

        // -------------------------
        // Spacing & Assembly
        // -------------------------
        glassCard.add(brand);
        glassCard.add(Box.createRigidArea(new Dimension(0, 20)));
        glassCard.add(tagline);
        glassCard.add(Box.createRigidArea(new Dimension(0, 50)));
        glassCard.add(enterBtn);

        add(glassCard);
    }
}