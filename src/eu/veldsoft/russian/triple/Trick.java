package eu.veldsoft.russian.triple;

import java.util.Collections;
import java.util.Map;
import java.util.Vector;

class Trick {
	//TODO Player trick tracking can be useful.
	private Vector<Card> cards = new Vector<Card>();

	public Trick(Map<Player, Card> trick) {
		for (Player player : trick.keySet()) {
			cards.add(trick.get(player));
		}
	}

	public Vector<Card> getCards() {
		return cards;
	}

	public void setCards(Vector<Card> cards) {
		this.cards = cards;
	}

	public void reset() {
		cards.clear();
	}

	public void sort() {
		Collections.sort(cards, Util.noTrumpComparator);
	}

}
