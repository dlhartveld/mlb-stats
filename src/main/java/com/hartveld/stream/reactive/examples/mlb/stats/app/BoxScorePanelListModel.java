package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;

public class BoxScorePanelListModel extends AbstractListModel<BoxScorePanel> implements ListModel<BoxScorePanel> {

	private final List<Game> games = new ArrayList<>();

	public void addGame(final Game game) {
		this.games.add(game);

		super.fireIntervalAdded(this, this.games.size() - 1, this.games.size());
	}

	public void clear() {
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
