
/**
 * 
 * @title: DLList.java
 * @Project: Assignment4
 * @Author: Jalen Battle
 * @Version: Created: 10/27/2015, Modified:
 * @Description: 
 * @References: Dr.Praveen Madiraju
 * 
 */

/** Doubly linked list with DLLNodes of type DLLNode storing strings. */
public class DLList {
	protected int size;			// number of elements
	protected DLLNode header, trailer;	// sentinels
	/** Constructor that creates an empty list */
	public DLList() { 
		size = 0;
		header = new DLLNode(null);	// create header
		trailer = new DLLNode(null);	// create trailer
		header.setLink(trailer);	// make header and trailer point to each other
		trailer.setBack(header);	// make header and trailer point to each other
	}
	/** Returns the number of elements in the list */
	public int size() { return size; }
	/** Returns whether the list is empty */
	
	public boolean isEmpty() { return (size == 0); }
	/** Returns the first DLLNode of the list */
	
	public DLLNode getFirst() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		return (DLLNode) header.getLink();
	}
	/** Returns the last DLLNode of the list */
	public DLLNode getLast() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		return trailer.getBack();
	}
	/** Returns the DLLNode before the given DLLNode v. An error occurs if v
	 * is the header */
	public DLLNode getPrev(DLLNode v) throws IllegalArgumentException {
		if (v == header) throw new IllegalArgumentException
		("Cannot move back past the header of the list");
		return v.getBack();
	}
	/** Returns the DLLNode after the given DLLNode v. An error occurs if v
	 * is the trailer */
	public DLLNode getNext(DLLNode v) throws IllegalArgumentException {
		if (v == trailer) throw new IllegalArgumentException
		("Cannot move forward past the trailer of the list");
		return (DLLNode) v.getLink();
	}

	/** Inserts the given DLLNode z before the given DLLNode v. An error
	 * occurs if v is the header */
	public void addBefore(DLLNode v, DLLNode z) throws IllegalArgumentException {
		DLLNode u = getPrev(v);	// may throw an IllegalArgumentException
		z.setBack(u);
		z.setLink(v);
		v.setBack(z);
		u.setLink(z);
		size++;
	}
	/** Inserts the given DLLNode z after the given DLLNode v. An error occurs
	 * if v is the trailer */
	public void addAfter(DLLNode v, DLLNode z) {
		DLLNode w = getNext(v);	// may throw an IllegalArgumentException
		z.setBack(v);
		z.setLink(w);
		w.setBack(z);
		v.setLink(z);
		size++;
	}
	/** Inserts the given DLLNode at the head of the list */
	public void addFirst(DLLNode v) {
		addAfter(header, v);
	}
	/** Inserts the given DLLNode at the tail of the list */
	public void addLast(DLLNode v) {
		addBefore(trailer, v);
	}
	/** Removes the given DLLNode v from the list. An error occurs if v is
	 * the header or trailer */
	public void remove(DLLNode v) {
		DLLNode u = getPrev(v);	// may throw an IllegalArgumentException
		DLLNode w = getNext(v);	// may throw an IllegalArgumentException
		// unlink the DLLNode from the list 
		w.setBack(u);
		u.setLink(w);
		v.setBack(null);
		v.setLink(null);
		size--;
	}
	/** Returns whether a given DLLNode has a previous DLLNode */
	public boolean hasPrev(DLLNode v) { return v != header; }
	/** Returns whether a given DLLNode has a next DLLNode */
	public boolean hasNext(DLLNode v) { return v != trailer; }
	
	public void push(String element) { 
		//DNode w = null;
		//w.setElement(element);
		DLLNode w = new DLLNode(element);
		addLast(w);
	}
	
	public void pop() {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		remove((DLLNode) header.getLink());
		
	}
	
	public void reversePop() {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		remove(trailer.getBack());
		
	}
	
	public void removeDuplicates() {
		//DLLNode w = new DLLNode();
		//DLLNode current = (DLLNode) header.getLink();
		//DLLNode compare = (DLLNode) current.getLink();
		for (int i = 0; i < size-1; i++) {
			DLLNode current = (DLLNode) header.getLink();
			DLLNode compare = (DLLNode) current.getLink();
			for (int j = 0; j < size; j++) {
				if (current == compare) {
					remove((DLLNode)current.getLink());
				}
				compare = (DLLNode) compare.getLink();
				
			}
		}
	}
	
	/** Returns a string representation of the list */
	public String toString() {
		String s = "[";
		DLLNode v = (DLLNode) header.getLink();
		while (v != trailer) {
			s += v.getInfo();
			v = (DLLNode) v.getLink();
			if (v != trailer)
				s += ",";
		}
		s += "]";
		return s;
	}

}

