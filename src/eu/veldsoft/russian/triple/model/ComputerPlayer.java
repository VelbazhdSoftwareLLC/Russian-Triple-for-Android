package eu.veldsoft.russian.triple.model;

import eu.veldsoft.russian.triple.model.Card.Suit;
import eu.veldsoft.russian.triple.model.computer.ComputerBidder;
import eu.veldsoft.russian.triple.model.computer.ComputerContractor;

/**
 * Computer player class.
 * 
 * @author Todor Balabanov
 */
public class ComputerPlayer extends Player implements ComputerBidder,
		ComputerContractor {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Suit trumpSelection() {
		// TODO Implement something stronger than random search.
		return getHand().getCards()
				.get(Util.PRNG.nextInt(getHand().getCards().size())).getSuit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Card[] giveCards() {
		Card gift[] = { null, null };

		/*
		 * Random selection, but two different cards.
		 */
		do {
			gift[0] = getHand().getCards().get(
					Util.PRNG.nextInt(getHand().getCards().size()));
			gift[1] = getHand().getCards().get(
					Util.PRNG.nextInt(getHand().getCards().size()));
		} while (gift[0] == gift[1]);

		return gift;
	}
}
