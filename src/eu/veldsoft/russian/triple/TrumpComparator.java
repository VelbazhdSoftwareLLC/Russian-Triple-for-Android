package eu.veldsoft.russian.triple;

import java.util.Comparator;

class TrumpComparator implements Comparator<Card> {

	@Override
	public int compare(Card lhs, Card rhs) {
		if (lhs.getSuit().isTrump() == true && rhs.getSuit().isTrump() == false) {
			return -1;
		}

		if (lhs.getSuit().isTrump() == false && rhs.getSuit().isTrump() == true) {
			return +1;
		}

		if (lhs.getSuit().getOrder() > rhs.getSuit().getOrder()) {
			return -1;
		} else if (lhs.getSuit().getOrder() < rhs.getSuit().getOrder()) {
			return +1;
		} else {
			if (lhs.getRank().getPoints() > rhs.getRank().getPoints()) {
				return -1;
			} else if (lhs.getRank().getPoints() < rhs.getRank().getPoints()) {
				return +1;
			}
		}

		return 0;
	}

}
