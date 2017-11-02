import javax.swing.*;

public class GameInstructions extends JFrame {
static JFrame frame;

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(frame,
                "Use the WASD keys to move \nUse the Space Bar to toggle your Jet wall" +
                        "\nUse the Left Shift key to speed up\nUse the 'Z' key to slow down",
                "Game Instructions",
                JOptionPane.PLAIN_MESSAGE);
    }
}
