package com.company.prob3;

import java.util.ArrayList;

public class RMITLetterUtility {
    public static void main(String[] args) {
        char[][] l = new char[][] {{'R', 'M', 'A', 'A', 'A', 'A', 'I', 'T'},
                {'R', 'M', 'A', 'F', 'E', 'S', 'I', 'T'}};
    }

    ArrayList<RMITLetter> ls;
    public ArrayList<RMITLetter> scan(char[][] letters) {
        ArrayList<RMITLetter> res = new ArrayList<>();
        for (int r = 0; r < letters.length; r++) {
            for (int c = 0; c < letters[0].length; c++) {
                char ch = letters[r][c];
                if(ch == 'R' || ch == 'M' || ch == 'I' || ch == 'T') {
                    res.add(new RMITLetter(ch, r, c));
                }
            }
        }
        ls = res;
        return res;
    }
}


class RMITLetter {
    private char letter;
    private int row, col;

    public RMITLetter(char letter, int row, int col) {
        this.letter = letter;
        this.row = row;
        this.col = col;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
