import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

public class SplashScreen {

    private static Font functionFont = new Font("Arial", Font.PLAIN, 12);
    private static Font updateFont = new Font("Arial", Font.BOLD, 22);

    public static void main(String[] args) {
        createSplashScreen();
    }

    public static void createSplashScreen() {
        JFrame frame = new JFrame("SplashScreen");

        // Create labels
        JLabel label = new JLabel("UPDATES TO HEAT: ");
        label.setBounds(10, 10, 300, 50);
        label.setFont(updateFont);

        JLabel update1 = new JLabel("- You can select font sizes from SMALL, MEDIUM, LARGE");
        update1.setBounds(20, 40, 700, 50); // Adjusted width to accommodate the text
        update1.setFont(functionFont);

        JLabel update2 = new JLabel("- Text to speech by pressing x");
        update2.setBounds(20, 80, 500, 50); // Adjusted Y position and width
        update2.setFont(functionFont);

        JLabel update3 = new JLabel("- Icons have been updated for visual clarity");
        update3.setBounds(20, 120, 500, 50); // Adjusted Y position and width
        update3.setFont(functionFont);

        JLabel update4 = new JLabel("- Working on changing the colors");
        update4.setBounds(20, 160, 500, 50); // Adjusted Y position and width
        update4.setFont(functionFont);

        // Add components to the frame
        frame.add(label);
        frame.add(update1);
        frame.add(update2);
        frame.add(update3);
        frame.add(update4);

        // Set frame properties
        frame.setSize(600, 300); // Adjusted the height to accommodate the text
        frame.setLayout(null);

        // Close operation - Not HEAT, just the JFrame is closed
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the frame location to the right side of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - frame.getWidth() - 70; // Adjust the value (20) as needed
        int y = 400; // Adjust the value as needed
        frame.setLocation(x, y);
      
        // Make the frame visible
        frame.setVisible(true);
    }

}
