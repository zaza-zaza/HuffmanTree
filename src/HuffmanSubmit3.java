import java.util.*;
public class HuffmanSubmit3 implements Huffman{

    private Node root;
    private final String text;
    private Map<Character, Integer> charFreq;
    private final Map<Character, String> huffCodes;

    // huffman coding constructor
    public HuffmanSubmit3(String text){
        this.text = text;
        charFreqTable();
        huffCodes = new HashMap<>();
    }
    // creating frequency table
    private void charFreqTable(){
        charFreq = new HashMap<>();
        for (char character : text.toCharArray()){
            Integer i = charFreq.get(character);
            charFreq.put(character, i != null ? i + 1 : 1);
        }
    }

    @Override
    public void encode(String inputFile, String outputFile, String freqFile) {

        Queue<Node> pq = new PriorityQueue<>();
        charFreq.forEach((character, freq) ->
                pq.add(new LeafNode(character, freq))
        );
        while(pq.size() > 1){
            pq.add(new Node(pq.poll(), pq.poll()));
        }
        generateCodes(root = pq.poll(), "");
        System.out.println(getEncodedText());
    }

    private String getEncodedText(){
        StringBuilder sb = new StringBuilder();
        for (char character : text.toCharArray()){
            sb.append(huffCodes.get(character));
        }
        return sb.toString();
    }

    private void generateCodes(Node node, String code){
        if (node instanceof LeafNode){
            huffCodes.put(((LeafNode) node).getCharacter(), code);
            return;
        }
        generateCodes(node.getLeft(), code.concat("0"));
        generateCodes(node.getRight(), code.concat("1"));

    }
    @Override
    public void decode(String inputFile, String outputFile, String freqFile) {
        StringBuilder sb = new StringBuilder();
        Node current = root;
        for(char character : inputFile.toCharArray()){
            current = character == '0' ? current.getLeft() : current.getRight();
            if(current instanceof LeafNode){
                sb.append(((LeafNode) current).getCharacter());
                current = root;
            }
        }
        System.out.println(sb.toString());
    }
    // print codes class. Make this output a file
    public void printCodes(){
        huffCodes.forEach((character, code) ->
                System.out.println((character + ": " + code)));
    }

    // creating a node class
    public class Node implements Comparable<Node> {
        private final int freq;
        private Node left;
        private Node right;

        public Node(Node left, Node right){
            this.freq = left.getFreq() + right.getFreq();
            this.right = right;
            this.left = left;
        }

        public Node(int freq) {
            this.freq = freq;
        }

        public int compareTo(Node node){
            return Integer.compare(freq, node.getFreq());
        }

        public int getFreq() {
            return freq;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    // creating leaf class for priority queue
    public class LeafNode extends Node{
        private final char character;
        public LeafNode(char character, int freq){
            super(freq);
            this.character = character;
        }

        public char getCharacter() {
            return character;
        }
    }
    public static void main(String[] args){
        HuffmanSubmit3 huffman = new HuffmanSubmit3("Peach apple pie");
        huffman.encode("apple", "apple", "apple");
        huffman.printCodes();
        huffman.decode("1111001100110011101011000000111110110100110101", "apple", "apple");
    }
}
