package eu.veldsoft.russian.triple;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

class Board {
	public static final int HUMAN_PLAYER_INDEX = 1;

	private State state = State.STARTING;

	private int firstInRoundIndex = HUMAN_PLAYER_INDEX;

	private int currentBidderIndex = HUMAN_PLAYER_INDEX;

	private Bid currentBid = null;

	// TODO Create BidHistory class.
	private Vector<Bid> bidHistory = new Vector<Bid>();

	private Talon talon = new Talon();

	private Map<Player, Card> trick = new HashMap<Player, Card>();

	private Player players[] = { new ComputerPlayer("Player 1"),
			new HumanPlayer("Player 2"), new ComputerPlayer("Player 3") };

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Talon getTalon() {
		return talon;
	}

	public void setTalon(Talon talon) {
		this.talon = talon;
	}

	public Vector<Card> getTrick() {
		Vector<Card> trick = new Vector<Card>();

		for (Player player : players) {
			trick.add(this.trick.get(player));
		}

		return trick;
	}

	public void setTrick(Vector<Card> trick) {
		// TODO Check for number of cards in the trick.

		this.trick.clear();

		for (int i = 0; i < trick.size() && i < players.length; i++) {
			this.trick.put(players[i], trick.elementAt(i));
		}
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Player getCurrnetBidder() {
		return (players[currentBidderIndex]);
	}

	public String[] getPlayersInfo() {
		String info[] = new String[players.length];

		for (int p = 0; p < players.length; p++) {
			info[p] = players[p].getName() + " ~ " + players[p].getScore();
		}

		return info;
	}

	public Card[] getCardsOnTheBoard() {
		Card cards[] = { null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, };

		int index = 0;
		for (int p = 0; p < players.length; p++, index = p * 8) {
			for (Card card : players[p].getHand().getCards()) {
				cards[index] = card;
				index++;
			}
		}

		index = 24;
		for (Card card : talon.getCards()) {
			cards[index] = card;
			index++;
		}

		index = 27;
		for (Player player : players) {
			cards[index] = trick.get(player);
			index++;
		}

		return cards;
	}

	public void resetGame() {
		state = State.STARTING;
		firstInRoundIndex = Util.PRNG.nextInt(players.length);
		resetRound();
	}

	public void resetRound() {
		Card.Suit.removeTrump();
		bidHistory.clear();
		talon.reset();
		trick.clear();
		for (Player player : players) {
			player.resetRound();
		}
		for (int p = 0; p < players.length; p++) {
			if (firstInRoundIndex == p) {
				firstInRoundIndex = (p + 1) % players.length;
				break;
			}
		}
		currentBidderIndex = firstInRoundIndex;
	}

	public void deal() {
		state = State.DEALING;

		Deck.reset();
		Deck.shuffle();

		int index = 0;

		/*
		 * Deal talon.
		 */
		for (int i = 0; i < 3; i++) {
			Deck.cardAtPosition(index).faceDown();
			talon.recieve(Deck.cardAtPosition(index));
			index++;
		}
		talon.sort();

		for (int p = 0; p < players.length; p++) {
			for (int i = 0; i < 7; i++) {
				Card card = Deck.cardAtPosition(index);
				if (p == HUMAN_PLAYER_INDEX) {
					card.faceUp();
				}
				players[p].recieve(card);
				index++;
			}
			players[p].prepare();
		}

		state = State.BIDDING;
	}

	public void click(int selected) {
		/*
		 * Players hands.
		 */
		int index = 0;
		for (int p = 0; p < players.length; p++, index = p * 8) {
			for (Card card : players[p].getHand().getCards()) {
				if (index == selected) {
					if (p == HUMAN_PLAYER_INDEX) {
						/*
						 * Talon spliting action.
						 */
						if (card.isUnhighlighted() == true) {
							Deck.setAllUnhighlighted();
							card.highlight();
						} else if (card.isHighlighted() == true) {
							card.unhighlight();
						}
					}

					// TODO Take actions.
					return;
				}

				index++;
			}
		}

		/*
		 * Talon.
		 */
		index = 24;
		for (Card card : talon.getCards()) {
			if (index == selected) {
				if (card.isUnhighlighted() == true) {
					Deck.setAllUnhighlighted();
					card.highlight();
				} else if (card.isHighlighted() == true) {
					card.unhighlight();
				}

				// TODO Take actions.
				return;
			}

			index++;
		}

		/*
		 * Trick.
		 */
		index = 27;
		for (Player player : players) {
			if (index == selected) {
				// TODO Take actions.
				return;
			}

			index++;
		}
	}

	public boolean doBid() {
		if (players[currentBidderIndex] instanceof AiBidder == false) {
			return false;
		}

		AiBidder bidder = (AiBidder) players[currentBidderIndex];

		int score = 0;
		if (bidHistory.isEmpty() == false) {
			score = bidHistory.lastElement().getScore();
		}

		if (bidder.canDoBid(score) == true) {
			Bid bid = bidder.doBid(score);

			if (bid.valid() == true) {
				currentBid = bid;
				bidHistory.add(currentBid);
				return true;
			} else {
				/*
				 * Pass.
				 */
			}
		} else {
			/*
			 * Pass.
			 */
		}

		return false;

	}
}
