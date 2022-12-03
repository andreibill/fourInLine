import javax.swing.*;
import java.awt.*;

public class Button extends JButton implements Board {
    static int turnOf = 2;
    Button(int i, int j) {
        setText(" ");
        setName("Button" + createText(i, j));
        setFocusable(false);
        setBackground(Color.lightGray);
        addActionListener(actionEvent -> {
            int realI = move(j);
            if (realI != -1) {
                if (checkWinning(realI, j)) {
                    disableButtons();
                }
            }
        });
    }
    private String createText(int i, int j) {
        String text = String.valueOf((char)('A' + j));
        text += (String.valueOf((char)(i + '1')));
        return text;
    }

    private int move(int j){
        for (int i = 0; i < 6; i++) {
            if (array[i][j].getText().equals(" ")) {
                array[i][j].setText(turnOf == 1 ? "O" : "X");
                turnOf = turnOf == 1 ? 2 : 1;
                return i;
            }
        }
        return -1;
    }

    private boolean checkWinning(int i, int j) {
        return checkOnVertical(i, j) || checkOnHorizontal(i, j)
                || checkOnDiagonalUpDown(i, j) || checkOnDiagonalDownUp(i, j);
    }

    private boolean checkOnVertical(int i, int j){
        int elemUp = i;
        int elemDown = i;
        String move = turnOf == 1 ? "X" : "O";
        boolean check;
        do{
            check = false;
            if(elemUp < 6 && move.equals(array[elemUp][j].getText())) {
                elemUp++;
                check = true;
            }
            if(elemDown >= 0 && move.equals(array[elemDown][j].getText())) {
                elemDown--;
                check = true;
            }
        } while (check);
        if (elemUp - elemDown == 5) {
            elemDown++;
            for (i = elemDown; i < elemUp; i++) {
                array[i][j].setBackground(Color.pink);
            }
            return true;
        }
        return false;
    }

    private boolean checkOnHorizontal(int i, int j){
        int elemLeft = j;
        int elemRight = j;
        boolean check;
        String move = turnOf == 1 ? "X" : "O";
        do{
            check = false;
            if(elemLeft >= 0 && move.equals(array[i][elemLeft].getText())) {
                elemLeft--;
                check = true;
            }
            if(elemRight < 7 && move.equals(array[i][elemRight].getText())) {
                elemRight++;
                check = true;
            }
        } while(check);
        if (elemRight - elemLeft == 5) {
            elemLeft++;
            for (j = elemLeft; j < elemRight; j++) {
                array[i][j].setBackground(Color.pink);
            }
            return true;
        }
        return (elemRight - elemLeft) == 5;
    }

    private boolean checkOnDiagonalUpDown(int i, int j){
        int elemLeftUp = i;
        int elemLeft = j;
        int elemRightDown = i;
        int elemRight = j;
        boolean check;
        String move = turnOf == 1 ? "X" : "O";
        do{
            check = false;
            if (elemLeftUp < 6 && elemLeft >= 0 && move.equals(array[elemLeftUp][elemLeft].getText())) {
                elemLeftUp++;
                elemLeft--;
                check = true;
            }
            if (elemRightDown >= 0 && elemRight < 7 && move.equals(array[elemRightDown][elemRight].getText())) {
                elemRightDown--;
                elemRight++;
                check = true;
            }
        }while(check);
        if (elemRight - elemLeft == 5) {
            elemLeft++;
            for (j = elemLeft; j < elemRight; j++) {
                array[--elemLeftUp][j].setBackground(Color.pink);
            }
            return true;
        }
        return false;
    }

    private boolean checkOnDiagonalDownUp(int i, int j) {
        int elemLeftDown = i;
        int elemLeft = j;
        int elemRightUp = i;
        int elemRight = j;
        boolean check;
        String move = turnOf == 1 ? "X" : "O";
        do{
            check = false;
            if (elemLeftDown >= 0 && elemLeft >= 0 && move.equals(array[elemLeftDown][elemLeft].getText())) {
                elemLeftDown--;
                elemLeft--;
                check = true;
            }
            if (elemRightUp < 7 && elemRight < 7 && move.equals(array[elemRightUp][elemRight].getText())) {
                elemRightUp++;
                elemRight++;
                check = true;
            }
        }while(check);
        if (elemRight - elemLeft == 5) {
            elemLeft++;
            for (j = elemLeft; j < elemRight; j++) {
                array[++elemLeftDown][j].setBackground(Color.pink);
            }
            return true;
        }
        return false;
    }
    private void  disableButtons(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7;  j++)
                array[i][j].setEnabled(false);
        }
    }
}