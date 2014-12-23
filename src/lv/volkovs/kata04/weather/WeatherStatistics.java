package lv.volkovs.kata04.weather;

import lv.volkovs.kata04.HasDifference;

public class WeatherStatistics implements HasDifference {

	private int dayNumber;

	private int maxTemperature;

	private int minTemperature;

	public WeatherStatistics(int dayNumber, int maxTemperature, int minTemperature) {
		super();
		this.dayNumber = dayNumber;
		this.maxTemperature = maxTemperature;
		this.minTemperature = minTemperature;
	}

	@Override
	public int getDifference() {
		return maxTemperature - minTemperature;
	}

	@Override
	public String getId() {
		return "" + dayNumber;
	}

}
