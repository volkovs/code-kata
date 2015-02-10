package lv.volkovs.kata05;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.BitSet;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

public class BloomFilter {

	private final byte k;

	private final int n;

	private final int m;

	private BitSet bitmap;

	private MessageDigest[] hashFunctions;

	public BloomFilter() {
		// http://pages.cs.wisc.edu/~cao/papers/summary-cache/node8.html
		k = 14;
		n = 340 * 1000;
		m = 20 * n;
		// p(false positive) = 6.71e-05 (4.84622%)

		// k = 9;
		// n = 340 * 1000;
		// m = 13 * n;
		// p(false positive) = 0.00194 (7.36658%)

		hashFunctions = new MessageDigest[k];
		for (byte i = 0; i < k; i++) {
			hashFunctions[i] = HashFunctionFactory.create();
		}
		bitmap = new BitSet(m);
	}

	public void loadDictionary(String resourceName) throws IOException {
		InputStream resource = BloomFilter.class.getResourceAsStream(resourceName);
		LineIterator lines = IOUtils.lineIterator(resource, Charset.forName("utf-8"));
		while (lines.hasNext()) {
			String line = lines.nextLine();
			loadWord(line);
		}
		lines.close();
	}

	private void loadWord(String word) {
		for (int bitIndex : getHashes(word)) {
			bitmap.set(bitIndex, true);
		}
	}

	public boolean testWord(String word) {
		boolean result = true;
		for (int bitIndex : getHashes(word)) {
			result &= bitmap.get(bitIndex);
		}
		return result;
	}

	protected int[] getHashes(String word) {
		int[] result = new int[k];
		for (byte i = 0; i < k; i++) {
			MessageDigest md = hashFunctions[i];
			md.update(word.getBytes());
			byte[] digest = md.digest();
			result[i] = Math.abs(Arrays.hashCode(digest) % m);
		}
		return result;
	}

}
