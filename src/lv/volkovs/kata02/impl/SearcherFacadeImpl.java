package lv.volkovs.kata02.impl;

import java.util.Arrays;

import lv.volkovs.kata02.Searcher;

public class SearcherFacadeImpl implements Searcher {

	@Override
	public int chop(int target, int[] source) {
		int foundIndex = Arrays.binarySearch(source, target);
		return foundIndex < 0 ? -1 : foundIndex;
	}
}
