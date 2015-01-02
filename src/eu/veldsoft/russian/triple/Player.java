package eu.veldsoft.russian.triple;

import java.util.Collections;
import java.util.Vector;

class Player {
	private static NoTrumpComparator cardsOrder = new NoTrumpComparator();
	
	private String name;

	private int score;

	// TODO Create Hand class.
	private Vector<Card> hand = new Vector<Card>();

	// TODO Create CollectedTricks class.
	private Vector<Card> tricks = new Vector<Card>();

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

	public Vector<Card> getHand() {
		return hand;
	}

	public void setHand(Vector<Card> hand) {
		this.hand = hand;
	}

	public Vector<Card> getTricks() {
		return tricks;
	}

	public void setTricks(Vector<Card> tricks) {
		this.tricks = tricks;
	}

	public void resetRound() {
		hand.clear();
		tricks.clear();
	}

	public void recieve(Card card) {
		hand.add(card);
	}

	public void recieve(Card[] trick) {
		for (Card card : trick) {
			tricks.add(card);
		}
	}

	public void sort(Vector<Card> hand) {
		//Collections.sort((Vector) hand, cardsOrder);
	}
}
