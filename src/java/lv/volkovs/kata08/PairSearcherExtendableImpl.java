package lv.volkovs.kata08;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class PairSearcherExtendableImpl implements PairSearcher {

	private static final int WORD_LENGTH_FOR_PAIR = 6;

	private static final String DICTIONARY_FILE_NAME = "wordlist.txt";

	private static final String DICTIONARY_ENCODING = "utf-8";

	private Set<String> dictionary;

	public PairSearcherExtendableImpl() throws IOException {
		dictionary = getDictionary(getWordIterator());
	}

	protected LineIterator getWordIterator() throws IOException {
		return IOUtils.lineIterator(getDictionaryStream(), getDictionaryEncoding());
	}

	protected InputStream getDictionaryStream() {
		return getClass().getResourceAsStream(getDictionaryResourceName());
	}

	protected String getDictionaryResourceName() {
		return DICTIONARY_FILE_NAME;
	}

	protected String getDictionaryEncoding() {
		return DICTIONARY_ENCODING;
	}

	protected Set<String> getDictionary(LineIterator wordIterator) {
		Set<String> dictionary = Sets.newHashSet();
		while (wordIterator.hasNext()) {
			dictionary.add(wordIterator.nextLine());
		}
		return dictionary;
	}

	protected Set<String> getPairCandidatesSource() {
		return Sets.filter(dictionary, getPairCandidatesPredicateOverFullDictionary());
	}

	protected Predicate<String> getPairCandidatesPredicateOverFullDictionary() {
		return new WordLengthPredicate(getWordLengthForPairCandidatesPredicateOverFullDictionary());
	}

	protected int getWordLengthForPairCandidatesPredicateOverFullDictionary() {
		return WORD_LENGTH_FOR_PAIR;
	}

	@Override
	public List<String> search() {
		Set<String> filteredDictionary = getPairCandidatesSource();

		List<String> searchResult = Lists.newArrayList();
		for (String word : filteredDictionary) {
			checkWord(word, searchResult);
		}
		return searchResult;
	}

	protected void checkWord(String word, List<String> searchResult) {
		int wordLength = word.length();
		for (int i = 1; i < wordLength - 1; i++) {
			String firstWord = word.substring(0, i);
			String secondWord = word.substring(i, wordLength);
			checkPair(firstWord, secondWord, searchResult);
		}
	}

	protected void checkPair(String firstWord, String secondWord, List<String> searchResult) {
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
