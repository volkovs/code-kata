package lv.volkovs.kata08;

import java.io.IOException;

import org.junit.Test;

public class PairSearcherPerformanceTest {

	private static final int ITERATIONS = 100;

	@Test
	public void performance() throws IOException {
		PairSearcher pairSearcher = new PairSearcherReadableImpl(6); // 14.3 / 13.7
		// PairSearcher pairSearcher = new PairSearcherPerformanceImpl(6); // 11.3 / 10.5

		PairSearcherTest pairSearcherTest = new PairSearcherTest();
		pairSearcherTest.setPairSearcher(pairSearcher);
		long started = System.nanoTime();
		for (int i = 0; i < ITERATIONS; i++) {
			pairSearcherTest.search();
		}
		long ended = System.nanoTime();
		long millis = (ended - started) / 1000000;
		System.out.println(String.format("%s searches took %s ms", ITERATIONS, millis));
	}

}
