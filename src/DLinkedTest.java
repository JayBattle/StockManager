public class DLinkedTest {

	public static void main(String[] args) {
		
		DNode BOS = new DNode("Boat");
		DNode ATL = new DNode("Car");
		DNode AMD = new DNode("Plane");
		DList DList = new DList();
		DList.addFirst(BOS);
		DList.addFirst(ATL);
		DList.addFirst(AMD);
		System.out.println(DList);
		
		DList.pop();
		System.out.println(DList);
		
		DList.push("train");
		System.out.println(DList);
		
		DList.push("train");
		System.out.println(DList);
		
		DList.push("Heli");
		System.out.println(DList);
		
		DList.removeDuplicates();
		System.out.println(DList);
		
		DList.push("train");
		System.out.println(DList);
		
		DList.removeDuplicates();
		System.out.println(DList);
		
		DList.push("Heli");
		System.out.println(DList);
		
		DList.push("Heli");
		System.out.println(DList);
		
		DList.removeDuplicatesForSortedList();
		System.out.println(DList);
		
		
		
		
	}
}