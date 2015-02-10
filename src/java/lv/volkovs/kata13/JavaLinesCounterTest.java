package lv.volkovs.kata13;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class JavaLinesCounterTest {

	@Test
	public void count() throws IOException {
		// assertCount(3, "Resource1.txt");
		assertCount(5, "Resource2.txt");
	}

	private void assertCount(int expectedCount, String javaSource) throws IOException {
		JavaLinesCounter counter = new JavaLinesCounter(javaSource);
		assertEquals(expectedCount, counter.count());
	}

}
