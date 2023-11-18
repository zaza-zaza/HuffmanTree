import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HuffmanSubmit3 implements Huffman{

    private Node root;
    public String text;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    private Map<Character, Integer> charFreq;
    private final Map<Character, String> huffCodes;

    private final Map<String, Integer> huffCodesAndFreq;

    // huffman coding constructor
    public HuffmanSubmit3() throws IOException {
//        this.text = text;
//        charFreqTable();
        huffCodes = new HashMap<>();
        huffCodesAndFreq = new HashMap<>();
    }
    // creating frequency table
//    private void charFreqTable(){
//        charFreq = new HashMap<>();
//        for (char character : text.toCharArray()){
//            Integer i = charFreq.get(character);
//            charFreq.put(character, i != null ? i + 1 : 1);
//        }
//    }

    // reading text file
    public static String readIn(String file) throws IOException {
        String string = new String(Files.readAllBytes(Paths.get(file)));
        return string;
    }
    @Override
    public void encode(String inputFile, String outputFile, String freqFile) {

        // read input file
        try {
            inputFile = readIn(inputFile);
        }catch(IOException e){
            e.getMessage();
        }

        // char frequency table
        charFreq = new HashMap<>();
        for (char character : inputFile.toCharArray()){
            Integer i = charFreq.get(character);
            charFreq.put(character, i != null ? i + 1 : 1);
        }
        // traversing priority queue and converting characters to bits
        Queue<Node> pq = new PriorityQueue<>();
        charFreq.forEach((character, freq) ->
                pq.add(new LeafNode(character, freq))
        );
        while(pq.size() > 1){
            pq.add(new Node(pq.poll(), pq.poll()));
        }
        generateCodes(root = pq.poll(), "");

        // generating output text file
        try {
            FileWriter fw = new FileWriter(new File(outputFile));
            fw.write(getEncodedText(inputFile));
            fw.close();
        } catch(IOException e){
            e.getMessage();
        }
        printFrequencyTable(freqFile);


    }

    private String getEncodedText(String inputFile){
        StringBuilder sb = new StringBuilder();
        for (char character : inputFile.toCharArray()){
            sb.append(huffCodes.get(character));
        }
        return sb.toString();
    }

    private void generateCodes(Node node, String code){
        BinaryOut bo = new BinaryOut();

        if (node instanceof LeafNode){
            huffCodes.put(((LeafNode) node).getCharacter(), code);
            return;
        }
        generateCodes(node.getLeft(), code.concat("0"));
        generateCodes(node.getRight(), code.concat("1"));

    }
    @Override
    public void decode(String inputFile, String outputFile, String freqFile) {
        // reading encoded input file
        try {
            inputFile = readIn(inputFile);
        }catch(IOException e){
            e.getMessage();
        }

        StringBuilder sb = new StringBuilder();
        Node current = root;
        for(char character : inputFile.toCharArray()){
            current = character == '0' ? current.getLeft() : current.getRight();
            if(current instanceof LeafNode){
                sb.append(((LeafNode) current).getCharacter());
                current = root;
            }
        }
        try{
            FileWriter fw = new FileWriter(new File(outputFile));
            fw.write(sb.toString());
        }catch(IOException e){
            e.getMessage();
            e.printStackTrace();
        }
//        System.out.println(sb.toString());
    }
    // print codes class. Make this output a file
    public void printFrequencyTable(String freqFile){
        ArrayList<String> al = new ArrayList<>();
        // combining codes and frequency into a new map
        charFreq.forEach((character, integer) ->
                huffCodesAndFreq.put(huffCodes.get(character), charFreq.get(character))
                );
        try {
            FileWriter fw = new FileWriter(freqFile);
            for (Map.Entry<String, Integer> entry :
                    huffCodesAndFreq.entrySet()) {
                fw.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void printCodes(){
        charFreq.forEach((character, freq) ->
                System.out.println((freq + ": " + character)));
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
    public static void main(String[] args) throws IOException {
        HuffmanSubmit3 huffman = new HuffmanSubmit3();
        huffman.encode("src/alice30.txt", "alice.enc", "alice_freqFile.txt");
        huffman.decode("alice.enc", "alice_dec.txt", "apple");
    }
}
