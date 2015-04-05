package eu.veldsoft.russian.triple;

import java.util.Collections;
import java.util.Vector;

class Talon {
	private Vector<Card> cards = new Vector<Card>();

	Vector<Card> getCards() {
		return cards;
	}

	void setCards(Vector<Card> cards) {
		this.cards = cards;
	}

	void recieve(Card card) {
		cards.add(card);
	}

	void reset() {
		cards.clear();
	}

	void sort() {
		Collections.sort(cards, Util.noTrumpComparator);
	}
}
