package userInterface;

import javax.swing.*;  
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class admin_menuPanel extends JPanel {

    private final Color SIDEBAR_BG = new Color(22, 28, 38);
    private final Color CARD_BG = new Color(28, 36, 48, 220);
    private final Color ACCENT = new Color(31, 219, 147);
    private final Color TEXT_PRIMARY = new Color(235, 235, 235);
    private final Color TEXT_SECONDARY = new Color(140, 160, 180);
    private final Color DANGER = new Color(255, 69, 58);

    private JPanel dynamicContentArea;
    private JButton activeButton = null;

    public admin_menuPanel(mainFrame frame) {

        setOpaque(false);
        setLayout(new BorderLayout());

        // ==============================
        // SIDEBAR
        // ==============================
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(260, 0));
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setLayout(new BorderLayout());
        sidebar.setBorder(new EmptyBorder(40, 20, 40, 20));

        // Header
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("SCHEDULIX");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(TEXT_PRIMARY);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Admin Control Hub");
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        sub.setForeground(TEXT_SECONDARY);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(logo);
        header.add(Box.createRigidArea(new Dimension(0, 5)));
        header.add(sub);
        header.add(Box.createRigidArea(new Dimension(0, 40)));

        sidebar.add(header, BorderLayout.NORTH);

        // ==============================
        // Navigation Buttons
        // ==============================
        JPanel navPanel = new JPanel();
        navPanel.setOpaque(false);
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));

        navPanel.add(createNavButton("Dashboard", e -> updateView("Admin Overview")));
        navPanel.add(createNavButton("Faculty", e -> updateView("Faculty Management")));
        navPanel.add(createNavButton("Courses", e -> updateView("Course Schedules")));
        navPanel.add(createNavButton("Generate Schedule", e -> updateView("Timetable Engine")));

        sidebar.add(navPanel, BorderLayout.CENTER);

        // ==============================
        // Logout Button
        // ==============================
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        logoutBtn.setForeground(DANGER);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                logoutBtn.setForeground(new Color(255, 100, 90));
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                logoutBtn.setForeground(DANGER);
            }
        });

        logoutBtn.addActionListener(e -> frame.showScreen("ROLE_SELECTION"));
        sidebar.add(logoutBtn, BorderLayout.SOUTH);

        // ==============================
        // MAIN CONTENT AREA
        // ==============================
        dynamicContentArea = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(CARD_BG);
                g2.fillRoundRect(40, 40, getWidth() - 80, getHeight() - 80, 40, 40);

                g2.dispose();
            }
        };

        dynamicContentArea.setOpaque(false);
        dynamicContentArea.setLayout(new GridBagLayout());

        updateView("Select a module to begin");

        add(sidebar, BorderLayout.WEST);
        add(dynamicContentArea, BorderLayout.CENTER);
    }

    // ======================================
    // Update Content View
    // ======================================
    private void updateView(String text) {
        dynamicContentArea.removeAll();

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 28));
        label.setForeground(TEXT_SECONDARY);

        dynamicContentArea.add(label);
        dynamicContentArea.revalidate();
        dynamicContentArea.repaint();
    }

    // ======================================
    // Navigation Button Creator
    // ======================================
    private JButton createNavButton(String text, java.awt.event.ActionListener action) {

        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(TEXT_PRIMARY);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 15, 10, 10));
        btn.setOpaque(false);

        btn.addActionListener(e -> {
            setActiveButton(btn);
            action.actionPerformed(e);
        });

        btn.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (btn != activeButton) {
                    btn.setForeground(ACCENT);
                    btn.setOpaque(true);
                    btn.setBackground(new Color(31, 219, 147, 30));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (btn != activeButton) {
                    btn.setForeground(TEXT_PRIMARY);
                    btn.setOpaque(false);
                    btn.setBackground(null);
                }
            }
        });

        return btn;
    }

    // ======================================
    // Active Button Highlight
    // ======================================
    private void setActiveButton(JButton btn) {

        if (activeButton != null) {
            activeButton.setForeground(TEXT_PRIMARY);
            activeButton.setOpaque(false);
            activeButton.setBackground(null);
        }

        activeButton = btn;
        activeButton.setForeground(ACCENT);
        activeButton.setOpaque(true);
        activeButton.setBackground(new Color(31, 219, 147, 40));
    }
}