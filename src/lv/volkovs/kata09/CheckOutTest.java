package lv.volkovs.kata09;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.Lists;

public class CheckOutTest {

	private static final List<PriceRule> RULES = Lists.newArrayList();
	static {
		RULES.add(new UnitPriceRule("A", 50));
		RULES.add(new UnitPriceRule("B", 30));
		RULES.add(new UnitPriceRule("C", 20));
		RULES.add(new UnitPriceRule("D", 15));
		RULES.add(new PriceRule("A", 130, 3));
		RULES.add(new PriceRule("B", 45, 2));
	}

	public int price(String goods) {
		CheckOut co = CheckOut.instance(RULES);
		for (String item : goods.split("")) {
			if (!StringUtils.isEmpty(item)) {
				co.scan(item);
			}
		}
		return co.getTotal();
	}

	@Test
	public void getTotal() {
		assertEquals(0, price(""));
		assertEquals(50, price("A"));
		assertEquals(80, price("AB"));
		assertEquals(115, price("CDBA"));

		assertEquals(100, price("AA"));
		assertEquals(130, price("AAA"));
		assertEquals(180, price("AAAA"));
		assertEquals(230, price("AAAAA"));
		assertEquals(260, price("AAAAAA"));

		assertEquals(160, price("AAAB"));
		assertEquals(175, price("AAABB"));
		assertEquals(190, price("AAABBD"));
		assertEquals(190, price("DABABA"));
	}

	@Test
	public void incremental() {
		CheckOut co = CheckOut.instance(RULES);
		assertEquals(0, co.getTotal());

		co.scan("A");
		assertEquals(50, co.getTotal());

		co.scan("B");
		assertEquals(80, co.getTotal());

		co.scan("A");
		assertEquals(130, co.getTotal());

		co.scan("A");
		assertEquals(160, co.getTotal());

		co.scan("B");
		assertEquals(175, co.getTotal());
	}

}
