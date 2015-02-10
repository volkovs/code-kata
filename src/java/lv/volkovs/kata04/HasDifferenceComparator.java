package lv.volkovs.kata04;

import java.util.Comparator;

public class HasDifferenceComparator implements Comparator<HasDifference> {

	@Override
	public int compare(HasDifference statistics1, HasDifference statistics2) {
		return statistics1.getDifference() - statistics2.getDifference();
	}

}
