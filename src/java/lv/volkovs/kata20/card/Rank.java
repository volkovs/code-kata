package lv.volkovs.kata20.card;

public enum Rank implements Comparable<Rank> {

	ACE("A", 1), _2(2), _3(3), _4(4), _5(5), _6(6), _7(7), _8(8), _9(9), _10(10), JACK("J", 11), QUEEN("Q", 12), KING("K", 13);

	private final int order;

	private String name;

	Rank(int order) {
		name = "" + order;
		this.order = order;
	}

	Rank(String name, int order) {
		this.name = name;
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

	public boolean canAddToDiscardPile(Rank pileTopCard) {
		return order == pileTopCard.order - 1;
	}

	public boolean canAddToFoundationPile(Rank pileTopCard) {
		return order == pileTopCard.order + 1;
	}

	@Override
	public String toString() {
		return name;
	}

}
