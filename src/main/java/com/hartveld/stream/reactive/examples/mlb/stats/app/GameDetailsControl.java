package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GameDetailsControl {

	private static final Logger LOG = LoggerFactory.getLogger(GameDetailsControl.class);

	void show(Game game) {
		LOG.trace("Showing game: {}", game);

		
	}

}
