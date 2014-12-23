package lv.volkovs.kata09;

import java.util.Map;

public class PriceRule implements Comparable<PriceRule> {

	private final String item;

	private final int price;

	private final int count;

	public PriceRule(String item, int price, int count) {
		this.item = item;
		this.price = price;
		this.count = count;
	}

	public String getItem() {
		return item;
	}

	public int getPrice() {
		return price;
	}

	public int getCount() {
		return count;
	}

	public Integer getPriority() {
		return 10;
	}

	@Override
	public int compareTo(PriceRule that) {
		return this.getPriority().compareTo(that.getPriority());
	}

	public int apply(Map<String, Integer> items) {
		Integer itemsCount = items.get(item);
		int itemsPrice = itemsCount / count * price;
		int itemsLeft = itemsCount - itemsCount / count * count;
		items.put(item, itemsLeft);
		return itemsPrice;
	}
}
