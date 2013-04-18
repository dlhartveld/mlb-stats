package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import com.hartveld.commons.test.swing.AbstractSwingFrameTest;
import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.examples.mlb.stats.client.MLBStatsClient;
import com.hartveld.stream.reactive.swing.DefaultReactiveListModel;
import com.hartveld.stream.reactive.swing.ReactiveSwingButton;
import com.hartveld.stream.reactive.swing.ReactiveSwingFrame;
import javax.swing.JFormattedTextField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class AppTest extends AbstractSwingFrameTest {

	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

	private ReactiveSwingFrame frame;
	private ReactiveListModel<Game> model;

	private JFormattedTextField input;
	private ReactiveSwingButton button;

	@Mock
	private MLBStatsClient client;

	@Override
	protected ReactiveSwingFrame createTestableFrame() throws Exception {
		this.model = new DefaultReactiveListModel<>();
		final AppFrameControl control = new AppFrameControl(model, new MLBStatsClient());

		this.frame = control.frame();

		return frame;
	}

	@Override
	protected void lookupComponents() {
		this.input = super.lookup("input", JFormattedTextField.class);
		this.button = super.lookup("load", ReactiveSwingButton.class);
	}

	@Test
	public void dummy() throws Exception {
		LOG.info("Test");

//		final Observable<GameDay> observable = ObservableFactory.observableOf(
//				new GameDay(null, LocalDate.MIN)
//		);
//
//		when(client.retrieve(LocalDate.of(2012, 5, 10))).thenReturn(observable);

		this.input.setText("2012-05-10");
		this.button.doClick();

		Thread.sleep(2000);

		assertThat(this.model.getElementAt(0).getBoxScoreA().getTeam().getName(),
				is(equalTo("Orioles")));
		assertThat(this.model.getElementAt(0).getBoxScoreB().getTeam().getName(),
				is(equalTo("Rangers")));
	}

}
