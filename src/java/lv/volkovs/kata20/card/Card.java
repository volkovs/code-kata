package lv.volkovs.kata20.card;

public class Card {

	private final Rank rank;

	private final Suit suit;

	private boolean visible = false;

	Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public boolean canAddToDiscardPile(Card pileTopCard) {
		return rank.canAddToDiscardPile(pileTopCard.rank) && !suit.getColor().equals(pileTopCard.suit.getColor());
	}

	public boolean canAddToFoundationPile(Card pileTopCard) {
		return rank.canAddToFoundationPile(pileTopCard.rank) && suit.equals(pileTopCard.suit);
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public boolean isVisible() {
		return visible;
	}

	public void show() {
		visible = true;
	}

	public void hide() {
		visible = false;
	}

	@Override
	public String toString() {
		String content = isVisible() ? rank.toString() + suit.toString() : "ХЗ";
		return '|' + content + '|';
	}

	public void output() {
		if (Color.RED.equals(suit.getColor()) && isVisible()) {
			System.err.print(toString() + " ");
			System.err.flush();
		} else {
			System.out.print(toString() + " ");
			System.out.flush();
		}
	}
}
