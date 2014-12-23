package lv.volkovs.kata04.football;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lv.volkovs.kata04.AbstractHasDifferenceParser;
import lv.volkovs.kata04.HasDifference;

public class FootballStatisticsParser extends AbstractHasDifferenceParser {

	private static final Pattern PATTERN = Pattern.compile("\\s+\\d+\\. ([^ ]+)\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+(\\d+)\\s+-\\s+(\\d+).+");

	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected HasDifference parse(Matcher matcher) {
		String team = matcher.group(1);
		int forGoals = Integer.parseInt(matcher.group(2));
		int againstGoals = Integer.parseInt(matcher.group(3));
		return new FootballStatistics(team, forGoals, againstGoals);
	}

}
