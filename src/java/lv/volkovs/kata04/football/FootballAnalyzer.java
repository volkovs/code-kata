package lv.volkovs.kata04.football;

import lv.volkovs.kata04.AbstractAnalyzer;
import lv.volkovs.kata04.HasDifferenceParser;

public class FootballAnalyzer extends AbstractAnalyzer {

	@Override
	protected String getResourceName() {
		return "football.dat";
	}

	@Override
	protected HasDifferenceParser getParser() {
		return new FootballStatisticsParser();
	}

}
