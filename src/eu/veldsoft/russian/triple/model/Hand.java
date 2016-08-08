package eu.veldsoft.russian.triple.model;

import java.util.Collections;
import java.util.Vector;

/**
 * Player's hand class.
 * 
 * @author Todor Balabanov
 */
class Hand {
	/**
	 * Set of cards.
	 */
	private Vector<Card> cards = new Vector<Card>();

	/**
	 * Cards getter.
	 * 
	 * @return Set of cards in the player's hand.
	 */
	Vector<Card> getCards() {
		// TODO May be it is better to do a deep copy.
		return cards;
	}

	/**
	 * Cards setter.
	 * 
	 * @param cards
	 *            Set of cards in the player's hand.
	 */
	void setCards(Vector<Card> cards) {
		// TODO May be it is better to do a deep copy.
		this.cards = cards;
	}

	/**
	 * Reset player's hand by removing all cards.
	 */
	void reset() {
		cards.clear();
	}

	/**
	 * Add card in the player's hand.
	 * 
	 * @param card
	 *            Card to be added.
	 */
	void recieve(Card card) {
		cards.add(card);
	}

	/**
	 * Arrange class according card arrangement policy.
	 */
	void sort() {
		Collections.sort(cards, Util.noTrumpComparator);
	}
}
