package lv.volkovs.kata09;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class CheckOut {

	private Map<String, List<PriceRule>> rules = Maps.newHashMap();

	private Map<String, Integer> items = Maps.newHashMap();

	public static CheckOut instance(List<PriceRule> rules) {
		return new CheckOut(rules);
	}

	private CheckOut(List<PriceRule> rules) {
		for (PriceRule rule : rules) {
			String item = rule.getItem();
			List<PriceRule> itemRules = this.rules.get(item);
			if (itemRules == null) {
				itemRules = Lists.newArrayList();
				this.rules.put(item, itemRules);
			}
			itemRules.add(rule);
		}
	}

	public void scan(String item) {
		Integer itemsCount = items.get(item);
		if (itemsCount == null) {
			itemsCount = 0;
		}
		items.put(item, ++itemsCount);
	}

	public int getTotal() {
		int total = 0;
		for (String item : items.keySet()) {
			total += getTotal(item, Maps.newHashMap(items));
		}
		return total;
	}

	private int getTotal(String item, Map<String, Integer> items) {
		List<PriceRule> itemRules = rules.get(item);
		if (itemRules == null) {
			throw new IllegalArgumentException("Unknown item: " + item);
		}
		Collections.sort(itemRules);
		int total = 0;
		for (PriceRule itemRule : itemRules) {
			total += itemRule.apply(items);
		}
		return total;
	}

}
