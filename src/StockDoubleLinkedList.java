
/**
 * @Author: Jay Battle
 * @title: DLList.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: Doubly linked list with DLLNodes of type DLLNode storing strings
 */

public class StockDoubleLinkedList {
	
	protected int size;
	protected StockDoubleLinkedListNode<String> header, trailer;
	
	public StockDoubleLinkedList() { 
		size = 0;
		header = new StockDoubleLinkedListNode(null);
		trailer = new StockDoubleLinkedListNode(null);
		header.setLink(trailer);
		trailer.setBack(header);
	}
	
	public int size() { return size; }
	
	public boolean isEmpty() { return (size == 0); }
	
	public StockDoubleLinkedListNode<String> getFirst() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		return (StockDoubleLinkedListNode) header.getLink();
	}
	
	public StockDoubleLinkedListNode<String> getLast() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		return trailer.getBack();
	}

	public StockDoubleLinkedListNode<String> getPrev(StockDoubleLinkedListNode<String> v) throws IllegalArgumentException {
		if (v == header) throw new IllegalArgumentException
		("Cannot move back past the header of the list");
		return v.getBack();
	}

	public StockDoubleLinkedListNode<String> getNext(StockDoubleLinkedListNode<String> v) throws IllegalArgumentException {
		if (v == trailer) throw new IllegalArgumentException
		("Cannot move forward past the trailer of the list");
		return (StockDoubleLinkedListNode) v.getLink();
	}

	public void addBefore(StockDoubleLinkedListNode<String> v, StockDoubleLinkedListNode<String> z) throws IllegalArgumentException {
		StockDoubleLinkedListNode u = getPrev(v);
		z.setBack(u);
		z.setLink(v);
		v.setBack(z);
		u.setLink(z);
		size++;
	}

	public void addAfter(StockDoubleLinkedListNode<String> v, StockDoubleLinkedListNode<String> z) {
		StockDoubleLinkedListNode w = getNext(v);
		z.setBack(v);
		z.setLink(w);
		w.setBack(z);
		v.setLink(z);
		size++;
	}

	public void addFirst(StockDoubleLinkedListNode<String> v) {
		addAfter(header, v);
	}

	public void addLast(StockDoubleLinkedListNode<String> v) {
		addBefore(trailer, v);
	}

	public void remove(StockDoubleLinkedListNode<String> v) {
		StockDoubleLinkedListNode u = getPrev(v);
		StockDoubleLinkedListNode w = getNext(v);
		w.setBack(u);
		u.setLink(w);
		v.setBack(null);
		v.setLink(null);
		size--;
	}
	
	public boolean hasPrev(StockDoubleLinkedListNode v) { return v != header; }

	public boolean hasNext(StockDoubleLinkedListNode v) { return v != trailer; }
	
	public void push(String element) { 
		StockDoubleLinkedListNode w = new StockDoubleLinkedListNode(element);
		addLast(w);
	}
	
	public void pop() {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		remove((StockDoubleLinkedListNode<String>) header.getLink());
		
	}
	
	public void reversePop() {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		remove(trailer.getBack());
		
	}
	
	public void removeDuplicates() {
		for (int i = 0; i < size-1; i++) {
			StockDoubleLinkedListNode current = (StockDoubleLinkedListNode) header.getLink();
			StockDoubleLinkedListNode compare = (StockDoubleLinkedListNode) current.getLink();
			for (int j = 0; j < size; j++) {
				if (current == compare) {
					remove((StockDoubleLinkedListNode<String>)current.getLink());
				}
				compare = (StockDoubleLinkedListNode) compare.getLink();
				
			}
		}
	}
	
	public String toString() {
		String s = "[";
		StockDoubleLinkedListNode v = (StockDoubleLinkedListNode) header.getLink();
		while (v != trailer) {
			s += v.getInfo();
			v = (StockDoubleLinkedListNode) v.getLink();
			if (v != trailer) s += ",";
		}
		s += "]";
		return s;
	}

}

