package eu.veldsoft.russian.triple.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Card deck.
 * 
 * @author Todor Balabanov
 */
final public class Deck {
	/**
	 * Set of cards.
	 */
	static private final List<Card> CARDS = new ArrayList<Card>();

	/**
	 * Initialize static fields.
	 */
	static {
		CARDS.add(new Card(Card.Rank.NINE, Card.Suit.CLUBS, false, false, false));
		CARDS.add(new Card(Card.Rank.TEN, Card.Suit.CLUBS, false, false, false));
		CARDS.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS, false, false, false));
		CARDS.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUBS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.KING, Card.Suit.CLUBS, false, false, false));
		CARDS.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS, false, false, false));
		CARDS.add(new Card(Card.Rank.NINE, Card.Suit.HEARTS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.TEN, Card.Suit.HEARTS, false, false, false));
		CARDS.add(new Card(Card.Rank.JACK, Card.Suit.HEARTS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.QUEEN, Card.Suit.HEARTS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.KING, Card.Suit.HEARTS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.ACE, Card.Suit.HEARTS, false, false, false));
		CARDS.add(new Card(Card.Rank.NINE, Card.Suit.DIAMONDS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.JACK, Card.Suit.DIAMONDS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.KING, Card.Suit.DIAMONDS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.ACE, Card.Suit.DIAMONDS, false, false,
				false));
		CARDS.add(new Card(Card.Rank.NINE, Card.Suit.SPADES, false, false,
				false));
		CARDS.add(new Card(Card.Rank.TEN, Card.Suit.SPADES, false, false, false));
		CARDS.add(new Card(Card.Rank.JACK, Card.Suit.SPADES, false, false,
				false));
		CARDS.add(new Card(Card.Rank.QUEEN, Card.Suit.SPADES, false, false,
				false));
		CARDS.add(new Card(Card.Rank.KING, Card.Suit.SPADES, false, false,
				false));
		CARDS.add(new Card(Card.Rank.ACE, Card.Suit.SPADES, false, false, false));
	}

	/**
	 * Deck size.
	 */
	public static final int SIZE = CARDS.size();

	/**
	 * Reset all cards in the deck.
	 */
	static void reset() {
		setAllUnhighlighted();
		setAllFaceDown();
		setAllVisible();
	}

	/**
	 * Shuffle deck.
	 */
	static void shuffle() {
		Collections.shuffle(CARDS);
	}

	/**
	 * Set all cards in the deck face up.
	 */
	static void setAllFaseUp() {
		for (Card card : CARDS) {
			card.faceUp();
		}
	}

	/**
	 * Set all cards in the deck face down.
	 */
	static void setAllFaceDown() {
		for (Card card : CARDS) {
			card.faceDown();
		}
	}

	/**
	 * Set all cards in the deck selected.
	 */
	static void setAllHighlighted() {
		for (Card card : CARDS) {
			card.highlight();
		}
	}

	/**
	 * Set all cards in the deck unselected.
	 */
	static void setAllUnhighlighted() {
		for (Card card : CARDS) {
			card.unhighlight();
		}
	}

	/**
	 * Set all cards in the deck visible.
	 */
	static void setAllVisible() {
		for (Card card : CARDS) {
			card.visible();
		}
	}

	/**
	 * Set all cards in the deck invisible.
	 */
	static void setAllInvisible() {
		for (Card card : CARDS) {
			card.invisible();
		}
	}

	/**
	 * Obtain card on the specific position in the deck.
	 * 
	 * @param index
	 *            Index of the card in the deck.
	 * 
	 * @return Card as object reference.
	 * 
	 * @throws RuntimeException
	 *             Return with error if the index is invalid.
	 */
	public static Card cardAtPosition(int index) throws RuntimeException {
		if (index < 0 || index >= CARDS.size()) {
			throw (new IndexOutOfBoundsException("No such card!"));
		}

		return (CARDS.get(index));
	}

	/**
	 * Private constructor, because instance in not needed from this class.
	 */
	private Deck() {
	}
}
