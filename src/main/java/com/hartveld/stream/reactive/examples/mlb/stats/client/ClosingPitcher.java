package com.hartveld.stream.reactive.examples.mlb.stats.client;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jdom2.Element;

public class ClosingPitcher extends Pitcher {

	private final int saves;

	public ClosingPitcher(final Element pitcher) {
		super(pitcher);

		this.saves = Integer.parseInt(pitcher.getAttributeValue("saves"));
	}

	public int getSaves() {
		return saves;
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("name", getName());
		builder.append("saves", getSaves());
		return builder.build();
	}

}
