package eu.veldsoft.russian.triple;

import java.util.Vector;

class Player {
	private String name = "";

	private int score = 0;

	private Hand hand = new Hand();

	private Vector<Trick> tricks = new Vector<Trick>();

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

	public Vector<Trick> getTricks() {
		return tricks;
	}

	public void setTricks(Vector<Trick> tricks) {
		this.tricks = tricks;
	}

	public void endBidding() {
		canBid = false;
	}

	public boolean isBidding() {
		return canBid;
	}

	// TODO Create Round class.
	public void resetRound() {
		canBid = true;
		hand.reset();
		tricks.clear();
	}

	public void recieve(Card card) {
		hand.recieve(card);
	}

	public void recieve(Trick trick) {
		tricks.add(trick);
	}

	public void prepare() {
		hand.sort();
	}
}
