package lv.volkovs.kata20.card;

import static lv.volkovs.kata20.card.Rank.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

	private Card jackClubs;
	private Card queenClubs;
	private Card kingClubs;

	private Card jackDiamonds;
	private Card kingDiamonds;

	private Card jackHearts;
	private Card kingHearts;

	private Card jackSpades;
	private Card kingSpades;

	@Before
	public void setUp() {
		jackClubs = new Card(JACK, Suit.CLUBS);
		queenClubs = new Card(QUEEN, Suit.CLUBS);
		kingClubs = new Card(KING, Suit.CLUBS);

		jackDiamonds = new Card(JACK, Suit.DIAMONDS);
		kingDiamonds = new Card(KING, Suit.DIAMONDS);

		jackHearts = new Card(JACK, Suit.HEARTS);
		kingHearts = new Card(KING, Suit.HEARTS);

		jackSpades = new Card(JACK, Suit.SPADES);
		kingSpades = new Card(KING, Suit.SPADES);
	}

	@Test
	public void canAddToDiscardPile() {

		// reflexive
		assertFalse(queenClubs.canAddToDiscardPile(queenClubs));

		// one point less important
		assertTrue(queenClubs.canAddToDiscardPile(kingDiamonds));
		assertTrue(queenClubs.canAddToDiscardPile(kingHearts));
		assertFalse(queenClubs.canAddToDiscardPile(kingClubs));
		assertFalse(queenClubs.canAddToDiscardPile(kingSpades));

		// one point more important
		assertFalse(queenClubs.canAddToDiscardPile(jackDiamonds));
		assertFalse(queenClubs.canAddToDiscardPile(jackHearts));
		assertFalse(queenClubs.canAddToDiscardPile(jackClubs));
		assertFalse(queenClubs.canAddToDiscardPile(jackSpades));

		// several points less important
		assertFalse(jackClubs.canAddToDiscardPile(kingDiamonds));
		assertFalse(jackClubs.canAddToDiscardPile(kingHearts));
		assertFalse(jackClubs.canAddToDiscardPile(kingClubs));
		assertFalse(jackClubs.canAddToDiscardPile(kingSpades));
	}

	@Test
	public void canAddToFoundationPile() {

		// reflexive
		assertFalse(queenClubs.canAddToFoundationPile(queenClubs));

		// one point less important
		assertFalse(queenClubs.canAddToFoundationPile(kingDiamonds));
		assertFalse(queenClubs.canAddToFoundationPile(kingHearts));
		assertFalse(queenClubs.canAddToFoundationPile(kingClubs));
		assertFalse(queenClubs.canAddToFoundationPile(kingSpades));

		// one point more important
		assertFalse(queenClubs.canAddToFoundationPile(jackDiamonds));
		assertFalse(queenClubs.canAddToFoundationPile(jackHearts));
		assertTrue(queenClubs.canAddToFoundationPile(jackClubs));
		assertFalse(queenClubs.canAddToFoundationPile(jackSpades));

		// several points less important
		assertFalse(kingClubs.canAddToFoundationPile(jackDiamonds));
		assertFalse(kingClubs.canAddToFoundationPile(jackHearts));
		assertFalse(kingClubs.canAddToFoundationPile(jackClubs));
		assertFalse(kingClubs.canAddToFoundationPile(jackSpades));
	}

}
