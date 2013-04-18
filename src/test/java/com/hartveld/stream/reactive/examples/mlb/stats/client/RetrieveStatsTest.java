package com.hartveld.stream.reactive.examples.mlb.stats.client;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetrieveStatsTest {

	private static final Logger LOG = LoggerFactory.getLogger(RetrieveStatsTest.class);

	@Test
	public void test() throws Exception {
		final MLBStatsClient client = new MLBStatsClient();
		final LocalDate date = LocalDate.of(2012, Month.MAY, 10);

		final List<Game> games = client.retrieve(date)
				.flatMap(gd -> gd.getGames().stream())
				.collect(toList());

		LOG.info("Games: {}", games);
	}

}
