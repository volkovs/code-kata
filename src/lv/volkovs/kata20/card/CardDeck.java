package lv.volkovs.kata20.card;

import static java.util.Collections.*;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

public class CardDeck {

	private List<Card> cards;

	public CardDeck() {
		cards = Lists.newArrayList();
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(rank, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public List<Card> getCards() {
		return unmodifiableList(cards);
	}

	@Override
	public String toString() {
		return cards.toString();
	}

	public void output() {
		for (Card card : cards) {
			card.output();
		}
	}

}
