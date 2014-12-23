package lv.volkovs.kata06;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Anagrams {

	private Map<String, List<String>> anagramCandidates = Maps.newHashMap();

	private List<List<String>> anagrams = Lists.newArrayList();

	private List<String> longestChainAnagram = Lists.newArrayList();

	private List<String> longestWordAnagram = Lists.newArrayList("");

	private int anagramSetsCount = 0;

	private int anagramWordsCount = 0;

	public Anagrams(String resource) throws IOException {
		LineIterator lineIterator = IOUtils.lineIterator(Anagrams.class.getResourceAsStream(resource), "utf-8");
		while (lineIterator.hasNext()) {
			String word = lineIterator.nextLine();
			word = normalize(word);
			String key = getKey(word);
			List<String> anagramList = anagramCandidates.get(key);
			if (anagramList == null) {
				anagramList = Lists.newArrayList();
				anagramCandidates.put(key, anagramList);
			}
			anagramList.add(word);
		}

		// filtering only anagrams
		for (List<String> anagramList : anagramCandidates.values()) {
			if (anagramList.size() > 1) {
				addAnagrams(anagramList);
			}
		}
	}

	private void addAnagrams(List<String> anagramList) {
		anagrams.add(anagramList);

		if (anagramList.size() > longestChainAnagram.size()) {
			longestChainAnagram = anagramList;
		}

		if (anagramList.iterator().next().length() > longestWordAnagram.iterator().next().length()) {
			longestWordAnagram = anagramList;
		}

		anagramSetsCount++;
		anagramWordsCount += anagramList.size();

	}

	private String getKey(String word) {
		char[] key = word.toCharArray();
		Arrays.sort(key);
		return String.valueOf(key);
	}

	private String normalize(String word) {
		return word;
	}

	public int getAnagramSetsCount() {
		return anagramSetsCount;
	}

	public int getAnagramWordsCount() {
		return anagramWordsCount;
	}

	public List<String> getLongestWords() {
		return longestWordAnagram;
	}

	public List<String> getLongestChainWords() {
		return longestChainAnagram;
	}

}
