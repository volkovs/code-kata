package lv.volkovs.kata04.weather;

import lv.volkovs.kata04.AbstractAnalyzer;
import lv.volkovs.kata04.HasDifferenceParser;

public class WeatherAnalyzer extends AbstractAnalyzer {

	@Override
	protected String getResourceName() {
		return "weather.dat";
	}

	@Override
	protected HasDifferenceParser getParser() {
		return new WeatherStatisticsParser();
	}

}
