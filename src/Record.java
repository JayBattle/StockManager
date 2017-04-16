
class Record
{
	public String buyOrSell;
	  public int shares;
	  public double pricePerShare;  
	  
	  
	/**
	 * @param buyOrSell
	 * @param shares
	 * @param pricePerShare
	 */
	public Record(String buyOrSell, int shares, double pricePerShare) {
		super();
		this.buyOrSell = buyOrSell;
		this.shares = shares;
		this.pricePerShare = pricePerShare;
	}
	/**
	 * @return the buyOrSell
	 */
	public String getBuyOrSell() {
		return buyOrSell;
	}
	/**
	 * @param buyOrSell the buyOrSell to set
	 */
	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	/**
	 * @return the shares
	 */
	public int getShares() {
		return shares;
	}
	/**
	 * @param shares the shares to set
	 */
	public void setShares(int shares) {
		this.shares = shares;
	}
	/**
	 * @return the pricePerShare
	 */
	public double getPricePerShare() {
		return pricePerShare;
	}
	/**
	 * @param pricePerShare the pricePerShare to set
	 */
	public void setPricePerShare(double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}

	 
	  
}
