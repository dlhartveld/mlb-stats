package com.hartveld.stream.reactive.examples.mlb.stats.client;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jdom2.Element;

public class StartingPitcher extends Pitcher {

	private final int wins;
	private final int losses;

	public StartingPitcher(final Element pitcher) {
		super(pitcher);
		this.wins = Integer.parseInt(pitcher.getAttributeValue("wins"));
		this.losses = Integer.parseInt(pitcher.getAttributeValue("losses"));
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("name", getName());
		builder.append("wins", getWins());
		builder.append("losses", getLosses());
		return builder.build();
	}

}
