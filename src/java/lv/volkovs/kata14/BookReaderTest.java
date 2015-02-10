package lv.volkovs.kata14;

import java.io.IOException;

import org.junit.Test;

public class BookReaderTest {

	@Test
	public void read() throws IOException {
		BookReader bookReader = new BookReader();
		bookReader.read();
		// System.out.println(bookReader.getNextWords());
		// System.out.println(bookReader.buildSentence("gravity", "man"));
		System.out.println(bookReader.buildSentence());
	}

}
