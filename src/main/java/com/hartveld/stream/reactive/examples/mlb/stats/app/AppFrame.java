package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.component.ReactiveButton;
import com.hartveld.stream.reactive.component.ReactiveList;
import com.hartveld.stream.reactive.component.ReactiveListModel;
import com.hartveld.stream.reactive.component.ReactiveProgressBar;
import com.hartveld.stream.reactive.component.ReactiveTextField;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import com.hartveld.stream.reactive.swing.ReactiveSwingButton;
import com.hartveld.stream.reactive.swing.ReactiveSwingFormattedTextField;
import com.hartveld.stream.reactive.swing.ReactiveSwingFrame;
import com.hartveld.stream.reactive.swing.ReactiveSwingList;
import com.hartveld.stream.reactive.swing.ReactiveSwingProgressBar;
import java.awt.Component;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppFrame extends ReactiveSwingFrame {

	private static final Logger LOG = LoggerFactory.getLogger(AppFrame.class);

	private final ReactiveSwingFormattedTextField dateInputField;
	private final ReactiveSwingButton loadButton;

	private final JScrollPane boxScoreScrollPane;
	private final ReactiveSwingList<Game> boxScoreList;

	private final ReactiveSwingProgressBar progressBar;

	public AppFrame(final ReactiveListModel<Game> boxScorePanelListModel) {
		super("Stats App");

		final DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("yyyy-MM-dd"));

		this.dateInputField = new ReactiveSwingFormattedTextField();
		this.dateInputField.setName("input");
		this.dateInputField.setFormatterFactory(new DefaultFormatterFactory(dateFormatter));
		try {
			this.dateInputField.setText(dateFormatter.valueToString(new Date()));
		} catch (ParseException ex) {
			LOG.error("Failed to parse date: {}", ex.getMessage(), ex);
		}

		this.loadButton = new ReactiveSwingButton("Load");
		this.loadButton.setName("load");

		this.boxScoreList = new ReactiveSwingList<>(boxScorePanelListModel);
		this.boxScoreList.setCellRenderer(new BoxScorePanelListCellRender());
		this.boxScoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.boxScoreScrollPane = new JScrollPane(boxScoreList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.progressBar = new ReactiveSwingProgressBar();

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		layoutFrame();
	}

	public ReactiveButton load() {
		return this.loadButton;
	}

	public ReactiveTextField dateInput() {
		return this.dateInputField;
	}

	public ReactiveList<Game> gameList() {
		return this.boxScoreList;
	}

	public ReactiveProgressBar progressBar() {
		return this.progressBar;
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

	private static class BoxScorePanelListCellRender implements ListCellRenderer<Game> {

		@Override
		public Component getListCellRendererComponent(final JList<? extends Game> list, final Game value, final int index, final boolean isSelected, final boolean cellHasFocus) {
			final BoxScorePanel panel = new BoxScorePanel(value);

			if (isSelected) {
				panel.setBackground(list.getSelectionBackground());
				panel.setForeground(list.getSelectionForeground());
			}

			return panel;
		}

	}

}
