package userInterface;
import dataBase.DB_Connection; 

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class adminLoginPanel extends JPanel {

    private final Color CARD_COLOR = new Color(28, 36, 48, 220);
    private final Color ACCENT = new Color(31, 219, 147);
    private final Color TEXT_PRIMARY = new Color(235, 235, 235);
    private final Color TEXT_SECONDARY = new Color(140, 160, 180);
    private final Color FIELD_BG = new Color(20, 26, 35);

    private JTextField userField;
    private JPasswordField passField;

    public adminLoginPanel(mainFrame frame) {

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

        loginCard.setPreferredSize(new Dimension(480, 560));
        loginCard.setOpaque(false);
        loginCard.setLayout(new GridBagLayout());
        loginCard.setBorder(new EmptyBorder(50, 50, 50, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // TITLE
        JLabel title = new JLabel("Administrator", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(ACCENT);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        loginCard.add(title, gbc);

        JLabel subtitle = new JLabel("Secure Access Gateway", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(TEXT_SECONDARY);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        loginCard.add(subtitle, gbc);

        // USERNAME
        JLabel userLabel = new JLabel("USERNAME");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userLabel.setForeground(TEXT_PRIMARY);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 8, 0);
        loginCard.add(userLabel, gbc);

        userField = createModernField();
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 25, 0);
        loginCard.add(userField, gbc);

        // PASSWORD
        JLabel passLabel = new JLabel("PASSWORD");
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passLabel.setForeground(TEXT_PRIMARY);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 8, 0);
        loginCard.add(passLabel, gbc);

        passField = new JPasswordField();
        stylePasswordField(passField);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 40, 0);
        loginCard.add(passField, gbc);

        // LOGIN BUTTON
        JButton loginBtn = createModernButton("Authenticate");

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 25, 0);
        loginCard.add(loginBtn, gbc);

        loginBtn.addActionListener(e -> authenticate(frame));

        // BACK BUTTON
        JLabel backBtn = new JLabel("← back to Role Selection", SwingConstants.CENTER);
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backBtn.setForeground(TEXT_SECONDARY);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridy = 7;
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

    private void authenticate(mainFrame frame) {

        String inputUser = userField.getText();
        String inputPass = new String(passField.getPassword());

        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
            return;
        }

        try (Connection conn = DB_Connection.getConnection()) {

            String query = "SELECT * FROM admin_login WHERE username = ? AND password = ?";
            try (PreparedStatement pst = conn.prepareStatement(query))
            {

                pst.setString(1, inputUser.trim());
                pst.setString(2, inputPass.trim());

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        userField.setText("");
                        passField.setText("");
                        frame.showScreen("MENU_PANEL");
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid credentials!");
                    }
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

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

    private void stylePasswordField(JPasswordField field) {
        field.setPreferredSize(new Dimension(0, 45));
        field.setBackground(FIELD_BG);
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(ACCENT);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
    }

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