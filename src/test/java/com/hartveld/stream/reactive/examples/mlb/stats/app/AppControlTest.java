package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.hartveld.stream.reactive.examples.mlb.stats.client.MLBStatsClient;
import com.hartveld.stream.reactive.swing.ReactiveButton;
import java.awt.Component;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppControlTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppControlTest.class);

	private MLBStatsClient mlbStatsClient;

	private AppFrame appFrame;
	private AppControl control;
	private BoxScorePanelListModel boxScorePanelListModel;

	private JFormattedTextField dateField;
	private ReactiveButton button;

	@Before
	public void setUp() throws Exception {
		mlbStatsClient = new MLBStatsClient();
		boxScorePanelListModel = new BoxScorePanelListModel();

		SwingUtilities.invokeAndWait(() -> {
			appFrame = new AppFrame(boxScorePanelListModel);
			control = new AppControl(appFrame, boxScorePanelListModel, mlbStatsClient);

			retrieveComponents();
		});
	}

	private void retrieveComponents() {
		final Component[] components = appFrame.getContentPane().getComponents();
		for (Component component : components) {
			if (component instanceof JFormattedTextField) {
				dateField = (JFormattedTextField) component;
			} else if (component instanceof ReactiveButton) {
				button = (ReactiveButton) component;
			}
		}
	}

	@Test
	public void smoke() throws Exception {
		control.showGUI();
		assertThat(appFrame.isVisible(), is(true));

		dateField.setText("2012-05-10");
		button.doClick();

		Thread.sleep(5000);

		assertThat(boxScorePanelListModel.getSize(), is(7));
	}

}
