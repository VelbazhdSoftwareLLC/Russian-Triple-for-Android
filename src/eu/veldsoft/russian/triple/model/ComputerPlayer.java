package eu.veldsoft.russian.triple.model;

import eu.veldsoft.russian.triple.model.ai.ComputerBidder;

/**
 * Computer player class.
 * 
 * @author Todor Balabanov
 */
public class ComputerPlayer extends Player implements ComputerBidder {
	/**
	 * Constructor with parameters.
	 * 
	 * @param name
	 *            Player's name.
	 */
	public ComputerPlayer(String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endBidding() {
		stopBidding();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canDoBid(int currentValue) {
		return isBidding();
	}

}
