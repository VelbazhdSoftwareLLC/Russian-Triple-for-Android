package eu.veldsoft.russian.triple.model;

import java.util.Comparator;

class NoTrumpComparator implements Comparator<Card> {

	@Override
	public int compare(Card lhs, Card rhs) {
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
