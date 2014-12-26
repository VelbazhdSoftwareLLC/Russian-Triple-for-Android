package eu.veldsoft.russian.triple;

class Card {

	enum Rank {
		TWO(2, "Two"), THREE(3, "Three"), FOUR(4, "Four"), FIVE(5, "Five"), SIX(
				6, "Six"), SEVEN(7, "Seven"), EIGHT(8, "Eight"), NINE(9, "Nine"), TEN(
				10, "Ten"), JACK(11, "Jack"), QUEEN(12, "Queen"), KING(13,
				"King"), ACE(14, "Ace");

		private int number;

		private String name;

		private Rank(int number, String name) {
			this.number = number;
			this.name = name;
		}

		public boolean isAdjacentTo(Rank rank) {
			// TODO Do not use internal numbering.

			if ((this.number + 1) == rank.number) {
				return (true);
			}

			if ((rank.number + 1) == this.number) {
				return (true);
			}

			return (false);
		}
	}

	enum Suit {
		SPADES(1, "Spades"), CLUBS(2, "Clubs"), DIAMONDS(3, "Diamonds"), HEARTS(
				4, "Hearts");

		private int number;

		private String name;

		private Suit(int number, String name) {
			this.number = number;
			this.name = name;
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

	public Card.Rank getRank() {
		return rank;
	}

	public void setRank(Card.Rank rank) {
		this.rank = rank;
	}

	public Card.Suit getSuit() {
		return suit;
	}

	public void setSuit(Card.Suit suit) {
		this.suit = suit;
	}

	public boolean isFaceUp() {
		return faceUp;
	}

	public boolean isFaceDown() {
		return !faceUp;
	}

	public void faceUp() {
		faceUp = true;
	}

	public void faceDown() {
		faceUp = false;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public boolean isUnhighlighted() {
		return !highlighted;
	}

	public void highlight() {
		highlighted = true;
	}

	public void unhighlight() {
		highlighted = false;
	}

	public void visible() {
		visible = true;
	}

	public void invisible() {
		visible = false;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isInvisible() {
		return !visible;
	}

	public void flip() {
		faceUp = !faceUp;
	}
}
