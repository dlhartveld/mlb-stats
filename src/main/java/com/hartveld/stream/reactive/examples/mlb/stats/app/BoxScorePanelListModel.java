package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.swing.DefaultReactiveListModel;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxScorePanelListModel extends DefaultReactiveListModel<BoxScorePanel> {

	private static final Logger LOG = LoggerFactory.getLogger(BoxScorePanelListModel.class);

	private final List<Game> games = new ArrayList<>();

	public void addGame(final Game game) {
		LOG.info("Adding game: {}", game);

		checkNotNull(game, "game");

		this.games.add(game);

		super.fireIntervalAdded(this, this.games.size() - 1, this.games.size());
	}

	@Override
	public BoxScorePanel getElementAt(int index) {
		return new BoxScorePanel(this.games.get(index));
	}

}
