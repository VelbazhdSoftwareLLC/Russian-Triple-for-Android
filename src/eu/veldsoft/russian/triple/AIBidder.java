package eu.veldsoft.russian.triple;

public interface AIBidder {

	public boolean canDoBid(int currentValue);

	public Bid doBid(int currentValue);

}
