
/**
 * @Author: Jay Battle
 * @title: DLLNode.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: Doubly linked list with DLLNodes of type DLLNode storing strings
 */

public class StockDoubleLinkedListNode<T> extends LinkedListNode<T> {

  private StockDoubleLinkedListNode<T> back;
  public Record record;
  
  public StockDoubleLinkedListNode(T info) {
    super(info);
    back = null;
  }
  
  public StockDoubleLinkedListNode(T info, String trade, int shares, double pricePerShare){
    super(info);
    back = null;
    record = new Record(trade, shares, pricePerShare);
    record.setTrade(trade);
    record.setShares(shares);
    record.setPricePerShare(pricePerShare);     
  }
 
  public void setBack(StockDoubleLinkedListNode<T> back){this.back = back;}

  public StockDoubleLinkedListNode<T> getBack(){return back;}
  
  public Record getRecord(){return record;}
  
  public String getBuyOrSell() {return record.getTrade();}
	
  public int getShares() {return record.getShares();}

  public double getPricePerShare() {return record.getPricePerShare();}
  
}
 
 