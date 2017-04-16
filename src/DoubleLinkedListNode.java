/**
 * @Author: Jay Battle
 * @title: DNode.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: Node of a doubly linked list of strings
 */


public class DoubleLinkedListNode {
	
	protected String element;
	protected DoubleLinkedListNode next, prev;
	
	public DoubleLinkedListNode(String e, DoubleLinkedListNode p, DoubleLinkedListNode n) {
		element = e;
		prev = p;
		next = n;
	}
	
	public DoubleLinkedListNode(String e) {this(e,null,null);}

	public String getElement() { return element; }

	public DoubleLinkedListNode getPrev() { return prev; }

	public DoubleLinkedListNode getNext() { return next; }

	public void setElement(String newElem) { element = newElem; }

	public void setPrev(DoubleLinkedListNode newPrev) { prev = newPrev; }

	public void setNext(DoubleLinkedListNode newNext) { next = newNext; }
}