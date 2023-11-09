import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @author Zachary Garson
 * zgarson@u.rochester.edu
 * 10/29/23
 * Lab 6
 */

public class BST<Key extends Comparable<Key>, Value>{
	
	// creating the node class
	private Node root;
	private class Node{
	private Key key;
	private Value val;
	private Node left;
	private Node right;
	private int size;
	
		
		// constructor
		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	// returns the number of nodes in the tree
	public int size() {
		return size(root);
	}
	
	// counts the number of nodes
	private int size(Node node) {
		if(node == null) return 0;
		return node.size;
	}
	
	// checks if the node is null or not
	public boolean contains(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		return get(key) != null;
	}
	
	// gets the specified node and returns it
	public Value get(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		return get(root,key);
	}
	
	private Value get(Node node, Key key){
		if(node == null) return null;
		int comp = key.compareTo(node.key);
		if (comp < 0) {
			return get(node.left, key);
		} else if (comp > 0) {
			return get(node.right, key);
		} else {
			return node.val;
		}
		
	}
	
	// checks if the root has children
	public boolean isEmpty() {
		return root == null;
	}
	
	// returns the height of the tree
	public int height() {
		return height(root);
	}
	
	private int height (Node node) {
		if(node==null) return -1;
		int left = height(node.left);
		int right = height(node.right);
		return 1 + Math.max(left, right);
	}
	
	// assigns a node a specified key 
	public void put(Key key, Value val) {
		if(key == null) {
			throw new IllegalArgumentException("Key cannot be null.");
		}
		root = put(root, key, val);
	}
	
	private Node put(Node node, Key key, Value val) {
		if(node == null) return new Node(key, val, 1);
		int comp = key.compareTo(node.key);
		if(comp < 0) {
			node.left = put(node.left, key, val);
		} else if (comp > 0) {
			node.right = put(node.right, key, val);
		} else {
			// updates the value if it exists
			node.val = val;
		}
		
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	// delete the min node
	public void deleteMin() {
		if(isEmpty()) {
			throw new NoSuchElementException("The tree is empty.");
		}
		root = deleteMin(root);
	}
	
	
	private Node deleteMin(Node node) {
		if(node.left == null) return node.right;
		node.left = deleteMin(node.left);
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	// delete the max node
	public void deleteMax() {
		if(isEmpty()) {
			throw new NoSuchElementException("The tree is empty.");
		}
		root = deleteMax(root);
	}
	
	
	private Node deleteMax(Node node) {
		if(node.right == null) return node.left;
		node.right = deleteMax(node.left);
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public void delete(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("Key cannot be null.");
		}
		
		root = delete(root, key);
	}
	
	// deletes specified nodes
	private Node delete(Node node, Key key) {
		if(node == null) return null;
		int comp = key.compareTo(node.key);
		if(comp < 0) {
			node.left = delete(node.left, key);
		} else if (comp > 0) {
			node.right = delete(node.right, key);
		} else {
			if(node.right == null) return node.left;
			if(node.left == null) return node.right;
			Node temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}
		node.size = 1 + size(node.left) + size (node.right);
		return node;
	}
	
	// returns the min key value
	public Key min() {
		if(isEmpty()){
			throw new NoSuchElementException("Tree is empty.");
		}
		return min(root).key;
	}
	
	private Node min(Node node) {
		if(node.left == null) return node;
		return min(node.left);
	}
	
	// returns the max key value
	public Key max() {
		if(isEmpty()) {
			throw new NoSuchElementException("Tree is empty.");
		}
		return max(root).key;
	}
	
	private Node max(Node node) {
		if(node.right == null) return node;
		return max(node.right);
	}
	
	// iterable methods
	public Iterable<Key> keys(Key key, Key key2) {
	    if (isEmpty()) {
	        return new LinkedList<>(); 
	    }
	    return keys(min(), max());
	}
	
	// prints an array of the order of the nodes
	public Iterable<Key> levelOrder() {
	    Queue<Key> keys = new LinkedList<>();
	    Queue<Node> queue = new LinkedList<>();
	    queue.offer(root);

	    while (!queue.isEmpty()) {
	        Node node = queue.poll();
	        if (node != null) {
	            keys.add(node.key);

	            if (node.left != null) {
	                queue.offer(node.left);
	            }
	            if (node.right != null) {
	                queue.offer(node.right);
	            }
	        }
	    }
	    return keys;
	}
}