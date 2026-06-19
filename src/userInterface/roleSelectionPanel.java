package userInterface;

import javax.swing.*; 
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class roleSelectionPanel extends JPanel {

    private final Color CARD_COLOR = new Color(28, 36, 48, 220);
    private final Color CARD_HOVER = new Color(35, 45, 60, 235);
    private final Color ACCENT = new Color(31, 219, 147);
    private final Color TEXT_PRIMARY = new Color(235, 235, 235);
    private final Color TEXT_SECONDARY = new Color(140, 160, 180);

    public roleSelectionPanel(mainFrame frame) {

        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        // -------------------------
        // TITLE
        // -------------------------
        JLabel header = new JLabel("Identify Your Role");
        header.setFont(new Font("Segoe UI", Font.BOLD, 34));
        header.setForeground(TEXT_PRIMARY);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0);
        add(header, gbc);

        // -------------------------
        // CARD CONTAINER
        // -------------------------
        JPanel cardContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        cardContainer.setOpaque(false);

        cardContainer.add(createRoleCard("Administrator", "Full Engine Access", frame, "ADMIN_LOGIN"));
        cardContainer.add(createRoleCard("Faculty", "View Workloads", frame, "FACULTY_LOGIN"));
        cardContainer.add(createRoleCard("Student", "View Timetables", frame, "STUDENT_LOGIN"));

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 60, 0);
        add(cardContainer, gbc);

        // -------------------------
        // BACK BUTTON
        // -------------------------
        JLabel backBtn = new JLabel("← back to home");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backBtn.setForeground(TEXT_SECONDARY);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //hover effect
        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                backBtn.setForeground(ACCENT);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                backBtn.setForeground(TEXT_SECONDARY);
            }

            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.showScreen("WELCOME");
            }
        });

        gbc.gridy = 2;
        add(backBtn, gbc);
    }
    // ---------------------------------------------------
    // ROLE CARD (Centered Content)
    // ---------------------------------------------------
    private JPanel createRoleCard(String title, String desc, mainFrame frame, String screenName) {

        JPanel card = new JPanel() {

            private boolean hovered = false;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(hovered ? CARD_HOVER : CARD_COLOR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);

                g2.setColor(new Color(255, 255, 255, 25));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 35, 35);

                g2.dispose();
            }

            {
                setCursor(new Cursor(Cursor.HAND_CURSOR));

                addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        hovered = true;
                        repaint();
                    }

                    public void mouseExited(java.awt.event.MouseEvent e) {
                        hovered = false;
                        repaint();
                    }

                    public void mouseClicked(java.awt.event.MouseEvent e) 
                    {
                        frame.showScreen(screenName);
                    }
                });
            }
        };

        card.setPreferredSize(new Dimension(280, 330));
        card.setOpaque(false);
        card.setLayout(new GridBagLayout()); // <-- IMPORTANT CHANGE
        card.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Center content inside card
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(ACCENT);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDesc = new JLabel(desc);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDesc.setForeground(TEXT_SECONDARY);
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(lblTitle);
        content.add(Box.createRigidArea(new Dimension(0, 15)));
        content.add(lblDesc);

        card.add(content); // GridBagLayout centers automatically

        return card;
    }
}