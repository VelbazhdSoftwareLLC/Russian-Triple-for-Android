package eu.veldsoft.russian.triple;

import java.util.Vector;

/**
 * Each round there is a different bidding. Create bidding object each time.
 * 
 * @author Todor Balabanov
 */
class Bidding {
	/**
	 * Keep track of the players from the board.
	 */
	private Player players[] = null;

	private int currentBidderIndex = -1;

	// TODO Create BidHistory class.
	private Vector<Bid> bidHistory = new Vector<Bid>();

	public Bidding(Player players[], int firstBidderIndex) {
		this.players = players;
		currentBidderIndex = firstBidderIndex;
	}

	Player getCurrnetBidder() {
		return (players[currentBidderIndex]);
	}

	boolean doBid() {
		// TODO Handle human bidder too.
		if (players[currentBidderIndex] instanceof AiBidder == false) {
			return false;
		}

		AiBidder bidder = (AiBidder) players[currentBidderIndex];

		int score = 0;
		if (hasLast() == true) {
			score = last().getScore();
		}

		if (bidder.canDoBid(score) == true) {
			Bid bid = bidder.doBid(score);

			if (bid.valid() == true) {
				bidHistory.add(bid);
				return true;
			} else {
				/*
				 * Pass.
				 */
				bidder.endBidding();
			}
		} else {
			/*
			 * Pass.
			 */
			bidder.endBidding();
		}

		return false;
	}

	boolean hasLast() {
		return !bidHistory.isEmpty();
	}

	Bid last() {
		if (bidHistory.isEmpty() == false) {
			return bidHistory.lastElement();
		}

		return null;
	}

	boolean hasWinner() {
		return !bidHistory.isEmpty();
	}

	Bid winner() {
		if (hasWinner() == true) {
			return bidHistory.lastElement();
		}

		return null;
	}
}
