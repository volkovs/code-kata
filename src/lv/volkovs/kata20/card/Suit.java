package lv.volkovs.kata20.card;

import static lv.volkovs.kata20.card.Color.*;

public enum Suit {

	CLUBS(BLACK, "\u2663"), DIAMONDS(RED, "\u2666"), HEARTS(RED, "\u2764"), SPADES(BLACK, "\u2660");

	private final Color color;

	private final String name;

	Suit(Color color, String name) {
		this.color = color;
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return name;
	}

}
