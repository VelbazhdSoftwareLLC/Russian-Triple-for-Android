package eu.veldsoft.russian.triple.model;

public class Card {

	public enum Rank {
		NINE(0, "Nine"), JACK(2, "Jack"), QUEEN(3, "Queen"), KING(4, "King"), TEN(
				10, "Ten"), ACE(11, "Ace");

		private int points;

		private String name;

		private Rank(int number, String name) {
			this.points = number;
			this.name = name;
		}

		int getPoints() {
			return points;
		}

		String getName() {
			return name;
		}

		boolean isAdjacentTo(Rank rank) {
			// TODO Do not use internal numbering.

			if ((this.points + 1) == rank.points) {
				return (true);
			}

			if ((rank.points + 1) == this.points) {
				return (true);
			}

			return (false);
		}
	}

	public enum Suit {
		DIAMONDS(1, "Diamonds"), CLUBS(2, "Clubs"), HEARTS(3, "Hearts"), SPADES(
				4, "Spades");

		private int order = 0;

		private String name = null;

		private boolean trump = false;

		static void removeTrump() {
			// TODO May be it is not working!
			for (Suit suit : Suit.values()) {
				suit.trump = false;
			}
		}

		public static boolean isTrumpSelected() {
			for (Suit suit : Suit.values()) {
				if (suit.isTrump() == true) {
					return true;
				}
			}

			return false;
		}

		private Suit(int number, String name) {
			this.order = number;
			this.name = name;
		}

		int getOrder() {
			return order;
		}

		String getName() {
			return name;
		}

		void setTrump() {
			removeTrump();
			trump = true;
		}

		public boolean isTrump() {
			return (trump);
		}
	}

	private Card.Suit suit;

	private Card.Rank rank;

	private boolean faceUp;

	private boolean highlighted;

	private boolean visible;

	public Card(Rank rank, Suit suit, boolean faceUp, boolean highlighted,
			boolean visible) {
		super();
		this.rank = rank;
		this.suit = suit;
		this.faceUp = faceUp;
		this.highlighted = highlighted;
		this.visible = visible;
	}

	@Override
	public int hashCode() {
		final int prime = 31;

		int result = 17;

		result = prime * result + ((rank == null) ? 0 : rank.hashCode());

		result = prime * result + ((suit == null) ? 0 : suit.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Card other = (Card) obj;

		if (rank != other.rank) {
			return false;
		}

		if (suit != other.suit) {
			return false;
		}

		return true;
	}

	Card.Rank getRank() {
		return rank;
	}

	void setRank(Card.Rank rank) {
		this.rank = rank;
	}

	Card.Suit getSuit() {
		return suit;
	}

	void setSuit(Card.Suit suit) {
		this.suit = suit;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public boolean isFaceDown() {
		return !faceUp;
	}

	void faceUp() {
		faceUp = true;
	}

	void faceDown() {
		faceUp = false;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	boolean isUnhighlighted() {
		return !highlighted;
	}

	void highlight() {
		highlighted = true;
	}

	void unhighlight() {
		highlighted = false;
	}

	void visible() {
		visible = true;
	}

	void invisible() {
		visible = false;
	}

	boolean isVisible() {
		return visible;
	}

	public boolean isInvisible() {
		return !visible;
	}

	void flip() {
		faceUp = !faceUp;
	}
}
