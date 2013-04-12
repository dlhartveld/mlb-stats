package com.hartveld.stream.reactive.examples.mlb.stats;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.examples.mlb.stats.client.GameDay;
import com.hartveld.stream.reactive.examples.mlb.stats.client.MLBStatsClient;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetrieveStatsTest {

	private static final Logger LOG = LoggerFactory.getLogger(RetrieveStatsTest.class);

	@Test
	public void test() throws Exception {
		final MLBStatsClient client = new MLBStatsClient();
		final LocalDate date = LocalDate.of(2012, Month.MAY, 10);

		final Observable<GameDay> gameDay = client.retrieve(date);

		final Observable<Game> games = gameDay.flatMap(gd -> gd.getGames().stream());

		games.subscribe(
				g -> LOG.info("Game: {}", g),
				e -> LOG.error("Something went wrong: {}", e.getMessage(), e),
				() -> LOG.info("Done.")
		).close();
	}

}
