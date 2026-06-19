package userInterface;

import javax.swing.*; 
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class facultyLoginPanel extends JPanel {

    private final Color CARD_COLOR = new Color(28, 36, 48, 220);
    private final Color ACCENT = new Color(31, 219, 147);
    private final Color TEXT_PRIMARY = new Color(235, 235, 235);
    private final Color TEXT_SECONDARY = new Color(140, 160, 180);
    private final Color FIELD_BG = new Color(20, 26, 35);

    private JTextField userField;

    public facultyLoginPanel(mainFrame frame) {

        setOpaque(false);
        setLayout(new GridBagLayout());

        JPanel loginCard = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(CARD_COLOR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

                g2.setColor(new Color(255, 255, 255, 25));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 40, 40);

                g2.dispose();
            }
        };

        loginCard.setPreferredSize(new Dimension(450, 520));
        loginCard.setOpaque(false);
        loginCard.setLayout(new GridBagLayout());
        loginCard.setBorder(new EmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // =========================
        // TITLE
        // =========================
        JLabel title = new JLabel("Faculty Portal", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(ACCENT);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        loginCard.add(title, gbc);

        JLabel subtitle = new JLabel("Access Your Teaching Schedule", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(TEXT_SECONDARY);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        loginCard.add(subtitle, gbc);

        // =========================
        // EMPLOYEE ID
        // =========================
        JLabel userLabel = new JLabel("EMPLOYEE ID");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userLabel.setForeground(TEXT_PRIMARY);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 8, 0);
        loginCard.add(userLabel, gbc);

        userField = createModernField();

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 35, 0);
        loginCard.add(userField, gbc);

        // =========================
        // BUTTON
        // =========================
        JButton loginBtn = createModernButton("Access Schedule");

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 25, 0);
        loginCard.add(loginBtn, gbc);

        // =========================
        // BACK BUTTON
        // =========================
        JLabel backBtn = new JLabel("← Back to Roles", SwingConstants.CENTER);
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backBtn.setForeground(TEXT_SECONDARY);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 0, 0);
        loginCard.add(backBtn, gbc);

        backBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                backBtn.setForeground(ACCENT);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                backBtn.setForeground(TEXT_SECONDARY);
            }

            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.showScreen("ROLE_SELECTION");
            }
        });

        add(loginCard);
    }

    // =========================
    // MODERN TEXT FIELD
    // =========================
    private JTextField createModernField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(0, 45));
        field.setBackground(FIELD_BG);
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        return field;
    }

    // =========================
    // MODERN BUTTON
    // =========================
    private JButton createModernButton(String text) {

        JButton button = new JButton(text) {

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

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.setBackground(ACCENT);
        button.setForeground(new Color(15, 20, 25));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(0, 50));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(25, 200, 135));
                button.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(ACCENT);
                button.repaint();
            }
        });

        return button;
    }
}