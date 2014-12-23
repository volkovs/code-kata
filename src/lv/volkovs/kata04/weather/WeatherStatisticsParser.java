package lv.volkovs.kata04.weather;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lv.volkovs.kata04.AbstractHasDifferenceParser;
import lv.volkovs.kata04.HasDifference;

public class WeatherStatisticsParser extends AbstractHasDifferenceParser {

	private static final Pattern PATTERN = Pattern.compile("\\s+(\\d+)\\s+(\\d+)\\*?\\s+(\\d+).+");

	@Override
	protected Pattern getPattern() {
		return PATTERN;
	}

	@Override
	protected HasDifference parse(Matcher matcher) {
		int dayNumber = Integer.parseInt(matcher.group(1));
		int maxTemperature = Integer.parseInt(matcher.group(2));
		int minTemperature = Integer.parseInt(matcher.group(3));
		return new WeatherStatistics(dayNumber, maxTemperature, minTemperature);
	}

}
