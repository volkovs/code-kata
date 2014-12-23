package lv.volkovs.kata11;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Lottery {

	private Set<Integer> balls = new TreeSet<Integer>();

	private int[] counts = new int[60];

	public void add(int ball) {
		counts[ball]++;
	}

	public String getBalls() {
		StringBuilder sb = new StringBuilder("[");
		boolean initiated = false;
		for (int i = 0; i < 60; i++) {
			if (counts[i] > 0) {
				if (initiated) {
					sb.append(", ");
				}
				sb.append(i);
				initiated = true;
			}
		}
		return sb.append("]").toString();
	}

	public void add2(int ball) {
		balls.add(ball);
	}

	public String getBalls2() {
		return Arrays.toString(balls.toArray());
	}

}
