package com.hartveld.rx.examples.mlb.stats.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

public class GameDay {

	private final List<Game> games = new ArrayList<>();

	public GameDay(final Document document, final LocalDate date) {
		final Element scoreboard = XmlUtils.findSingleElementInDocumentByXPath(document, "//scoreboard");
		final Attribute scoreboardDateAttr = scoreboard.getAttribute("date");
		final String scoreboardDateString = scoreboardDateAttr.getValue();
		final LocalDate scoreboardDate = DateTimeFormatters.basicIsoDate().parse(scoreboardDateString, LocalDate::from);

		if (!scoreboardDate.equals(date)) {
			throw new RuntimeException("Retrieved data does not correspond with expected date");
		}

		scoreboard.getChildren("go_game").forEach(game -> games.add(new Game(game)));
	}

	public List<Game> getGames() {
		return Collections.unmodifiableList(games);
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("games", games);
		return builder.build();
	}

}
