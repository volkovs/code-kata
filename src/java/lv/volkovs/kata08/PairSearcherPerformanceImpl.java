package lv.volkovs.kata08;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class PairSearcherPerformanceImpl implements PairSearcher {

	private Set<String> dictionary = Sets.newHashSet();

	private List<String> fixedLengthWordDictionary = Lists.newArrayList();

	public PairSearcherPerformanceImpl(int wordLength) throws IOException {
		LineIterator wordIterator = IOUtils.lineIterator(PairSearcherTest.class.getResourceAsStream("wordlist.txt"), "utf-8");
		while (wordIterator.hasNext()) {
			String word = wordIterator.nextLine();
			int currentWordLength = word.length();
			if (currentWordLength < wordLength) {
				dictionary.add(word);
			} else if (currentWordLength == wordLength) {
				fixedLengthWordDictionary.add(word);
			}
		}
	}

	@Override
	public List<String> search() {
		List<String> searchResult = Lists.newArrayList();
		for (String word : fixedLengthWordDictionary) {
			checkWord(word, searchResult);
		}
		return searchResult;
	}

	private void checkWord(String word, List<String> searchResult) {
		int wordLength = word.length();
		for (int i = 1; i < wordLength - 1; i++) {
			String firstWord = word.substring(0, i);
			String secondWord = word.substring(i, wordLength);
			checkPair(firstWord, secondWord, searchResult);
		}
	}

	private void checkPair(String firstWord, String secondWord, List<String> searchResult) {
		if (dictionary.contains(firstWord) && dictionary.contains(secondWord)) {
			searchResult.add(String.format("%s + %s => %s", firstWord, secondWord, firstWord + secondWord));
		}
	}

}
