package lv.volkovs.kata09;

public class UnitPriceRule extends PriceRule {

	public UnitPriceRule(String item, int price) {
		super(item, price, 1);
	}

	@Override
	public Integer getPriority() {
		return 20;
	}

}
