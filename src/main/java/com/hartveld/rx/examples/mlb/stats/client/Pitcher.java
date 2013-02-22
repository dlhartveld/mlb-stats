package com.hartveld.rx.examples.mlb.stats.client;

import org.jdom2.Element;

public class Pitcher {

	private final String name;

	protected Pitcher(final Element pitcher) {
		this.name = pitcher.getChild("pitcher").getAttributeValue("name");
	}

	public String getName() {
		return name;
	}

}
