package lv.volkovs.kata02.impl;

import java.util.Arrays;

import lv.volkovs.kata02.Searcher;

public class SearcherFunctionalImpl implements Searcher {

	@Override
	public int chop(int target, int[] source) {

		System.out.println(String.format("Searching for %s in %s", target, Arrays.toString(source)));

		// not found
		int size = source.length;
		if (size == 0) {
			return -1;
		}

		int middlePoint = size / 2;
		int middlePointValue = source[middlePoint];

		if (middlePointValue > target) {
			return chop(target, Arrays.copyOfRange(source, 0, middlePoint));
		} else if (middlePointValue < target) {
			int indexInSubarray = chop(target, Arrays.copyOfRange(source, middlePoint + 1, size));
			return indexInSubarray < 0 ? -1 : middlePoint + 1 + indexInSubarray;
		} else {
			return middlePoint;
		}
	}

}
