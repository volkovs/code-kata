package lv.volkovs.kata08;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PairSearcherTest {

	private PairSearcher pairSearcher;

	@Before
	public void setUp() throws IOException {
		pairSearcher = new PairSearcherReadableImpl(6);
		// pairSearcher = new PairSearcherPerformanceImpl(6);
		// pairSearcher = new PairSearcherExtendableImpl();
	}

	@Test
	public void search() {
		List<String> pairs = pairSearcher.search();
		assertEquals(22510, pairs.size());
		// Collections.sort(pairs);
		// System.out.println(pairs);
	}

	public void setPairSearcher(PairSearcher pairSearcher) {
		this.pairSearcher = pairSearcher;
	}

}
