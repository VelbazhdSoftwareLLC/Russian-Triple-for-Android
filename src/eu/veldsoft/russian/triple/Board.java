package eu.veldsoft.russian.triple;

import java.util.Vector;

public class Board {
	public static final int HUMAN_PLAYER_INDEX = 1;
	
	private Card.Suit trump;

	// TODO Create Talon class.
	private Vector<Card> talon = new Vector<Card>();

	// TODO Create Trick class.
	private Vector<Card> trick = new Vector<Card>();

	private Player players[] = { new Player(), new Player(), new Player() };
	public Card.Suit getTrump() {
		return trump;
	}

	public void setTrump(Card.Suit trump) {
		this.trump = trump;
	}

	public Vector<Card> getTalon() {
		return talon;
	}

	public void setTalon(Vector<Card> talon) {
		this.talon = talon;
	}

	public Vector<Card> getTrick() {
		return trick;
	}

	public void setTrick(Vector<Card> trick) {
		this.trick = trick;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Card[] getCardsOnTheBoard() {
		Card cards[] = { null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null,
				null, };
		
		int index = 0;
		for(int p=0; p<players.length; p++, index=p*8) {
			for(Card card : players[p].getHand()) {
				cards[index] = card;
				index++;
			}
		}
		
		index = 24;
		for(Card card : talon) {
			cards[index] = card;
			index++;
		}
		
		index = 27;
		for(Card card : trick) {
			cards[index] = card;
			index++;
		}
		
		return cards;
	}

	public void deal() {
		Deck.reset();
		Deck.shuffle();
		
		int index = 0;
		
		/*
		 * Deal talon.
		 */
		for(int i=0; i<3; i++) {
			talon.add(Deck.cardAtPosition(index));
			index++;
		}
		
		for(int p=0; p<players.length; p++) {
			for(int i=0; i<7; i++) {
				Card card = Deck.cardAtPosition(index);
				if(p == HUMAN_PLAYER_INDEX) {
					card.visible();
				}
				players[p].recieve(card);
				index++;
			}
			players[p].sort(players[p].getHand());
		}
	}
}
