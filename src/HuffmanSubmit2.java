// Import any package as required
import java.io.*;
import java.util.*;


public class HuffmanSubmit2 implements Huffman {


    HuffmanSubmit2() {

    }

    public void charFreqTable(File file)
            throws IOException{

        String string = "";
//        File file = new File(file);
        Scanner scanner = new Scanner(file);
        string = scanner.nextLine();
        while (scanner.hasNextLine()) {
            string = string + "\n" + scanner.nextLine();
        }
        char[] charArr = string.toCharArray();

        for(char c : charArr){
            System.out.print(c);
        }

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

        // writing the frequency table to text file
        FileWriter fw = new FileWriter("freqTable");
        for(Map.Entry<Character, Integer> e : freq.entrySet()){
            fw.write(e.toString() + "\n");
        }
        fw.close();
    }

    public void encode(String inputFile, String outputFile, String freqFile){
        // constructing huffman tree
        try{
            charFreqTable(new File(inputFile));
        } catch(IOException e){
            System.out.println("FileReader exception");
        }

        Scanner s = new Scanner(freqFile);
        String string = s.nextLine();
        while(s.hasNextLine()){
            System.out.println(string);
        }
        s.close();

        try{
            FileWriter fw = new FileWriter(outputFile);
            fw.write("test");
            fw.close();
        } catch(IOException e){
            System.out.println("FileWriter error" + e.getMessage());
        }

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

//        charFreqTable(new File("src/alice30.txt"));
        huffman.encode("src/testFile2.txt", "test.enc", "src/freqTable");
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
