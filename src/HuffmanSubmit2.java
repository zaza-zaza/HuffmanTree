// Import any package as required
import java.util.*;
public class HuffmanSubmit2 implements Huffman {



  HuffmanSubmit2(){

  }


  public static int charFreqTable(String input){
      Map<Character, Integer> freq = new HashMap<>();
      int count = 0;
      for (int i = 0; i < input.toCharArray().length; i++){
          count++;
      }

      return count;
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

       System.out.print(charFreqTable("appleadjkfhnal'hfkjahfg;kjahnf;kgjafk;ghafg"));
  }


}
