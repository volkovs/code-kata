package lv.volkovs.kata04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;

public abstract class AbstractAnalyzer {

	public final String execute() throws IOException {
		List<HasDifference> statistics = parseLines();
		Collections.sort(statistics, new HasDifferenceComparator());
		return statistics.iterator().next().getId();
	}

	private List<HasDifference> parseLines() throws IOException {
		List<HasDifference> statistics = new ArrayList<HasDifference>();
		List<String> lines = IOUtils.readLines(getClass().getResourceAsStream(getResourceName()));
		for (String line : lines) {
			HasDifference hasDifference = getParser().parse(line);
			if (hasDifference != null) {
				statistics.add(hasDifference);
			}
		}
		return statistics;
	}

	protected abstract String getResourceName();

	protected abstract HasDifferenceParser getParser();

}
