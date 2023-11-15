// Import any package as required
import java.io.*;
import java.util.*;


public class HuffmanSubmit2 implements Huffman {


    HuffmanSubmit2() {

    }


    public void charFreqTable(File file)
            throws IOException{
        Scanner s = new Scanner(file);
        String input = s.next();

        // convert string to char array
        int n = input.length();

        int i = 0;
        while(s.hasNext()){
            System.out.print(input.charAt(i++));
        }
        Map<Character, Integer> freq = new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            if (freq.containsKey(input.charAt(i))) {
//                freq.put(input.charAt(i), freq.get(input.charAt(i)) + 1);
//            } else {
//                freq.put(input.charAt(i), 1);
//            }
//        }

        // writing the frequency table to text file
        FileWriter fw = new FileWriter("freqTable");
        for(Map.Entry<Character, Integer> e : freq.entrySet()){
            fw.write(e.toString() + "\n");
        }
        fw.close();
    }

    // Feel free to add more methods and variables as required.

    public void encode(String inputFile, String outputFile, String freqFile){
        // constructing huffman tree
        try{
            charFreqTable(new File("/src/alice30.txt"));
        } catch(IOException e){
            System.out.println("IOException");
        }

        // creating file to read
        Scanner s = new Scanner("/src/freqTable");
        String freqIn = s.nextLine();
    }


    public void decode(String inputFile, String outputFile, String freqFile){
        // TODO: Your code here
    }




    public static void main(String[] args) throws IOException{
        Huffman  huffman = new HuffmanSubmit2();
        huffman.encode("ur.jpg", "ur.enc", "freq.txt");
        huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
        // After decoding, both ur.jpg and ur_dec.jpg should be the same.
        // On linux and mac, you can use `diff' command to check if they are the same.

    }

    public static class Node {

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
}
