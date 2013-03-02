package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.concurrency.Schedulers;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.examples.mlb.stats.client.GameDay;
import com.hartveld.stream.reactive.examples.mlb.stats.client.MLBStatsClient;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatsApp {

	private static final Logger LOG = LoggerFactory.getLogger(StatsApp.class);

	private final AppFrame appFrame = new AppFrame();
	private final MLBStatsClient client = new MLBStatsClient();

	public void run() {
		try {
			SwingUtilities.invokeAndWait(() -> {
				appFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

				final AutoCloseable subscription = appFrame.loadRequests.subscribe(s -> onLoadRequest(s, appFrame));
				appFrame.events.window.closing.subscribe(e -> onWindowClosing(e, subscription));

				appFrame.setVisible(true);
			});
		} catch (InterruptedException ex) {
			LOG.error("Interrupted while setting up app: {}", ex.getMessage(), ex);
		} catch (InvocationTargetException ex) {
			LOG.error("Exception while setting up app: {}", ex.getMessage(), ex);
		}

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
				.subscribe(
					el -> {
						LOG.info("Got: {}", el);
						appFrame.addGame(el);
					},
					ex -> {
						LOG.error("Error: {}", ex.getMessage(), ex);
						finishUpAfterDataRetrieval();
					},
					() -> {
						finishUpAfterDataRetrieval();
					}
				);
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

	public static void main(String [] args) {

		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			LOG.warn("Failed to load class: {}", ex.getMessage(), ex);
		} catch (InstantiationException ex) {
			LOG.warn("Failed to instantiate class: {}", ex.getMessage(), ex);
		} catch (IllegalAccessException ex) {
			LOG.warn("Class or initializer not available: {}", ex.getMessage(), ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			LOG.warn("Look and feel is not supported: {}", ex.getMessage(), ex);
		}
		//</editor-fold>

		StatsApp app = new StatsApp();
		app.run();
	}

	private void prepareForDataRetrieval() {
		appFrame.disableLoadButton();
		appFrame.clearGames();
		appFrame.startProgressBar();
	}

	private void finishUpAfterDataRetrieval() {
		appFrame.stopProgressBar();
		appFrame.pack();
		appFrame.enableLoadButton();
	}

}
