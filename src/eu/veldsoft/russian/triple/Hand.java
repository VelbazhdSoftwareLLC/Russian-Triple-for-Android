package eu.veldsoft.russian.triple;

import java.util.Collections;
import java.util.Vector;

class Hand {
	private static NoTrumpComparator noTrumpComparator = new NoTrumpComparator();

	private Vector<Card> cards = new Vector<Card>();

	public Vector<Card> getCards() {
		return cards;
	}

	public void setCards(Vector<Card> cards) {
		this.cards = cards;
	}

	public void rest() {
		cards.clear();
	}

	public void recieve(Card card) {
		cards.add(card);
	}

	public void sort() {
		Collections.sort(cards, noTrumpComparator);
	}

}
