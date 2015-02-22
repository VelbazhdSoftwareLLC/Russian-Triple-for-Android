package eu.veldsoft.russian.triple;

class Deck {
	static private final Card[] cards = {
			new Card(Card.Rank.NINE, Card.Suit.CLUBS, false, false, false),
			new Card(Card.Rank.TEN, Card.Suit.CLUBS, false, false, false),
			new Card(Card.Rank.JACK, Card.Suit.CLUBS, false, false, false),
			new Card(Card.Rank.QUEEN, Card.Suit.CLUBS, false, false, false),
			new Card(Card.Rank.KING, Card.Suit.CLUBS, false, false, false),
			new Card(Card.Rank.ACE, Card.Suit.CLUBS, false, false, false),
			new Card(Card.Rank.NINE, Card.Suit.HEARTS, false, false, false),
			new Card(Card.Rank.TEN, Card.Suit.HEARTS, false, false, false),
			new Card(Card.Rank.JACK, Card.Suit.HEARTS, false, false, false),
			new Card(Card.Rank.QUEEN, Card.Suit.HEARTS, false, false, false),
			new Card(Card.Rank.KING, Card.Suit.HEARTS, false, false, false),
			new Card(Card.Rank.ACE, Card.Suit.HEARTS, false, false, false),
			new Card(Card.Rank.NINE, Card.Suit.DIAMONDS, false, false, false),
			new Card(Card.Rank.TEN, Card.Suit.DIAMONDS, false, false, false),
			new Card(Card.Rank.JACK, Card.Suit.DIAMONDS, false, false, false),
			new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS, false, false, false),
			new Card(Card.Rank.KING, Card.Suit.DIAMONDS, false, false, false),
			new Card(Card.Rank.ACE, Card.Suit.DIAMONDS, false, false, false),
			new Card(Card.Rank.NINE, Card.Suit.SPADES, false, false, false),
			new Card(Card.Rank.TEN, Card.Suit.SPADES, false, false, false),
			new Card(Card.Rank.JACK, Card.Suit.SPADES, false, false, false),
			new Card(Card.Rank.QUEEN, Card.Suit.SPADES, false, false, false),
			new Card(Card.Rank.KING, Card.Suit.SPADES, false, false, false),
			new Card(Card.Rank.ACE, Card.Suit.SPADES, false, false, false), };

	public static final int SIZE = cards.length;

	static void reset() {
		setAllUnhighlighted();
		setAllFaceDown();
		setAllVisible();
	}

	static void shuffle() {
		for (int last = cards.length - 1, r = -1; last > 0; last--) {
			r = Util.PRNG.nextInt(last + 1);
			Card swap = cards[last];
			cards[last] = cards[r];
			cards[r] = swap;
		}
	}

	static void setAllFaseUp() {
		for (Card card : cards) {
			card.faceUp();
		}
	}

	static void setAllFaceDown() {
		for (Card card : cards) {
			card.faceDown();
		}
	}

	static void setAllHighlighted() {
		for (Card card : cards) {
			card.highlight();
		}
	}

	static void setAllUnhighlighted() {
		for (Card card : cards) {
			card.unhighlight();
		}
	}

	static void setAllVisible() {
		for (Card card : cards) {
			card.visible();
		}
	}

	static void setAllInvisible() {
		for (Card card : cards) {
			card.invisible();
		}
	}

	static Card cardAtPosition(int index) throws RuntimeException {
		if (index < 0 || index >= cards.length) {
			throw (new IndexOutOfBoundsException("No such card!"));
		}

		return (cards[index]);
	}

	private Deck() {
	}
}
