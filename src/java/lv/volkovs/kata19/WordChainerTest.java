package lv.volkovs.kata19;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class WordChainerTest {

	private WordChainer wordChainer;

	@Before
	public void setUp() {
		wordChainer = new WordChainer();
	}

	@Test
	public void chain() {
		// System.out.println(wordChainer.findSimilarTo("cat"));
		System.out.println(wordChainer.chain("cat", "dog"));
		System.out.println(wordChainer.chain("lead", "gold"));
		System.out.println(wordChainer.chain("ruby", "code"));
		assertEquals(Arrays.asList("cat", "cot", "cog", "dog"), wordChainer.chain("cat", "dog"));
		assertEquals(Arrays.asList("lead", "load", "goad", "gold"), wordChainer.chain("lead", "gold"));
		assertEquals(Arrays.asList("gold", "goad", "load", "lead"), wordChainer.chain("gold", "lead"));
		assertEquals(Arrays.asList("ruby", "rubs", "robs", "rods", "rode", "code"), wordChainer.chain("ruby", "code"));
		assertEquals(Arrays.asList("code", "rode", "rods", "robs", "rubs", "ruby"), wordChainer.chain("code", "ruby"));
	}

	@Test
	public void comparator() {
		List<String> words = Arrays.asList("code", "rods", "rode", "rubs", "robs", "ruby");
		Collections.sort(words, new WordChainer.TargetWordComparator("ruby"));
		assertEquals(Arrays.asList("ruby", "rubs", "robs", "rods", "rode", "code"), words);

	}
}
