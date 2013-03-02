package com.hartveld.stream.reactive.examples.mlb.stats.app;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class BoxScorePanelListCellRender implements ListCellRenderer<BoxScorePanel> {

	@Override
	public Component getListCellRendererComponent(JList<? extends BoxScorePanel> list, BoxScorePanel value, int index, boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			value.setBackground(list.getSelectionBackground());
			value.setForeground(list.getSelectionForeground());
		}

		return value;
	}

}
