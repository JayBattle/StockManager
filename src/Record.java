/**
 * @Author: Jay Battle
 * @title: Record.java
 * @Project: Stock Manager
 * @References: My alma mater, Dr.Praveen Madiraju, Dale Joyce & Weems,
 * @Created: 10/27/2015
 * @Description: A stock record class
 */

class Record {
	
	public String trade;
	public int shares;
	public double pricePerShare;  
	  
	  
	public Record(String trade, int shares, double pricePerShare) {
		super();
		this.trade = trade;
		this.shares = shares;
		this.pricePerShare = pricePerShare;
	}

	public String getTrade() {return trade;}

	public void setTrade(String trade) {this.trade = trade;}

	public int getShares() {return shares;}

	public void setShares(int shares) {this.shares = shares;}

	public double getPricePerShare() {return pricePerShare;}

	public void setPricePerShare(double pricePerShare) {this.pricePerShare = pricePerShare;}
	 
	  
}
