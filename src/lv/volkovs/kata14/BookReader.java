package lv.volkovs.kata14;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class BookReader {

	private static final int SENTENCE_SIZE = 5;

	private static final int START_LINE = 56;

	private static final int END_LINE = 3612;

	private String key1;

	private String key2;

	private String lastWord;

	private Map<String, List<String>> nextWords = Maps.newHashMap();

	private Set<String> starters = Sets.newHashSet();

	private Set<String> terminators = Sets.newHashSet();

	public void read() throws IOException {
		InputStream input = BookReader.class.getResourceAsStream("astronomy.txt");
		LineIterator lines = IOUtils.lineIterator(input, Charset.defaultCharset());
		int lineNumber = 1;
		while (lines.hasNext()) {
			String line = lines.nextLine();
			if (lineNumberInRange(lineNumber)) {
				readLine(line);
			}
			lineNumber++;
		}
	}

	private boolean lineNumberInRange(int lineNumber) {
		return lineNumber > START_LINE && lineNumber < END_LINE;
	}

	private void readLine(String line) {
		String[] words = line.split("[^a-zA-Z.]+");
		String previousWord = "";
		boolean sentenceTerminated = false;
		for (String word : words) {
			if (".".equals(word)) {
				terminators.add(previousWord);
				sentenceTerminated = true;
			} else {
				word = normalize(word);
				if (!StringUtils.isEmpty(word)) {
					if (sentenceTerminated) {
						sentenceTerminated = false;
						starters.add(word);
					}
					readWord(word);
					previousWord = word;
				}
			}
		}
	}

	private String normalize(String word) {
		return word.replaceAll("[^a-zA-Z]+", "").trim().toLowerCase();
	}

	// track of words in the beginning of sentense - cut them from regular usage in the sentence.
	private void readWord(String word) {
		key1 = key2;
		key2 = lastWord;
		lastWord = word;
		if (key1 == null) {
			return;
		}

		String key = key1 + " " + key2;
		List<String> followers = nextWords.get(key);
		if (followers == null) {
			followers = Lists.newArrayList();
			nextWords.put(key, followers);
		}

		followers.add(word);
	}

	public Map<String, List<String>> getNextWords() {
		return nextWords;
	}

	public String buildSentence() {
		String key = getRandom(nextWords.keySet());
		String[] seeds = key.split(" ");
		while (!starters.contains(seeds[0])) {
			key = getRandom(nextWords.keySet());
			seeds = key.split(" ");
		}
		return buildSentence(seeds[0], seeds[1]);
	}

	private String getRandom(Set<String> myHashSet) {
		int size = myHashSet.size();
		int item = new Random().nextInt(size);
		int i = 0;
		for (String obj : myHashSet) {
			if (i == item) {
				return obj;
			}
			i = i + 1;
		}
		return null;
	}

	public String buildSentence(String seed1, String seed2) {
		String key = seed1 + " " + seed2;
		StringBuilder sb = new StringBuilder(key);
		String nextWord = nextWord(key);
		int wordCount = 0;
		while ((nextWord != null && wordCount++ < SENTENCE_SIZE) || (nextWord != null && wordCount++ >= SENTENCE_SIZE && !terminators.contains(nextWord))) {
			sb.append(" ").append(nextWord);
			seed1 = seed2;
			seed2 = nextWord;
			nextWord = nextWord(seed1 + " " + seed2);
		}
		sb.append(".");
		String sentence = sb.toString();
		String firstSentenceLetter = String.valueOf(sentence.charAt(0)).toUpperCase();
		return sentence.replaceFirst(".", firstSentenceLetter);
	}

	public String nextWord(String key) {
		List<String> followers = nextWords.get(key);
		if (followers != null) {
			return followers.get(new Random(System.currentTimeMillis()).nextInt(followers.size()));
		}
		return null;
	}

}
