/**
 * @Author: Jay Battle
 * @title: DLinkedTest.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: tests for a doubly linked list of strings
 */

public class DoubleLinkedListTest {
	
	public static int DebugLevel = 0;

	public static void main(String[] args) {
		
		DoubleLinkedListNode Boat = new DoubleLinkedListNode("Boat");
		DoubleLinkedListNode Car = new DoubleLinkedListNode("Car");
		DoubleLinkedListNode Plane = new DoubleLinkedListNode("Plane");
		DoubleLinkedList transportList = new DoubleLinkedList();
		
		transportList.addFirst(Boat);
		transportList.addFirst(Car);
		transportList.addFirst(Plane);
		
		if (DebugLevel>1) System.out.println(transportList);		
		transportList.pop();
		if (DebugLevel>1) System.out.println(transportList);		
		transportList.push("train");
		if (DebugLevel>2) System.out.println(transportList);		
		transportList.push("train");
		if (DebugLevel>3) System.out.println(transportList);		
		transportList.push("Heli");
		if (DebugLevel>4) System.out.println(transportList);		
		transportList.removeDuplicates();
		if (DebugLevel>5) System.out.println(transportList);	
		transportList.push("train");
		if (DebugLevel>6) System.out.println(transportList);
		transportList.removeDuplicates();
		if (DebugLevel>7) System.out.println(transportList);
		transportList.push("Heli");
		if (DebugLevel>8) System.out.println(transportList);
		transportList.push("Heli");
		if (DebugLevel>9) System.out.println(transportList);
		transportList.removeDuplicatesForSortedList();
		System.out.println(transportList);		
	}
}