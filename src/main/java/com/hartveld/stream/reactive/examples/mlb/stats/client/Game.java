package com.hartveld.stream.reactive.examples.mlb.stats.client;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jdom2.Element;

public class Game {

	private final String id;
	private final String league;
	private final GameStatus status;
	private final String startTime;
	private final BoxScore boxScoreA;
	private final BoxScore boxScoreB;
	private final StartingPitcher winningPitcher;
	private final StartingPitcher losingPitcher;
	private final ClosingPitcher savingPitcher;

	public Game(final Element go_game) {
		final Element game = go_game.getChild("game");
		this.id = game.getAttributeValue("id");
		this.league = game.getAttributeValue("league");
		this.status = GameStatus.valueOf(game.getAttributeValue("status"));
		this.startTime = game.getAttributeValue("start_time");

		final List<Element> teams = go_game.getChildren("team");
		boxScoreA = new BoxScore(teams.get(0));
		boxScoreB = new BoxScore(teams.get(1));

		winningPitcher = new StartingPitcher(go_game.getChild("w_pitcher"));
		losingPitcher = new StartingPitcher(go_game.getChild("l_pitcher"));
		savingPitcher = new ClosingPitcher(go_game.getChild("sv_pitcher"));
	}

	public String getId() {
		return id;
	}

	public String getLeague() {
		return league;
	}

	public GameStatus getStatus() {
		return status;
	}

	public String getStartTime() {
		return startTime;
	}

	public BoxScore getBoxScoreA() {
		return boxScoreA;
	}

	public BoxScore getBoxScoreB() {
		return boxScoreB;
	}

	public StartingPitcher getWinningPitcher() {
		return winningPitcher;
	}

	public StartingPitcher getLosingPitcher() {
		return losingPitcher;
	}

	public ClosingPitcher getSavingPitcher() {
		return savingPitcher;
	}

	@Override
	public String toString() {
		final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append("id", getId());
		builder.append("league", getLeague());
		builder.append("status", getStatus());
		builder.append("startTime", getStartTime());
		builder.append("boxScoreA", getBoxScoreA());
		builder.append("boxScoreB", getBoxScoreB());
		builder.append("winningPitcher", getWinningPitcher());
		builder.append("losingPitcher", getLosingPitcher());
		builder.append("savingPitcher", getSavingPitcher());
		return builder.build();
	}

}
