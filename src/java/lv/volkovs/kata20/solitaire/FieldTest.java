package lv.volkovs.kata20.solitaire;

import lv.volkovs.kata20.card.CardDeck;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {

	private Field field;

	@Before
	public void setUp() {
		field = new Field(new CardDeck());
	}

	@Test
	public void layout() {
		field.output();
	}

}
