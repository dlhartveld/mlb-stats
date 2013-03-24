package com.hartveld.stream.reactive.examples.mlb.stats.app;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StatsApp {

	private static final Logger LOG = LoggerFactory.getLogger(StatsApp.class);

	public static void main(String [] args) {
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
			SwingUtilities.invokeAndWait(() -> {
				final BoxScorePanelListModel boxScorePanelListModel = new BoxScorePanelListModel();
				final AppFrame appFrame = new AppFrame(boxScorePanelListModel);

				final AppFrameControl appFrameControl = new AppFrameControl(appFrame, boxScorePanelListModel);
				appFrameControl.showGUI();
			});
		} catch (InterruptedException ex) {
			LOG.error("Interrupted while setting up app: {}", ex.getMessage(), ex);
		} catch (InvocationTargetException ex) {
			LOG.error("Exception while setting up app: {}", ex.getMessage(), ex);
		}
	}

}
