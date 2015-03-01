package eu.veldsoft.russian.triple;

interface Bidder {

	public boolean canDoBid(int currentValue);

	public Bid doBid(int currentValue);
	
	public void endBidding();

}
