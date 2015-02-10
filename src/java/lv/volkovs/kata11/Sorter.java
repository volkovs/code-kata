package lv.volkovs.kata11;

import java.util.Arrays;

public class Sorter {

	public String sort(String string) {
		char[] input = string.replaceAll("[^a-zA-Z]", "").toLowerCase().toCharArray();

		// sorting
		byte[] letters = new byte[26];
		byte aCode = (byte) 'a';
		for (char c : input) {
			int index = ((byte) c) - aCode;
			letters[index]++;
		}

		// outputting
		StringBuilder sb = new StringBuilder();
		for (int letterNumber = 0; letterNumber < 26; letterNumber++) {
			byte count = letters[letterNumber];
			for (int i = 0; i < count; i++) {
				sb.append((char) (aCode + letterNumber));
			}
		}
		return sb.toString();
	}

	public String sort2(String string) {
		char[] chars = string.replaceAll("[^a-zA-Z]", "").toLowerCase().toCharArray();
		Arrays.sort(chars);
		return String.valueOf(chars);
	}

}
