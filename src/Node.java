
public class Node {

    private Character ch;
    private Node left = null;
    private Node right = null;
    Integer freq;

    public Node(Character ch, Integer right){
       this.ch = ch;
       this.freq = freq;
    }

    // node class constructor
    public Node(Character ch, Integer freq, Node left, Node right){
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }


}

