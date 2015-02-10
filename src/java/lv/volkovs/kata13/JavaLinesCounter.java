package lv.volkovs.kata13;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

public class JavaLinesCounter {

	private static final Pattern COMMENTS_PATTERN = Pattern.compile("(.*)//.*");

	private static final Pattern COMMENTS_START = Pattern.compile("(.*)/\\*(.*)");

	private static final Pattern COMMENTS_END = Pattern.compile(".*\\*/(.*)");

	private static final Pattern JAVA_PATTERN = Pattern.compile(".*[^\\s].*");

	private LineIterator lines;

	private boolean commentsContext = false;

	public JavaLinesCounter(String resource) throws IOException {
		InputStream stream = JavaLinesCounter.class.getResourceAsStream(resource);
		lines = IOUtils.lineIterator(stream, Charset.defaultCharset());
	}

	public int count() {
		int count = 0;

		while (lines.hasNext()) {
			String line = lines.next();
			line = stripCommentsIfAny(line);
			if (isJava(line)) {
				System.out.println("Java: '" + line + "'");
				count++;
			}
		}
		return count;
	}

	private String stripCommentsIfAny(String line) {
		Matcher commentsMatcher = COMMENTS_PATTERN.matcher(line);
		Matcher commentsStarted = COMMENTS_START.matcher(line);
		Matcher commentsEnded = COMMENTS_END.matcher(line);

		// multiple line comments ended
		if (commentsContext && commentsEnded.matches()) {
			String code = commentsEnded.group(1);
			commentsContext = false;
			return stripCommentsIfAny(code);
		}

		// we are in multiple line comments - no code
		if (commentsContext) {
			return "";
		}

		// multiple lines comments started
		if (commentsStarted.matches()) {
			String code = commentsStarted.group(1);

			// hack - use String context instead
			if (!code.contains("\"")) {
				String comments = commentsStarted.group(2);
				commentsContext = true;
				return code + stripCommentsIfAny(comments);
			}
			return code;
		}

		// regular comments
		if (commentsMatcher.matches()) {
			String code = commentsMatcher.group(1);
			return code;
		}

		return line;
	}

	private boolean isJava(String line) {
		return JAVA_PATTERN.matcher(line).matches();
	}

}
