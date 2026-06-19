package userInterface;
import javax.swing.*; 
import java.awt.*;
import dataBase.DB_init;


public class mainFrame extends JFrame
{

    private CardLayout cardLayout;
    private JPanel container;
    private Image backgroundImage;

    public mainFrame()
    {

        // -------------------------------
        // Load Background Image
        // -------------------------------
        backgroundImage = new ImageIcon(
                getClass().getResource("background.jpeg")
        ).getImage();

        // -------------------------------
        // Window Configuration
        // -------------------------------
        setTitle("SCHEDULIX");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // -------------------------------
        // Custom Background Panel
        // -------------------------------
        JPanel background = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                // Draw Background Image
                g2.drawImage(backgroundImage, 0, 0,
                        getWidth(), getHeight(), this);

                // Dark Overlay for readability
                g2.setColor(new Color(0, 0, 0, 120));
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.dispose();
            }
        };

        background.setLayout(new BorderLayout());
        setContentPane(background);

        // -------------------------------
        // Card Layout Container
        // -------------------------------
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);
        container.setOpaque(true); // IMPORTANT for glass cards

        // -------------------------------
        // Register Panels
        // -------------------------------
        container.add(new welcomePanel(this), "WELCOME");
        container.add(new roleSelectionPanel(this), "ROLE_SELECTION");
        container.add(new adminLoginPanel(this), "ADMIN_LOGIN");
        container.add(new facultyLoginPanel(this), "FACULTY_LOGIN");
        container.add(new studentLoginPanel(this), "STUDENT_LOGIN");
        container.add(new admin_menuPanel(this), "MENU_PANEL");

        background.add(container, BorderLayout.CENTER);

        showScreen("WELCOME");
    }

    // -------------------------------
    // Navigation Controller
    // -------------------------------
    public void showScreen(String name) {
        cardLayout.show(container, name);
        container.revalidate();
        container.repaint();
    }

    public static void main(String[] args) {

        try 
        {
            DB_init.init();
        } catch (Exception e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }

        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new mainFrame().setVisible(true);
        });
    }
}