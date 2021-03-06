package com.hartveld.stream.reactive.examples.mlb.stats.client;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.ObservableFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MLBStatsClient {

	private static final Logger LOG = LoggerFactory.getLogger(MLBStatsClient.class);
	private final SAXBuilder builder = new SAXBuilder();

	public Observable<GameDay> retrieve(final LocalDate date) {
		LOG.debug("Retrieving GameDay data for date: {}", date);

		return ObservableFactory.observableOfTask(() -> {
			LOG.trace("Retrieving document for date: {}", date);
			return retrieveDocument(date);
		}).map(doc -> new GameDay(doc, date));
	}

	private Document retrieveDocument(final LocalDate date) throws JDOMException, IOException {
		LOG.debug("Retrieving data for date: {}", date);

		checkNotNull(date, "date");

		return builder.build(urlForDate(date));
	}

	private URL urlForDate(final LocalDate date) {
		final String result = String.format("http://gd2.mlb.com/components/game/mlb/year_%d/month_%02d/day_%02d/scoreboard.xml",
				date.getYear(), date.getMonthValue(), date.getDayOfMonth());

		LOG.trace("URL: {}", result);

		try {
			return new URL(result);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
