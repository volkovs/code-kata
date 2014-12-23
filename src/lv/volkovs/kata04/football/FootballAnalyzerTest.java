package lv.volkovs.kata04.football;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class FootballAnalyzerTest {

	@Test
	public void execute() throws IOException {
		assertEquals("Aston_Villa", new FootballAnalyzer().execute());
	}

}
