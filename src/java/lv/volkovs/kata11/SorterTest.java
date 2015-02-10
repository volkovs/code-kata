package lv.volkovs.kata11;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SorterTest {

	@Test
	public void sort() {
		Sorter sorter = new Sorter();
		String sorted = sorter.sort("When not studying nuclear physics, Bambi likes to play beach volleyball.");
		assertEquals("aaaaabbbbcccdeeeeeghhhiiiiklllllllmnnnnooopprsssstttuuvwyyyy", sorted);
	}

}
