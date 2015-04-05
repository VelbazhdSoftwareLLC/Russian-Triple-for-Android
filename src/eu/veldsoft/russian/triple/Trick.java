package eu.veldsoft.russian.triple;

import java.util.Collections;
import java.util.Map;
import java.util.Vector;

class Trick {
	// TODO Player trick tracking can be useful.
	private Vector<Card> cards = new Vector<Card>();

	public Trick(Map<Player, Card> trick) {
		for (Player player : trick.keySet()) {
			cards.add(trick.get(player));
		}
	}

	Vector<Card> getCards() {
		return cards;
	}

	void setCards(Vector<Card> cards) {
		this.cards = cards;
	}

	void reset() {
		cards.clear();
	}

	void sort() {
		Collections.sort(cards, Util.noTrumpComparator);
	}

}
