// Import any package as required
import java.util.*;

public class HuffmanSubmit implements Huffman {



  HuffmanSubmit(){

  }

  public static void  huffmanTree(String txt){
      if(txt == null || txt.length()== 0){
          return;
      }
      // sort characters by frequency
      Map<Character, Integer> freq = new HashMap<>();

      // loop string to stores characters
      for (char c:  txt.toCharArray()){

          // returns a default value if null character is null
          freq.put(c, freq.getOrDefault(c, 0)+1);
      }

      // create priority queue to store nodes
      PriorityQueue<HuffmanSubmit2.Node> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

      // iterate through the hash map
      for(var entry: freq.entrySet()){

          // creating leaf node and adding it to the queue
          pq.add(new HuffmanSubmit2.Node(entry.getKey(), entry.getValue()));
      }

    }





	// Feel free to add more methods and variables as required.
 
	public void encode(String inputFile, String outputFile, String freqFile){

   }


   public void decode(String inputFile, String outputFile, String freqFile){
		// TODO: Your code here
   }




   public static void main(String[] args) {
      Huffman  huffman = new HuffmanSubmit();
		huffman.encode("ur.jpg", "ur.enc", "freq.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freq.txt");
		// After decoding, both ur.jpg and ur_dec.jpg should be the same. 
		// On linux and mac, you can use `diff' command to check if they are the same. 
   }

}
