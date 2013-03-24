package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import com.hartveld.stream.reactive.concurrency.Schedulers;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.examples.mlb.stats.client.GameDay;
import com.hartveld.stream.reactive.examples.mlb.stats.client.MLBStatsClient;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppFrameControl {

	private static final Logger LOG = LoggerFactory.getLogger(AppFrameControl.class);

	private final MLBStatsClient client;
	private final AppFrame appFrame;
	private final BoxScorePanelListModel boxScorePanelListModel;

	public AppFrameControl(final AppFrame appFrame, final BoxScorePanelListModel boxScorePanelListModel) {
		checkNotNull(appFrame, "appFrame");
		checkNotNull(boxScorePanelListModel, "boxScorePanelListModel");

		this.appFrame = appFrame;
		this.boxScorePanelListModel = boxScorePanelListModel;

		this.client = new MLBStatsClient();

		initControl();
	}

	private void initControl() {
		checkState(SwingUtilities.isEventDispatchThread(), "Not on EDT");

		final AutoCloseable subscription = appFrame.loadRequests.subscribe(s -> onLoadRequest(s, appFrame));
		appFrame.window.closing.subscribe(e -> onWindowClosing(e, subscription));

		appFrame.setVisible(true);
	}

	public void showGUI() {
		appFrame.setVisible(true);
	}

	private void onLoadRequest(final String s, final AppFrame appFrame) {
		LOG.trace("Request for date: {}", s);

		prepareForDataRetrieval();

		client.retrieve(LocalDate.parse(s))
				.flatMap((GameDay gd, Consumer<Game> sink) -> {
					gd.getGames().forEach(sink);
				})
				.observeOn(Schedulers.EDT)
				.subscribeOn(Schedulers.DEFAULT)
				.subscribe(boxScorePanelListModel::addGame, this::onError, this::finishUpAfterDataRetrieval);
	}

	private void prepareForDataRetrieval() {
		appFrame.disableLoadButton();
		boxScorePanelListModel.clear();
		appFrame.startProgressBar();
	}

	private void finishUpAfterDataRetrieval() {
		appFrame.stopProgressBar();
		appFrame.pack();
		appFrame.enableLoadButton();
	}

	private void onError(final Exception ex) {
		LOG.error("Error: {}", ex.getMessage(), ex);

		finishUpAfterDataRetrieval();
	}

	private void onWindowClosing(final WindowEvent e, final AutoCloseable subscription) {
		try {
			subscription.close();
		} catch (Exception ex) {
			LOG.error("Unsubscribe failed: {}", ex.getMessage(), ex);
		}

		final Timer timer = new Timer(1000, ev -> {
			LOG.info("Exiting app ...");
			System.exit(0);
		});
		timer.setRepeats(true);

		LOG.info("Waiting a little while for app to spin down ...");
		timer.start();
	}

}
