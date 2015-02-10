package lv.volkovs.kata19;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class WordChainer {

	private HashSet<String> words = Sets.newHashSet();

	private Map<Integer, Set<String>> wordsByLength = Maps.newHashMap();

	public WordChainer() {
		Scanner scanner = new Scanner(WordChainer.class.getResourceAsStream("wordlist.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			words.add(line);
			int size = line.length();
			Set<String> bucket = wordsByLength.get(size);
			if (bucket == null) {
				bucket = Sets.newHashSet();
				wordsByLength.put(size, bucket);
			}
			bucket.add(line);
		}
		scanner.close();
	}

	public List<String> chain(String from, String to) {
		Stack<String> stack = new Stack<String>();
		chainInternal(from, to, stack);
		return Lists.newArrayList(stack.iterator());
	}

	public void chainInternal(String from, String to, Stack<String> stack) {
		Preconditions.checkArgument(from.length() == to.length());
		stack.push(from);
		if (from.equals(to)) {
			// we have found target
			return;
		}

		List<String> similarWords = findSimilarTo(from);
		Collections.sort(similarWords, new TargetWordComparator(to));
		System.out.println(String.format("Searching path from '%s' to '%s' (Stack: %s) (Similar: %s)", from, to, stack, similarWords));
		for (String similarWord : similarWords) {
			chainInternal(similarWord, to, stack);
			if (stack.peek().equals(to)) {
				// we have found target
				return;
			}
		}
		stack.pop();
	}

	public List<String> findSimilarTo(String word) {
		List<String> similarWords = Lists.newArrayList();
		for (String potentiallySimilarWord : wordsByLength.get(word.length())) {
			if (isSimilar(potentiallySimilarWord, word)) {
				similarWords.add(potentiallySimilarWord);
			}
		}
		return similarWords;
	}

	private boolean isSimilar(String word1, String word2) {
		if (word1.equals(word2)) {
			return false;
		}
		int nonEqualSymbolsCount = getDifference(word1, word2);
		if (nonEqualSymbolsCount > 1) {
			return false;
		}
		return true;
	}

	private static int getDifference(String word1, String word2) {
		int nonEqualSymbolsCount = 0;
		char[] word1Chars = word1.toCharArray();
		char[] word2Chars = word2.toCharArray();
		for (int i = 0; i < word1Chars.length; i++) {
			if (word1Chars[i] != word2Chars[i]) {
				nonEqualSymbolsCount++;
			}
		}
		return nonEqualSymbolsCount;
	}

	public static class TargetWordComparator implements Comparator<String> {

		private String targetWord;

		public TargetWordComparator(String targetWord) {
			this.targetWord = targetWord;
		}

		@Override
		public int compare(String word1, String word2) {
			return getDifference(targetWord, word1) - getDifference(targetWord, word2);
		}

	}

	private static class Tree {

		private String value;

		private String parent;

		private List<String> childrenValues;

		private List<Tree> children;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getParent() {
			return parent;
		}

		public void setParent(String parent) {
			this.parent = parent;
		}

		public List<String> getChildrenValues() {
			return childrenValues;
		}

		public void setChildrenValues(List<String> childrenValues) {
			this.childrenValues = childrenValues;
		}

		public List<Tree> getChildren() {
			return children;
		}

		public void setChildren(List<Tree> children) {
			this.children = children;
		}

	}

}
