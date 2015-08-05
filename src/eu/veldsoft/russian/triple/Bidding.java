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

	Player getCurrentBidder() {
		return (players[currentBidderIndex]);
	}

	// TODO Handle human bidder too.
	boolean doBid(int value) {
		if (players[currentBidderIndex] instanceof HumanPlayer == false) {
			return false;
		}

		Bid bid = new Bid(value, players[currentBidderIndex]);

		if (bid.valid() == true) {
			bidHistory.add(bid);
			return true;
		} else {
			/*
			 * Pass.
			 */
			players[currentBidderIndex].stopBidding();
		}

		return false;
	}

	boolean doBid() {
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

	public boolean nextBidder() {
		boolean biddingInProgress = false;
		for (Player player : players) {
			if (player.isBidding() == true) {
				biddingInProgress = true;
				break;
			}
		}

		if (biddingInProgress == false) {
			return false;
		}

		do {
			currentBidderIndex = (currentBidderIndex + 1) % players.length;
		} while (players[currentBidderIndex].isBidding() == false);

		return true;
	}
}
