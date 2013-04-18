package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.examples.mlb.stats.client.MLBStatsClient;
import com.hartveld.stream.reactive.swing.DefaultReactiveListModel;
import java.awt.EventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatsApp {

	private static final Logger LOG = LoggerFactory.getLogger(StatsApp.class);

	private final MLBStatsClient service;
	private final ReactiveListModel<Game> model;

	private AppFrameControl appFrameControl;

	StatsApp() throws Exception {
		service = new MLBStatsClient();
		model = new DefaultReactiveListModel<>();

		EventQueue.invokeAndWait(() -> {
			appFrameControl = new AppFrameControl(model, service);
		});
	}

	void run() throws Exception {
		appFrameControl.showFrame();
	}

	public static void main(String[] args) throws Exception {
		LOG.info("Starting ...");

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

		try {
			StatsApp app = new StatsApp();
			app.run();
		} catch (Exception e) {
			LOG.error("Something went terribly wrong: {}", e.getMessage(), e);
			LOG.error("Exiting app ...");
			System.exit(1);
		}
	}

}
