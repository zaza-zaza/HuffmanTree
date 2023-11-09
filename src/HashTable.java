import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Zachary Garson
 * zgarson@u.rochester.edu
 * 10/22/23
 * Lab 5
 */

public class HashTable<Key, Value> extends UR_HashTable<Key, Value> implements Iterable<Key>{
	private static final int INIT_CAPACITY = 5;
	
	protected int n; // size of the data set
	
	protected int m ; // size of the hash table
	
	protected Key[] keys;
	
	Value[] vals;
	
	int inserts, collisions;
	
	// constructors
	public HashTable() {
		this(INIT_CAPACITY);
	}
	public HashTable(int cap) { 
		m = cap;
		n = 0;
		keys = (Key[]) new Object[m];
		vals = (Value[]) new Object[m];
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
				
	}
	public void resize(int capacity) {
		HashTable<Key, Value> tmp = new HashTable<>(capacity);
		for(int i = 0; i < m; i++) {
			if(keys[i] != null) {
				tmp.put(keys[i], vals[i]);
			}
		}
		this.keys = tmp.keys;
		this.vals = tmp.vals;
		this.m = tmp.m;
	}
	@Override
	public void put(Key key, Value val) {
		if(key == null) throw new IllegalArgumentException("Key cannot be null.");
		if(val == null) {
			delete(key);
			return;
		}
		if(size() == m) {
			System.out.println("Table full");
			return;
		}
		if (n >= m / 2) resize (2 * m);
		
		int i = hash(key);
		while(keys[i] != null) {
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
			
			i = (i + 1) % m;
			collisions++;
		}
		keys[i] = key;
		vals[i] = val;
		n++;
		inserts++;
	}

	public Value get(Key key) {
		if(key == null) throw new IllegalArgumentException("Key cannot be null.");
		for(int i = hash(key); keys[i] != null; i = (i +1) % m) {
			if(keys[i].equals(key)) {
				// returns the 
				return vals[i];
			}
		}	
		return null;
	}

	
	public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        if (!contains(key)) return;
        //linear probing
        int i = hash(key);
        while(!key.equals(keys[i])) {
        	i = (i + 1) % m;
        }
		
        keys[i] = null;
        vals[i] = null;
        
        i = (i + 1) % m;
        //linear probing
        while(keys[i] != null) {
        	Key keyRehash = keys[i];
        	Value valRehash = vals[i];
        	keys[i] = null;
        	vals[i] = null;
        	n--;
        	put(keyRehash, valRehash);
        	i = (i + 1) % m;
        }
        n--;
        
        if(n > 0 && n <= m / 8) resize(m / 2);
	}

	
	public int size() { return n; }

	
	public boolean isEmpty() { return size() == 0; }

	
	public boolean contains(Key key) { return get(key) != null; }

	
	public Iterable<Key> keys() {
		List<Key> listKey = new ArrayList<>();
		for(Key key : keys) {
			if(key != null) {
				listKey.add(key);
			}
		}
		return listKey;
	}

	@Override
	public Iterator<Key> iterator() {
		return new Iterator<Key>() {
			private int current = 0;
			
			@Override
			public boolean hasNext() {
				while(current < m && keys[current] == null) {
					current++;
				}
				return current < m;
			}
			@Override
			public Key next() {
				if(!hasNext()) {
					throw new NoSuchElementException();
				}
				return keys[current++];
			}
		};
	}
}
