package eu.veldsoft.russian.triple;

class ComputerPlayer extends Player implements AiBidder {

	public ComputerPlayer(String name) {
		super(name);
	}

	@Override
	public Bid doBid(int currentValue) {
		Bid bid = null;

		// TODO Implement better AI.
		if (Util.PRNG.nextDouble() < 0.55) {
			if (currentValue < 100) {
				bid = new Bid(100, this);
			} else {
				bid = new Bid(currentValue + 1, this);
			}
		} else {
			/*
			 * Pass.
			 */
			bid = new Bid(0, this);
		}

		return bid;
	}

	@Override
	public void endBidding() {
		stopBidding();
	}

	@Override
	public boolean canDoBid(int currentValue) {
		return isBidding();
	}

}
