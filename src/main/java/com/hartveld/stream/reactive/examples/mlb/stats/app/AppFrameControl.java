package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.hartveld.stream.reactive.concurrency.Schedulers.defaultScheduler;
import static com.hartveld.stream.reactive.concurrency.Schedulers.eventQueueScheduler;

import com.hartveld.stream.reactive.AutoCloseables;
import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.examples.mlb.stats.client.MLBStatsClient;
import com.hartveld.stream.reactive.swing.AbstractFrameControl;
import java.time.LocalDate;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppFrameControl extends AbstractFrameControl {

	private static final Logger LOG = LoggerFactory.getLogger(AppFrameControl.class);

	private final MLBStatsClient client;
	private final AppFrame appFrame;
	private final ReactiveListModel<Game> model;

	public AppFrameControl(final AppFrame appFrame, final ReactiveListModel<Game> model, final MLBStatsClient client) {
		checkNotNull(appFrame, "appFrame");
		checkNotNull(model, "model");
		checkNotNull(client, "client");

		this.appFrame = appFrame;
		this.model = model;
		this.client = client;

		initControl();
	}

	@Override
	public AppFrame frame() {
		return this.appFrame;
	}

	private void initControl() {
		checkState(SwingUtilities.isEventDispatchThread(), "Not on EDT");

		final AutoCloseable subscription1 = appFrame.load().clicks()
				.map(click -> appFrame.dateInput().getText())
				.subscribe(text -> onLoadRequest(text));

		final AutoCloseable subscription2 = appFrame.gameList().selection()
				.filter(event -> !event.getValueIsAdjusting())
				.map(event -> {
					return appFrame.gameList().model().getElementAt(event.getFirstIndex());
				})
				.subscribe(game -> LOG.trace("Selected game: {}", game));

		appFrame.window().closing()
				.subscribe(event -> onWindowClosing(AutoCloseables.composite(subscription1, subscription2)));

		appFrame.setVisible(true);
	}

	private void onLoadRequest(final String text) {
		LOG.trace("Request for date: {}", text);

		prepareForDataRetrieval();

		client.retrieve(LocalDate.parse(text))
				.flatMap(gd -> gd.getGames().stream())
				.observeOn(eventQueueScheduler())
				.subscribeOn(defaultScheduler())
				.subscribe(
						model::onNext,
						this::onError,
						this::finishUpAfterDataRetrieval
				);
	}

	private void prepareForDataRetrieval() {
		appFrame.load().setEnabled(false);
		model.clear();
		appFrame.progressBar().setIndeterminate(true);
	}

	private void finishUpAfterDataRetrieval() {
		appFrame.progressBar().setIndeterminate(false);
		appFrame.pack();
		appFrame.load().setEnabled(true);
	}

	private void onError(final Exception ex) {
		LOG.error("Error: {}", ex.getMessage(), ex);

		finishUpAfterDataRetrieval();
	}

	private void onWindowClosing(final AutoCloseable subscription) {
		LOG.trace("Frame closing ...");

		try {
			subscription.close();
		} catch (Exception ex) {
			LOG.error("Unsubscribe failed: {}", ex.getMessage(), ex);
		}

		final Timer timer = new Timer(1000, event -> {
			LOG.info("Exiting app ...");
			System.exit(0);
		});
		timer.setRepeats(true);

		LOG.info("Waiting a little while for app to spin down ...");
		timer.start();
	}

}
