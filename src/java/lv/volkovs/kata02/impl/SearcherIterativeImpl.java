package lv.volkovs.kata02.impl;

import java.util.Arrays;

import lv.volkovs.kata02.Searcher;

public class SearcherIterativeImpl implements Searcher {

	@Override
	public int chop(int target, int[] source) {
		int startIndex = 0;
		int endIndex = source.length - 1;
		System.out.println(String.format("Looking for %s in array %s", target, Arrays.toString(source)));

		while (startIndex <= endIndex) {
			int midPoint = (endIndex - startIndex) / 2 + startIndex;
			int midPointValue = source[midPoint];
			System.out.println(String.format("Looking at range [%s, %s]. Comparing value at %s", startIndex, endIndex, midPoint));

			if (midPointValue < target) {
				startIndex = midPoint + 1;
			} else if (midPointValue > target) {
				endIndex = midPoint - 1;
			} else {
				return midPoint;
			}
		}
		System.out.println();

		return -1;
	}

}
