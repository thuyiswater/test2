package com.company.prob1;

public class VariantCollection {
    public static void main(String[] args) {
        VariantCollection v = new VariantCollection();
        v.addVariant(new CovidVariant("Alpha", "210201A"));
        v.addVariant(new CovidVariant("Delta", "210311D"));
        v.addVariant(new CovidVariant("Beta", "210311A"));
        v.addVariant(new CovidVariant("Omicron", "211120D"));
        v.print(v.root);
        System.out.println(v.search("210311A").name); // return the Beta variant
        System.out.println(v.previous("211120D").name);
    }

    Node root;
    public void addVariant(CovidVariant v) {
        Node n = new Node(v);
        if (root == null) {
            root = n;
            return;
        }

        Node temp = root;
        while (true) {
            if (temp.value.isGreater(v)) {
                if (temp.left == null) {
                    temp.left = n;
                    break;
                } else temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = n;
                    break;
                } else temp = temp.right;
            }
        }
    }

    public CovidVariant search(String code) {
        Node temp = root;
        CovidVariant v = new CovidVariant("abc", code);

        while (true) {
            if (temp.value.code.equals(code)) return temp.value;
            if (temp.value.isGreater(v)) {
                if (temp.left == null) return null;
                else temp = temp.left;
            } else {
                if (temp.right == null) return null;
                else temp = temp.right;
            }
        }
    }

    public CovidVariant previous(String code) {
        Node temp = root;
        CovidVariant v = new CovidVariant("Alpha", code);
        boolean found;
        Node[] visit = new Node[100];
        int start = 0;

        while (true) {
            visit[start] = temp;
            start++;
            if (temp.value.code.equals(code)) {
                found = true;
                break;
            }
            if (temp.value.isGreater(v)) {
                if (temp.left == null) {
                    return null;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    return null;
                }
                temp = temp.right;
            }
        }
        if (found) {
            if (temp.left != null) {
                temp = temp.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                return temp.value;
            } else {
                start--;
                while (start - 1 >= 0) {
                    if (visit[start - 1].right == visit[start]) {
                        return visit[start - 1].value;
                    }
                    start--;
                }
                return null;
            }
        }
        return null;
    }

    private void print(Node n) {
        if (n == null) return;
        print(n.left);
        System.out.println(n.value.name + " " + n.value.code);
        print(n.right);
    }
}
class CovidVariant {
    String code;
    String name;

    public CovidVariant(String name, String code) {
        this.code = code;
        this.name = name;
    }

    boolean isGreater(CovidVariant v) {
        int i1 = Integer.parseInt(code.substring(0, 6));
        int i2 = Integer.parseInt(v.code.substring(0, 6));
        char c1 = code.charAt(6);
        char c2 = v.code.charAt(6);

        if (i1 != i2) {
            return i1 > i2;
        }
        return c1 > c2;
    }
}

class Node {
    CovidVariant value;
    Node right, left;

    public Node(CovidVariant value) {
        this.value = value;
        this.right = this.left = null;
    }
}
