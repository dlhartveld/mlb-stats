package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxScorePanelListModel extends AbstractListModel<BoxScorePanel> implements ListModel<BoxScorePanel> {

	private static final Logger LOG = LoggerFactory.getLogger(BoxScorePanelListModel.class);

	private final List<Game> games;

	public BoxScorePanelListModel() {
		this.games = new ArrayList<>();
	}

	public void addGame(final Game game) {
		LOG.info("Adding game: {}", game);

		checkNotNull(game, "game");

		this.games.add(game);

		super.fireIntervalAdded(this, this.games.size() - 1, this.games.size());
	}

	public void clear() {
		LOG.info("Clearing games list ...");

		int previousSize = this.games.size();

		this.games.clear();

		super.fireIntervalRemoved(this, 0, previousSize);
	}

	@Override
	public int getSize() {
		return this.games.size();
	}

	@Override
	public BoxScorePanel getElementAt(int index) {
		return new BoxScorePanel(this.games.get(index));
	}

}
