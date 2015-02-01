package eu.veldsoft.russian.triple;

interface AIBidder {

	public boolean canDoBid(int currentValue);

	public Bid doBid(int currentValue);

}
