package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.Observable;
import com.hartveld.stream.reactive.swing.ReactiveButton;
import com.hartveld.stream.reactive.swing.ReactiveFrame;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppFrame extends ReactiveFrame {

	private static final Logger LOG = LoggerFactory.getLogger(AppFrame.class);

	private final JFormattedTextField dateInputField;
	private final ReactiveButton loadButton;

	private final JScrollPane boxScoreScrollPane;
	private final JList<BoxScorePanel> boxScoreList;

	private final JProgressBar progressBar;

	public final Observable<String> loadRequests;

	public AppFrame(BoxScorePanelListModel boxScorePanelListModel) {
		super("Stats App");

		final DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));

		this.dateInputField = new JFormattedTextField();
		this.dateInputField.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
		try {
			this.dateInputField.setText(dateFormatter.valueToString(new Date()));
		} catch (ParseException ex) {
			LOG.error("Failed to parse date: {}", ex.getMessage(), ex);
		}

		this.loadButton = new ReactiveButton("Load");

		this.boxScoreList = new JList<>(boxScorePanelListModel);
		this.boxScoreList.setCellRenderer(new BoxScorePanelListCellRender());
		this.boxScoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.boxScoreScrollPane = new JScrollPane(boxScoreList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.progressBar = new JProgressBar();

		this.loadRequests = this.loadButton.events
				.map(e -> dateInputField.getText());

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

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
                    .addComponent(boxScoreScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dateInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadButton)
                    .addComponent(dateInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(boxScoreScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

		final Dimension dimension = new Dimension(300, 400);
		this.setPreferredSize(dimension);
		this.setResizable(false);

		this.pack();
	}

	void disableLoadButton() {
		this.loadButton.setEnabled(false);
	}

	void enableLoadButton() {
		this.loadButton.setEnabled(true);
	}

	void startProgressBar() {
		this.progressBar.setIndeterminate(true);
	}

	void stopProgressBar() {
		this.progressBar.setIndeterminate(false);
	}

}
