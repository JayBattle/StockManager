/**
 * @Author: Jay Battle
 * @title: DList.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description:  Doubly linked list with nodes of type double linked list Node storing strings
 */

public class DoubleLinkedList {
	
	public static int DebugLevel = 0;
	protected int size;
	protected DoubleLinkedListNode header, trailer;	

	public DoubleLinkedList() { 
		size = 0;
		header = new DoubleLinkedListNode(null, null, null);	
		trailer = new DoubleLinkedListNode(null, header, null);
		header.setNext(trailer);
	}

	public int size() { return size; }
	
	public boolean isEmpty() { return (size == 0); }
	
	public DoubleLinkedListNode getFirst() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		return header.getNext();
	}
	
	public DoubleLinkedListNode getLast() throws IllegalStateException {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		return trailer.getPrev();
	}

	public DoubleLinkedListNode getPrev(DoubleLinkedListNode v) throws IllegalArgumentException {
		if (v == header) throw new IllegalArgumentException("Cannot move back past the header of the list");
		return v.getPrev();
	}

	public DoubleLinkedListNode getNext(DoubleLinkedListNode v) throws IllegalArgumentException {
		if (v == trailer) throw new IllegalArgumentException("Cannot move forward past the trailer of the list");
		return v.getNext();
	}

	public void addBefore(DoubleLinkedListNode v, DoubleLinkedListNode z) throws IllegalArgumentException {
		DoubleLinkedListNode u = getPrev(v);
		z.setPrev(u);
		z.setNext(v);
		v.setPrev(z);
		u.setNext(z);
		size++;
	}
	
	public void addAfter(DoubleLinkedListNode v, DoubleLinkedListNode z) {
		DoubleLinkedListNode w = getNext(v);
		z.setPrev(v);
		z.setNext(w);
		w.setPrev(z);
		v.setNext(z);
		size++;
	}

	public void addFirst(DoubleLinkedListNode v) {
		addAfter(header, v);
	}

	public void addLast(DoubleLinkedListNode v) {
		addBefore(trailer, v);
	}

	public void remove(DoubleLinkedListNode v) {
		DoubleLinkedListNode u = getPrev(v);
		DoubleLinkedListNode w = getNext(v);
		w.setPrev(u);
		u.setNext(w);
		v.setPrev(null);
		v.setNext(null);
		size--;
	}
	
	public boolean hasPrev(DoubleLinkedListNode v) { return v != header; }
	
	public boolean hasNext(DoubleLinkedListNode v) { return v != trailer; }
	
	public String toString() {
		String s = "[";
		DoubleLinkedListNode v = header.getNext();
		while (v != trailer) {
			s += v.getElement();
			v = v.getNext();
			if (v != trailer) s += ",";
		}
		s += "]";
		return s;
	}
	
	public void push(String element) { 
		DoubleLinkedListNode w = new DoubleLinkedListNode(element);
		addLast(w);
	}
	
	public void pop() {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		remove(header.getNext());		
	}
	
	public void reversePop() {
		if (isEmpty()) throw new IllegalStateException("List is empty");
		remove(trailer.getPrev());	
	}
	
	 public void removeDuplicates() {
		DoubleLinkedListNode current = header.getNext();
		DoubleLinkedListNode compare = current.getNext();
		int k = 0;
		for (int i = 0; i < size; i++) {
			if (DebugLevel>1) System.out.println("Loop");
			if (DebugLevel>2)System.out.println(current.getElement());
			if (DebugLevel>3)System.out.println(compare.getElement());
			for (int j = k; j < size; j++) {
				if (DebugLevel>4)System.out.println(current.getElement() + " vs " + compare.getElement());
				if (current.getElement().equals(compare.getElement())) {
					
					if (DebugLevel>5)System.out.println("removed " + compare.getElement());
					remove(compare);
					compare = current.getNext();
					if (DebugLevel>6)System.out.println("New " + compare.getElement());
				}
				compare = compare.getNext();
				if (DebugLevel>7) System.out.println("...");				
			}
			current = current.getNext();
			compare = current.getNext();
			k++;
		}
	}
	 
	 
	public void removeDuplicatesForSortedList() {
		DoubleLinkedListNode current = header.getNext();
		DoubleLinkedListNode compare = current.getNext();
		for (int i = 0; i < size; i++) {
			if (DebugLevel>8) System.out.println(current.getElement() + " vs " + compare.getElement());
			if (current.getElement().equals(compare.getElement())) {	
				if (DebugLevel>9) System.out.println("removed " + compare.getElement());				
				remove(current);
				current = compare;
				compare = current.getNext();		
			} else {
				current = compare;
				compare = current.getNext();
			}
		}
	}

}

