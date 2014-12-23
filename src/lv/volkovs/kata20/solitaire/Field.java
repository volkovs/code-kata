package lv.volkovs.kata20.solitaire;

import static com.google.common.base.Preconditions.*;

import java.util.List;
import java.util.Stack;

import lv.volkovs.kata20.card.Card;
import lv.volkovs.kata20.card.CardDeck;

import com.google.common.collect.Lists;

public class Field {

	private Stack<Card> deck = new Stack<Card>();

	private Stack<Card> deckUsed = new Stack<Card>();

	private Stack<Card> foundationPile1 = new Stack<Card>();
	private Stack<Card> foundationPile2 = new Stack<Card>();
	private Stack<Card> foundationPile3 = new Stack<Card>();
	private Stack<Card> foundationPile4 = new Stack<Card>();
	private List<Stack<Card>> foundationPiles = Lists.newArrayList();
	{
		foundationPiles.add(foundationPile1);
		foundationPiles.add(foundationPile2);
		foundationPiles.add(foundationPile3);
		foundationPiles.add(foundationPile4);
	}

	private Stack<Card> discardPile1 = new Stack<Card>();
	private Stack<Card> discardPile2 = new Stack<Card>();
	private Stack<Card> discardPile3 = new Stack<Card>();
	private Stack<Card> discardPile4 = new Stack<Card>();
	private Stack<Card> discardPile5 = new Stack<Card>();
	private Stack<Card> discardPile6 = new Stack<Card>();
	private Stack<Card> discardPile7 = new Stack<Card>();
	private List<Stack<Card>> discardPiles = Lists.newArrayList();
	{
		discardPiles.add(discardPile1);
		discardPiles.add(discardPile2);
		discardPiles.add(discardPile3);
		discardPiles.add(discardPile4);
		discardPiles.add(discardPile5);
		discardPiles.add(discardPile6);
		discardPiles.add(discardPile7);
	}

	public Field(CardDeck deck) {
		deck.shuffle();
		for (Card card : deck.getCards()) {
			this.deck.push(card);
		}

		for (int i = 0; i < discardPiles.size(); i++) {
			for (int j = i; j < discardPiles.size(); j++) {
				Card card = this.deck.pop();
				discardPiles.get(j).push(card);
				if (i == j) {
					card.show();
				}
			}
		}
	}

	public void rotate() {
		checkState(deck.isEmpty());
		while (!deckUsed.isEmpty()) {
			deck.push(deckUsed.pop());
		}
	}

	public void output() {
		System.out.println();
		tableBorder();

		// deck
		monolithPile(deck);

		// deck used
		showTop(deckUsed, 3);
		System.out.print("         ");

		// foundation piles
		for (Stack<Card> pile : foundationPiles) {
			monolithPile(pile);
			System.out.print("   ");
		}
		System.out.println();

		// discard piles
		tableBorder();
		for (Stack<Card> discardPile : discardPiles) {
			startPile();
			for (Card card : discardPile) {
				card.output();
			}
			endPile();
			System.out.println();
		}
		tableBorder();
	}

	private void tableBorder() {
		System.out.println("----------------------------------------------------------------------------------------------------");
	}

	private void showTop(Stack<Card> deck, int count) {
		int cardsToShow = Math.min(3, deck.size());
		startPile();
		if (cardsToShow == 0) {
			emptyPile();
		} else {
			for (int i = 0; i < cardsToShow; i++) {
				deck.get(deck.size() - i).output();
			}
		}
		endPile();
	}

	private void monolithPile(Stack<Card> pile) {
		startPile();
		if (pile.isEmpty()) {
			emptyPile();
		} else {
			pile.peek().output();
		}
		endPile();
	}

	private void startPile() {
		System.out.print("{ ");
	}

	private void endPile() {
		System.out.print("} ");
	}

	private void emptyPile() {
		System.out.print("-- ");
	}

}
