package eu.veldsoft.russian.triple;

class Bid {
	private int score;
	
	private Player player;
	
	public Bid(int score, Player player) {
		super();
		this.score = score;
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public Player getPlayer() {
		return player;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (score != other.score)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bid [score=" + score + ", player=" + player + "]";
	}
}
