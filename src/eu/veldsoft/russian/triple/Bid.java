package eu.veldsoft.russian.triple;

import java.util.Vector;

import eu.veldsoft.russian.triple.Card.Rank;

class Bid {
	private int score;

	private Player player;

	public Bid(Bid bid) {
		super();
		this.score = bid.score;
		this.player = bid.player;
	}

	public Bid(int score, Player player) {
		super();
		this.score = score;
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public Player getPlayer() {
		return player;
	}

	public int maximum() {
		int numberOfMarriages = 0;

		Vector<Card> cards = player.getHand().getCards();

		for (Card a : cards) {
			if (a.getRank() != Rank.QUEEN) {
				continue;
			}

			for (Card b : cards) {
				if (b.getRank() != Rank.KING) {
					continue;
				}

				if (a.getSuit() == b.getSuit()) {
					numberOfMarriages++;
				}
			}
		}

		if (numberOfMarriages < 1) {
			return 120;
		}

		if (numberOfMarriages < 2) {
			return 160;
		}

		if (numberOfMarriages < 3) {
			return 180;
		}

		if (numberOfMarriages < 4) {
			return 200;
		}

		return 0;
	}

	public boolean valid() {
		if (score > maximum()) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (score != other.score)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bid [score=" + score + ", player=" + player + "]";
	}
}
