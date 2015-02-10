package lv.volkovs.kata05;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class BloomFilterTest {

	private BloomFilter bloomFilter;

	@Before
	public void setUp() {
		bloomFilter = new BloomFilter();
	}

	@Test
	public void loadDictionary() throws IOException {
		bloomFilter.loadDictionary("wordlist.txt");
		assertTrue(bloomFilter.testWord("word"));
		assertTrue(bloomFilter.testWord("test"));
		assertFalse(bloomFilter.testWord("wordzzz"));
		assertFalse(bloomFilter.testWord("testzzz"));
		assertFalse(bloomFilter.testWord("wordz"));
		assertFalse(bloomFilter.testWord("testz"));
	}

	@Test
	public void probability() throws IOException {
		bloomFilter.loadDictionary("wordlist.txt");
		float falsePositive = 0f;
		int experimentSize = 1000 * 1000 * 10;
		for (int i = 0; i < experimentSize; i++) {
			if (bloomFilter.testWord(UUID.randomUUID().toString().substring(1, 8))) {
				falsePositive++;
			}
		}
		System.out.println("False positive rate " + falsePositive * 100 / experimentSize + "%");
	}

}
