package lv.volkovs.kata20.card;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class CardDeckTest {

	private CardDeck cardDeck;

	@Before
	public void setUp() {
		cardDeck = new CardDeck();
		System.out.println();
	}

	@Test
	public void constructor() {
		assertEquals(52, cardDeck.getCards().size());
		Iterator<Card> iterator = cardDeck.getCards().iterator();
		assertTrue(isFollowing(iterator));
		cardDeck.output();
	}

	@Test
	public void shuffle() {
		cardDeck.shuffle();
		Iterator<Card> iterator = cardDeck.getCards().iterator();
		assertFalse(isFollowing(iterator));
		cardDeck.output();
	}

	private boolean isFollowing(Iterator<Card> iterator) {
		return iterator.next().getRank().getOrder() == iterator.next().getRank().getOrder() - 1;
	}
}
