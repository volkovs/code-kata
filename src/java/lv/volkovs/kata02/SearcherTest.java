package lv.volkovs.kata02;

import static org.junit.Assert.assertEquals;
import lv.volkovs.kata02.impl.SearcherFacadeImpl;

import org.junit.Test;

public class SearcherTest {

	private Searcher searcher = new SearcherFacadeImpl();

	@Test
	public void chop() {
		assertEquals(-1, searcher.chop(3, new int[] {}));
		assertEquals(-1, searcher.chop(3, new int[] { 1 }));
		assertEquals(0, searcher.chop(1, new int[] { 1 }));

		assertEquals(0, searcher.chop(1, new int[] { 1, 3, 5 }));
		assertEquals(1, searcher.chop(3, new int[] { 1, 3, 5 }));
		assertEquals(2, searcher.chop(5, new int[] { 1, 3, 5 }));
		assertEquals(-1, searcher.chop(0, new int[] { 1, 3, 5 }));
		assertEquals(-1, searcher.chop(2, new int[] { 1, 3, 5 }));
		assertEquals(-1, searcher.chop(4, new int[] { 1, 3, 5 }));
		assertEquals(-1, searcher.chop(6, new int[] { 1, 3, 5 }));

		assertEquals(0, searcher.chop(1, new int[] { 1, 3, 5, 7 }));
		assertEquals(1, searcher.chop(3, new int[] { 1, 3, 5, 7 }));
		assertEquals(2, searcher.chop(5, new int[] { 1, 3, 5, 7 }));
		assertEquals(3, searcher.chop(7, new int[] { 1, 3, 5, 7 }));
		assertEquals(-1, searcher.chop(0, new int[] { 1, 3, 5, 7 }));
		assertEquals(-1, searcher.chop(2, new int[] { 1, 3, 5, 7 }));
		assertEquals(-1, searcher.chop(4, new int[] { 1, 3, 5, 7 }));
		assertEquals(-1, searcher.chop(6, new int[] { 1, 3, 5, 7 }));
		assertEquals(-1, searcher.chop(8, new int[] { 1, 3, 5, 7 }));
	}

}
