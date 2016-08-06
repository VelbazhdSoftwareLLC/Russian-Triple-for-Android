package eu.veldsoft.russian.triple.model;

import java.util.Collections;
import java.util.Vector;

class Hand {
	private Vector<Card> cards = new Vector<Card>();

	Vector<Card> getCards() {
		return cards;
	}

	void setCards(Vector<Card> cards) {
		this.cards = cards;
	}

	void reset() {
		cards.clear();
	}

	void recieve(Card card) {
		cards.add(card);
	}

	void sort() {
		Collections.sort(cards, Util.noTrumpComparator);
	}

}
