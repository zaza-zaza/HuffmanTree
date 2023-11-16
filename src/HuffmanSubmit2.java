// Import any package as required
import java.io.*;
import java.util.*;



public class HuffmanSubmit2 implements Huffman {


    HuffmanSubmit2() {

    }

    public static Map<Character, Integer> charFreqTable(File file)
            throws IOException{

        // reading text file and converting to a character array
        String string = "";
        Scanner scanner = new Scanner(file);
        string = scanner.nextLine();
        while (scanner.hasNextLine()) {
//            string = string + "\n" + scanner.nextLine();
            string = string + scanner.nextLine();

        }
        char[] charArr = string.toCharArray();

        // adding char array to hash map
        int n = charArr.length;
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : charArr) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            } else {
                freq.put(c, 1);
            }
        }
        // writing frequency table to a text file
        FileWriter fw = new FileWriter("freq.txt");

        for(Map.Entry<Character, Integer> entry : freq.entrySet()){
            char c = entry.getKey();
            fw.write(c);
        }
        fw.write("\n");
        for(Map.Entry<Character, Integer> entry : freq.entrySet()){
            int i = entry.getValue();
            fw.write(i + ",");
        }
        fw.close();
//        System.out.println(Arrays.toString(freq.entrySet().toArray()));

        return freq;
    }

    public void encode(String inputFile, String outputFile, String freqFile) {

       Map<Character, Integer> freq = fileHandler(inputFile, outputFile, freqFile);
       Queue<Node> pq = new PriorityQueue<>();
        freq.forEach((character, frequency ) ->
                pq.add(new LeafNode(character, frequency)));
    }

    public void decode(String inputFile, String outputFile, String freqFile){
        // TODO: Your code here
    }

    public static Map<Character, Integer> fileHandler(String inputFile, String outputFile, String freqFile){
        ArrayList<Character> charList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();
        Map<Character, Integer> freq = new HashMap<>();

        // imports text file to frequency table method
        try {
            charFreqTable(new File(inputFile));
        } catch (IOException e) {
            System.out.println("FileReader exception");
        }

        // reading freqFile and transferring freqFile data to array lists
        try (Scanner s = new Scanner(new File("freq.txt"))){
            char[] charArr = s.nextLine().toCharArray();
            // adding characters to character arrayList
            for(char c : charArr){
                charList.add(c);
            }
            // adding integer string to integer array
            String[] intString = s.nextLine().split(",");
            for (String e : intString) {
                intList.add(Integer.parseInt(e));
            }
            s.close();
        } catch (IOException e) {
            e.getMessage();
        }

        // adding strings and characters to hash map to add to nodes

        int n = charList.size();
        for(int i = 0; i < n; i++){
            freq.put(charList.get(i), intList.get(i));
        }

//        System.out.println(Arrays.toString(freq.entrySet().toArray()));
//        System.out.println(Arrays.toString(charList.toArray()));
//        System.out.println(Arrays.toString(intList.toArray()));

        // adding
        try {
            FileWriter fw = new FileWriter(outputFile);
            fw.write("test");
            fw.close();
        } catch (IOException e) {
            System.out.println("FileWriter error" + e.getMessage());
        }
        return freq;
    }
    public static void main(String[] args) throws IOException{
        Huffman  huffman = new HuffmanSubmit2();

//        huffman.encode("ur.jpg", "ur.enc", "freq.txt");
//        huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
        // After decoding, both ur.jpg and ur_dec.jpg should be the same.
        // On linux and mac, you can use `diff' command to check if they are the same.

//        charFreqTable(new File("src/alice30.txt"));
        huffman.encode("src/alice30.txt", "test.enc", "/freq.txt");
    }

    public class Node implements Comparable<Node> {
        private int frequency;
        private Node left;
        private Node right;

        public Node(Node left, Node right){
            this.frequency = left.getFrequency() + right.getFrequency();
            this.left = left;
            this.right = right;
        }



        @Override
        public int compareTo(Node node) {
            return Integer.compare(frequency, node.getFrequency());
        }

        public int getFrequency() {
            return frequency;
        }
        public void setFrequency(int freq) {
            this.frequency = frequency;
        }
    }
    public class LeafNode extends Node{
        private final char character;

        public LeafNode(char character, int freq){
            super(freq);
            this.character = character;

        }


    }
}
