public class Main {
    public static void main(String[] args) {

        BST<Integer, String> bst = new BST<>();
        bst.put(1, "apple");
        System.out.println(bst.get(1));

    }
}