package lv.volkovs.kata04.football;

import lv.volkovs.kata04.HasDifference;

public class FootballStatistics implements HasDifference {

	private String team;

	private int forGoals;

	private int againstGoals;

	public FootballStatistics(String team, int forGoals, int againstGoals) {
		this.team = team;
		this.forGoals = forGoals;
		this.againstGoals = againstGoals;
	}

	@Override
	public int getDifference() {
		return Math.abs(forGoals - againstGoals);
	}

	@Override
	public String getId() {
		return team;
	}

}
