package lv.volkovs.kata11;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LotteryTest {

	@Test
	public void add() {
		Lottery lottery = new Lottery();
		assertEquals("[]", lottery.getBalls());
		lottery.add(20);
		assertEquals("[20]", lottery.getBalls());
		lottery.add(10);
		assertEquals("[10, 20]", lottery.getBalls());
		lottery.add(30);
		assertEquals("[10, 20, 30]", lottery.getBalls());
	}

}
