package lv.volkovs.kata04.weather;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class WeatherAnalyzerTest {

	@Test
	public void execute() throws IOException {
		assertEquals("14", new WeatherAnalyzer().execute());
	}

}
