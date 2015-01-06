package eu.veldsoft.russian.triple;

class Card {

	enum Rank {
		NINE(0, "Nine"), JACK(2, "Jack"), QUEEN(3, "Queen"), KING(4, "King"), TEN(
				10, "Ten"), ACE(11, "Ace");

		private int points;

		private String name;

		private Rank(int number, String name) {
			this.points = number;
			this.name = name;
		}

		public int getPoints() {
			return points;
		}

		public String getName() {
			return name;
		}

		public boolean isAdjacentTo(Rank rank) {
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

	enum Suit {
		DIAMONDS(1, "Diamonds"), CLUBS(2, "Clubs"), HEARTS(3, "Hearts"), SPADES(
				4, "Spades");

		private int order;

		private String name;

		private Suit(int number, String name) {
			this.order = number;
			this.name = name;
		}

		public int getOrder() {
			return order;
		}

		public String getName() {
			return name;
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
