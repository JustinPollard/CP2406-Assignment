import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameMenu extends JFrame {

    private GameMenu(){
        super("Start Menu");
        setVisible(true);
        GridLayout gridLayout = new GridLayout(6,1);
        Container container = getContentPane();
        JButton button = new JButton("Play Game");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Client.paint(new String [0]);
                setVisible(false);
            }
        });

        JButton instructionButton = new JButton("Instructions");
        instructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                GameInstructions.main(null);
            }
        });


        container.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT));
        container.add(instructionButton);
        container.add(button);
    }


    public static void main(String[] args) {
        GameMenu mainMenu = new GameMenu();
        mainMenu.setSize(new Dimension(600, 800));
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainMenu.setVisible(true);

    }
}
