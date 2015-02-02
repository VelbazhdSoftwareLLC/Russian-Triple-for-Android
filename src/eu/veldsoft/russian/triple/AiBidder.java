package eu.veldsoft.russian.triple;

interface AiBidder {

	public boolean canDoBid(int currentValue);

	public Bid doBid(int currentValue);

}
