package eu.veldsoft.russian.triple;

import java.util.Vector;

class Player {
	private String name = "";

	private int score = 0;

	private Hand hand = new Hand();

	// TODO Create CollectedTricks class.
	private Vector<Card> tricks = new Vector<Card>();

	private boolean canBid = false;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Vector<Card> getTricks() {
		return tricks;
	}

	public void setTricks(Vector<Card> tricks) {
		this.tricks = tricks;
	}

	public void endBidding() {
		canBid = false;
	}

	public boolean isBidding() {
		return canBid;
	}

	//TODO Create Round class.
	public void resetRound() {
		canBid = true;
		hand.rest();
		tricks.clear();
	}

	public void recieve(Card card) {
		hand.recieve(card);
	}

	public void recieve(Card[] trick) {
		for (Card card : trick) {
			tricks.add(card);
		}
	}

	public void prepare() {
		hand.sort();
	}
}
