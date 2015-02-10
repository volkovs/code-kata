package lv.volkovs.kata02.impl;

import java.util.Arrays;

import lv.volkovs.kata02.Searcher;

public class SearcherRecursiveImpl implements Searcher {

	@Override
	public int chop(int target, int[] source) {
		System.out.println();
		return chop(target, source, 0, source.length - 1);
	}

	private int chop(int target, int[] source, int from, int to) {

		System.out.println(String.format("Searching for %s in %s[%s, %s]", target, Arrays.toString(source), from, to));

		// not found
		if (to < from) {
			return -1;
		}

		int middlePoint = from + (to - from) / 2;
		int middlePointValue = source[middlePoint];

		if (target > middlePointValue) {
			return chop(target, source, middlePoint + 1, to);
		} else if (target < middlePointValue) {
			return chop(target, source, from, middlePoint - 1);
		} else {
			return middlePoint;
		}
	}

}
