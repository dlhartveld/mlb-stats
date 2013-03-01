package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.Observable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JProgressBar;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppFrame extends ReactiveFrame {

	private static final Logger LOG = LoggerFactory.getLogger(AppFrame.class);

	private final JFormattedTextField dateInputField;
	private final JButton loadButton;

	private final JProgressBar progressBar;

	public final Observable<String> loadRequests;

	public AppFrame() {
		super("Stats App");

		this.dateInputField = new JFormattedTextField();
		final DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));
		this.dateInputField.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
		try {
			this.dateInputField.setText(dateFormatter.valueToString(new Date()));
		} catch (ParseException ex) {
			LOG.error("Failed to parse date: {}", ex.getMessage(), ex);
		}

		this.loadButton = new JButton("Load");
		this.progressBar = new JProgressBar();

		this.loadRequests = new ActionEventSubject(loadButton)
				.map(e -> dateInputField.getText());

		layoutFrame();
	}

	private void layoutFrame() {
		final GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(dateInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(loadButton))
					.addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
				.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(loadButton)
					.addComponent(dateInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
				.addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
		);

		pack();
	}

	void disableLoadButton() {
		this.loadButton.setEnabled(false);
	}

	void enableLoadButton() {
		this.loadButton.setEnabled(true);
	}

}
