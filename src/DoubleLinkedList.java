

import java.util.ListIterator;
import java.util.NoSuchElementException;

		public class DoubleLinkedList<Item> implements Iterable<Item> {
		    private int N;        // number of elements on list
		    private DLLNode pre;     // sentinel before first item
		    private DLLNode post;    // sentinel after last item

		    public DoubleLinkedList() {
		        pre  = new DLLNode();
		        post = new DLLNode();
		        pre.next = post;
		        post.prev = pre;
		    }

		    // linked list DLLNode helper data type
		    private class DLLNode {
		        private Item item;
		        private DLLNode next;
		        private DLLNode prev;
		    }

		    public boolean isEmpty()    { return N == 0; }
		    public int size()           { return N;      }

		    // add the item to the list
		    public void add(Item item) {
		        DLLNode last = post.prev;
		        DLLNode x = new DLLNode();
		        x.item = item;
		        x.next = post;
		        x.prev = last;
		        post.prev = x;
		        last.next = x;
		        N++;
		    }

		    public ListIterator<Item> iterator()  { return new DoubleLinkedListIterator(); }

		    // assumes no calls to DoubleLinkedList.add() during iteration
		    private class DoubleLinkedListIterator implements ListIterator<Item> {
		        private DLLNode current      = pre.next;  // the DLLNode that is returned by next()
		        private DLLNode lastAccessed = null;      // the last DLLNode to be returned by prev() or next()
		                                               // reset to null upon intervening remove() or add()
		        private int index = 0;

		        public boolean hasNext()      { return index < N; }
		        public boolean hasPrevious()  { return index > 0; }
		        public int previousIndex()    { return index - 1; }
		        public int nextIndex()        { return index;     }

		        public Item next() {
		            if (!hasNext()) throw new NoSuchElementException();
		            lastAccessed = current;
		            Item item = current.item;
		            current = current.next; 
		            index++;
		            return item;
		        }

		        public Item previous() {
		            if (!hasPrevious()) throw new NoSuchElementException();
		            current = current.prev;
		            index--;
		            lastAccessed = current;
		            return current.item;
		        }

		        // replace the item of the element that was last accessed by next() or previous()
		        // condition: no calls to remove() or add() after last call to next() or previous()
		        public void set(Item item) {
		            if (lastAccessed == null) throw new IllegalStateException();
		            lastAccessed.item = item;
		        }

		        // remove the element that was last accessed by next() or previous()
		        // condition: no calls to remove() or add() after last call to next() or previous()
		        public void remove() { 
		            if (lastAccessed == null) throw new IllegalStateException();
		            DLLNode x = lastAccessed.prev;
		            DLLNode y = lastAccessed.next;
		            x.next = y;
		            y.prev = x;
		            N--;
		            if (current == lastAccessed)
		                current = y;
		            else
		                index--;
		            lastAccessed = null;
		        }

		        // add element to list 
		        public void add(Item item) {
		            DLLNode x = current.prev;
		            DLLNode y = new DLLNode();
		            DLLNode z = current;
		            y.item = item;
		            x.next = y;
		            y.next = z;
		            z.prev = y;
		            y.prev = x;
		            N++;
		            index++;
		            lastAccessed = null;
		        }

		    }

		    public String toString() {
		        StringBuilder s = new StringBuilder();
		        for (Item item : this)
		            s.append(item + " ");
		        return s.toString();
		    }
		}

