package com.company.prob3;

import java.util.ArrayList;

public class RMITLetterUtility {
    public static void main(String[] args) {
        char[][] l = new char[][] {
                {'A', 'R', 'N', 'U', 'R', 'M', 'U', 'V'},
                {'X', 'L', 'N', 'U', 'T', 'M', 'J', 'C'},
                {'A', 'Q', 'N', 'H', 'I', 'U', 'V', 'H'},
                {'A', 'R', 'O', 'U', 'R', 'G', 'U', 'I'},
                {'B', 'R', 'N', 'L', 'R', 'M', 'U', 'T'}};

        RMITLetterUtility le = new RMITLetterUtility();
        ArrayList<RMITLetter> test = le.scan(l);
        System.out.println(le.canConnect(test.get(2), test.get(3)));
        System.out.println(le.canFindRMIT());
//        System.out.println(Arrays.deepToString(l));
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

    public boolean canConnect(RMITLetter l1, RMITLetter l2) {
        if (l1.getRow() > l2.getRow()) return false;
        if (l1.getCol() > l2.getCol()) return false;
        if ((l2.getCol() - l1.getCol()) + (l2.getRow() - l1.getRow()) > 4 &&
                (l2.getCol() - l1.getCol()) + (l2.getRow() - l1.getRow()) < 0) return false;
        if (l1.getLetter() != 'R' && l1.getLetter() != 'M' && l1.getLetter() != 'I') return false;
        if (l1.getLetter() == 'R' && l2.getLetter() != 'M') return false;
        if (l1.getLetter() == 'M' && l2.getLetter() != 'I') return false;
        if (l1.getLetter() == 'I' && l2.getLetter() != 'T') return false;
        return true;
    }

    public boolean canFindRMIT() {
        for (int i1 = 0; i1 < ls.size(); i1++) {
            if (ls.get(i1).getLetter() != 'R') continue;
            RMITLetter st = ls.get(i1);
            // M
            for (int i2 = 0; i2 < ls.size(); i2++) {
                if (ls.get(i2).getLetter() != 'M') continue;
                RMITLetter sd = ls.get(i2);
                if (!canConnect(st, sd)) continue;
                // I
                for (int i3 = 0; i3 < ls.size(); i3++) {
                    if (ls.get(i3).getLetter() == 'I' && ls.get(i3).getCol() > ls.get(i2).getCol()) {
                        RMITLetter rd = ls.get(i3);
                        if (!canConnect(sd, rd)) continue;

                    // T
                        for (int i4 = 0; i4 < ls.size(); i4++) {
                            if (ls.get(i4).getLetter() != 'T') return false;
                            RMITLetter th = ls.get(i4);
                            if (!canConnect(rd, th)) return false;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
