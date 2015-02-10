package lv.volkovs.kata08;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class PairSearcherReadableImpl implements PairSearcher {

	private WordLengthPredicate wordLengthPredicate;

	private Set<String> dictionary = Sets.newHashSet();

	public PairSearcherReadableImpl(int wordLength) throws IOException {
		LineIterator wordIterator = IOUtils.lineIterator(PairSearcherTest.class.getResourceAsStream("wordlist.txt"), "utf-8");
		wordLengthPredicate = new WordLengthPredicate(wordLength);
		while (wordIterator.hasNext()) {
			dictionary.add(wordIterator.nextLine());
		}
	}

	@Override
	public List<String> search() {
		Set<String> filteredDictionary = Sets.filter(dictionary, wordLengthPredicate);

		List<String> searchResult = Lists.newArrayList();
		for (String word : filteredDictionary) {
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

	private static class WordLengthPredicate implements Predicate<String> {

		private int wordLength;

		public WordLengthPredicate(int wordLength) {
			this.wordLength = wordLength;
		}

		@Override
		public boolean apply(String word) {
			return word.length() == wordLength;
		}

	}

}
