
public interface UR_Heap<T extends Comparable<T>> {
 
	public void insert(T item);
	private void bubbleUp() {};
	public boolean isEmpty();
	public int size();
	public Comparable deleteMin();
	private void bubbleDown(int index, T[] array, int size) {};
	public void printHeap();
	public void heapify();

}
