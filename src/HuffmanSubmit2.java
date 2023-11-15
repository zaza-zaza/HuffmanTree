// Import any package as required
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class HuffmanSubmit2 implements Huffman {


    HuffmanSubmit2() {

    }


    public static Map<Character, Integer> charFreqTable(String input) {
        // convert string to char array
        int n = input.length();
        Map<Character, Integer> freq = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (freq.containsKey(input.charAt(i))) {
                freq.put(input.charAt(i), freq.get(input.charAt(i)) + 1);
            } else {
                freq.put(input.charAt(i), 1);
            }
        }

        return freq;
    }




    // Feel free to add more methods and variables as required.

    public void encode(String inputFile, String outputFile, String freqFile){

    }


    public void decode(String inputFile, String outputFile, String freqFile){
        // TODO: Your code here
    }




    public static void main(String[] args) {
        Huffman  huffman = new HuffmanSubmit2();
        huffman.encode("ur.jpg", "ur.enc", "freq.txt");
        huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
        // After decoding, both ur.jpg and ur_dec.jpg should be the same.
        // On linux and mac, you can use `diff' command to check if they are the same.

        System.out.print(charFreqTable("APPLES AND BANANAS"));
    }



}
