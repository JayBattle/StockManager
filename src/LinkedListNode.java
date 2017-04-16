/**
 * @Author: Jay Battle
 * @title: LinkedListNode.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: Implements <T> nodes for a Linked List.
 */

public class LinkedListNode<T>{
  private LinkedListNode<T> link;
  private T info;
  
  public LinkedListNode(T info){
    this.info = info;
    link = null;
  }
 
  public void setInfo(T info) {
    this.info = info;
  }

  public T getInfo(){return info;}
 
  public void setLink(LinkedListNode<T> link){ this.link = link;}

  public LinkedListNode<T> getLink() {return link;}
  
}
 
 