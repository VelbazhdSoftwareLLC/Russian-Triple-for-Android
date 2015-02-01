package eu.veldsoft.russian.triple;

class ComputerPlayer extends Player implements AIBidder {

	public ComputerPlayer(String name) {
		super(name);
	}

	@Override
	public boolean canDoBid(int currentValue) {
		// TODO
		return false;
	}

	@Override
	public Bid doBid(int currentValue) {
		Bid bid = null;
		
		//TODO 
		if(Util.PRNG.nextDouble() < 0.95) {
			bid = new Bid(currentValue+1, this);
		} else {
			bid = new Bid(0, this);
		}
		
		return bid;
	}

}
