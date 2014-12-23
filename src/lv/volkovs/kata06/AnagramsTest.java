package lv.volkovs.kata06;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class AnagramsTest {

	@Test
	public void count() throws IOException {
		Anagrams anagrams = new Anagrams("wordlist.txt");

		assertEquals(20683, anagrams.getAnagramSetsCount());
		assertEquals(48162, anagrams.getAnagramWordsCount());
		System.out.println(anagrams.getLongestWords());
		System.out.println(anagrams.getLongestChainWords());
	}

}
