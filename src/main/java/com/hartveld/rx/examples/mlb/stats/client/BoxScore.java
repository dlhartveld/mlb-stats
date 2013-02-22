package com.hartveld.rx.examples.mlb.stats.client;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jdom2.Element;

public class BoxScore {

	private final Team team;
	private final int runs;
	private final int hits;
	private final int errors;

	public BoxScore(final Element team) {
		this.team = new Team(team.getAttributeValue("name"));

		final Element gameteam = team.getChild("gameteam");
		runs = Integer.parseInt(gameteam.getAttributeValue("R"));
		hits = Integer.parseInt(gameteam.getAttributeValue("H"));
		errors = Integer.parseInt(gameteam.getAttributeValue("E"));
	}

	public Team getTeam() {
		return team;
	}

	public int getRuns() {
		return runs;
	}

	public int getHits() {
		return hits;
	}

	public int getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("team", getTeam());
		builder.append("runs", getRuns());
		builder.append("hits", getHits());
		builder.append("errors", getErrors());
		return builder.build();
	}

}
