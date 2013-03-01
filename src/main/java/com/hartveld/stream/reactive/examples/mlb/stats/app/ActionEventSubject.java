package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observer;
import com.hartveld.stream.reactive.subjects.BasicSubject;
import com.hartveld.stream.reactive.subjects.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionEventSubject extends BasicSubject<ActionEvent, ActionListener> implements Subject<ActionEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(ActionEventSubject.class);

	private final AbstractButton button;

	public ActionEventSubject(final AbstractButton button) {
		checkNotNull(button, "button");

		this.button = button;
	}

	@Override
	protected ActionListener onSubscribe(final Observer<ActionEvent> observer) {
		LOG.trace("onSubscribe(): {}", observer);

		final ActionListener listener = e -> onNext(e);

		LOG.trace("Adding action listener: {}", listener);
		this.button.addActionListener(listener);

		return listener;
	}

	@Override
	protected void onClose(final ActionListener source) {
		LOG.trace("onClose(): {}", source);

		checkNotNull(source, "source");

		this.button.removeActionListener(source);
	}

}
