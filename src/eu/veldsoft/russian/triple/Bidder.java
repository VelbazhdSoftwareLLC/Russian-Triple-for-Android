package eu.veldsoft.russian.triple;

interface Bidder {

	boolean canDoBid(int currentValue);

	Bid doBid(int currentValue);

	void endBidding();

}
