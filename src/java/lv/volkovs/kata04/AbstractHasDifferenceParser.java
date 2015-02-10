package lv.volkovs.kata04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractHasDifferenceParser implements HasDifferenceParser {

	@Override
	public final HasDifference parse(String line) {
		Matcher matcher = getPattern().matcher(line);
		if (matcher.matches()) {
			return parse(matcher);
		}
		return null;
	}

	protected abstract Pattern getPattern();

	protected abstract HasDifference parse(Matcher matcher);

}
