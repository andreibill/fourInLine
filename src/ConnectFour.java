import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame implements Board{
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setTitle("Connect Four");
        makeBoard();
        setLayout(new FlowLayout());
        setVisible(true);

    }

    private void makeBoard(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6,7));
        mainPanel.setSize(700, 700);
        JPanel resetPanel = new JPanel();
        for(int i = 5; i >= 0; i--) {
            for(int j = 0; j < 7; j++) {
                Button button = new Button(i, j);
                array[i][j] = button;
                mainPanel.add(button);
            }
        }
        JButton reset = new JButton("Reset");
        reset.setName("ButtonReset");
        reset.setFocusable(false);
        reset.addActionListener(actionEvent -> {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    array[i][j].setBackground(Color.lightGray);
                    array[i][j].setText(" ");
                }
            }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7;  j++)
                    array[i][j].setEnabled(true);
            }
            Button.turnOf = 2;
        });
        resetPanel.add(reset);
        add(mainPanel);
        add(resetPanel);
    }

}