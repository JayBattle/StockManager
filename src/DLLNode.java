//----------------------------------------------------------------------------
// DLLNode.java              by Dale/Joyce/Weems                     Chapter 7
//
// Implements <T> nodes for a doubly linked list.
//----------------------------------------------------------------------------
/**
 * 
 * @title: DLLNode.java
 * @Project: Assignment4
 * @Author: Jalen Battle
 * @Version: Created: 10/27/2015, Modified:
 * @Description: 
 * @References: Dr.Praveen Madiraju
 * 
 */


//import Record;

public class DLLNode<T> extends LLNode<T> 
{
  private DLLNode<T> back;
  public Record R;
  
  public DLLNode(T info)
  {
    super(info);
    back = null;
  }
  
  public DLLNode(T info, String buyOrSell, int shares, double pricePerShare)
  {
    super(info);
    back = null;
    R = new Record(buyOrSell, shares, pricePerShare);
    //R.setBuyOrSell(buyOrSell);
    //R.setShares(shares);
    //R.setPricePerShare(pricePerShare); 
    
  }
 
  public void setBack(DLLNode<T> back)
  // Sets back link of this DLLNode.
  {
    this.back = back;
  }

  public DLLNode getBack()
  // Returns back link of this DLLNode.
  {
    return back;
  }
  
  public Record getRecord()
  // Returns back link of this DLLNode.
  {
    return R;
  }
  
  /**
	 * @return the buyOrSell
	 */
	public String getBuyOrSell() {
		return R.getBuyOrSell();
	}
	
	/**
	 * @return the shares
	 */
	public int getShares() {
		return R.getShares();
	}
	/**
	 * @return the pricePerShare
	 */
	public double getPricePerShare() {
		return R.getPricePerShare();
	}
  
}
 
 