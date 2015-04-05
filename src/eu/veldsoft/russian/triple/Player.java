package eu.veldsoft.russian.triple;

import java.util.Vector;

class Player {
	private String name = "";

	private int score = 0;

	private Hand hand = new Hand();

	private Vector<Trick> tricks = new Vector<Trick>();

	// TODO May be it should be in Bidding class.
	private boolean canBid = false;

	public Player(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	int getScore() {
		return score;
	}

	void setScore(int score) {
		this.score = score;
	}

	Hand getHand() {
		return hand;
	}

	void setHand(Hand hand) {
		this.hand = hand;
	}

	Vector<Trick> getTricks() {
		return tricks;
	}

	void setTricks(Vector<Trick> tricks) {
		this.tricks = tricks;
	}

	boolean isBidding() {
		return canBid;
	}

	void stopBidding() {
		canBid = false;
	}

	// TODO Create Round class.
	void resetRound() {
		canBid = true;
		hand.reset();
		tricks.clear();
	}

	void recieve(Card card) {
		hand.recieve(card);
	}

	void recieve(Trick trick) {
		tricks.add(trick);
	}

	void prepare() {
		hand.sort();
	}
}
