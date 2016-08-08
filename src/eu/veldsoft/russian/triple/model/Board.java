package eu.veldsoft.russian.triple.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Game board class. The most common object in the object model.
 * 
 * @author Todor Balabanov
 */
public class Board {
	/**
	 * Index of the human player in the array with the players.
	 */
	public static final int HUMAN_PLAYER_INDEX = 1;

	/**
	 * Game state.
	 */
	private State state = State.STARTING;

	/**
	 * Each round different player is first.
	 */
	private int firstInRoundIndex = HUMAN_PLAYER_INDEX;

	/**
	 * Bidding process object.
	 */
	private Bidding bidding = null;

	/**
	 * Talone object.
	 */
	private Talon talon = null;

	/**
	 * Tricks played.
	 */
	private Map<Player, Card> trick = new HashMap<Player, Card>();

	/**
	 * All players on the board.
	 */
	private Player players[] = { new ComputerPlayer("Player 1"),
			new HumanPlayer("Player 2"), new ComputerPlayer("Player 3") };

	/**
	 * State getter.
	 * 
	 * @return Board state.
	 */
	public State getState() {
		return state;
	}

	/**
	 * State setter.
	 * 
	 * @param state
	 *            Next board state.
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Bidding object getter.
	 * 
	 * @return Bidding object.
	 */
	public Bidding getBidding() {
		// TODO Consider to do a deep copy of this object.
		return bidding;
	}

	/**
	 * Talon getter.
	 * 
	 * @return Talon object.
	 */
	Talon getTalon() {
		// TODO Consider to do a deep copy of this object.
		return talon;
	}

	/**
	 * Talon setter.
	 * 
	 * @param talon
	 *            New talon.
	 */
	void setTalon(Talon talon) {
		// TODO Consider to do a deep copy of this object.
		this.talon = talon;
	}

	/**
	 * Trick getter.
	 * 
	 * @return Last trick.
	 */
	Vector<Card> getTrick() {
		Vector<Card> trick = new Vector<Card>();

		for (Player player : players) {
			trick.add(this.trick.get(player));
		}

		return trick;
	}

	/**
	 * Trick setter.
	 * 
	 * @param trick
	 *            New trick.
	 */
	void setTrick(Vector<Card> trick) {
		// TODO Check for number of cards in the trick.

		this.trick.clear();

		for (int i = 0; i < trick.size() && i < players.length; i++) {
			this.trick.put(players[i], trick.elementAt(i));
		}
	}

	/**
	 * Players getter.
	 * 
	 * @return All players on the board.
	 */
	Player[] getPlayers() {
		return players;
	}

	/**
	 * Players setter.
	 * 
	 * @param players
	 *            All players who should be on the board.
	 */
	void setPlayers(Player[] players) {
		this.players = players;
	}

	/**
	 * Player internal information.
	 * 
	 * @return Service information for the players.
	 */
	public String[] getPlayersInfo() {
		String info[] = new String[players.length];

		for (int p = 0; p < players.length; p++) {
			info[p] = players[p].getName() + " ~ " + players[p].getScore();
		}

		return info;
	}

	/**
	 * Get all cards which are on the board.
	 * 
	 * @return All visible cards on the board even if they should be face downd.
	 */
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

	/**
	 * Reset full game.
	 */
	public void resetGame() {
		state = State.STARTING;
		firstInRoundIndex = Util.PRNG.nextInt(players.length);
		resetRound();
	}

	/**
	 * Reset the current round.
	 */
	public void resetRound() {
		Card.Suit.removeTrump();
		bidding = new Bidding(players, firstInRoundIndex);
		talon = new Talon();
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
	}

	/**
	 * Deal cards in the beginning of each round.
	 */
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

	/**
	 * Modify board according selected card.
	 * 
	 * @param selected
	 *            Index of the card which was selected.
	 */
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
}
