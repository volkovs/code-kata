package lv.volkovs.kata18;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class JDependTest {

	private JDepend jDepend;

	@Before
	public void setUp() {
		jDepend = new JDepend();
	}

	@Test
	public void dependenciesFor() {
		jDepend.add("A", "B C");
		jDepend.add("B", "C E");
		jDepend.add("C", "G");
		jDepend.add("D", "A F");
		jDepend.add("E", "F");
		jDepend.add("F", "H");

		assertEquals("B C E F G H", jDepend.dependenciesFor("A"));
		assertEquals("C E F G H", jDepend.dependenciesFor("B"));
		assertEquals("G", jDepend.dependenciesFor("C"));
		assertEquals("A B C E F G H", jDepend.dependenciesFor("D"));
		assertEquals("F H", jDepend.dependenciesFor("E"));
		assertEquals("H", jDepend.dependenciesFor("F"));
	}

	@Test
	public void cycles() {
		jDepend.add("A", "B");
		jDepend.add("B", "C");
		jDepend.add("C", "A");

		assertEquals("B C", jDepend.dependenciesFor("A"));
		assertEquals("A C", jDepend.dependenciesFor("B"));
		assertEquals("A B", jDepend.dependenciesFor("C"));
	}
}
