package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.Observer;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class WindowClosingEventSubject extends EventSubject<WindowEvent, WindowListener> {

	private final JFrame frame;

	public WindowClosingEventSubject(final JFrame frame) {
		checkNotNull(frame, "frame");

		this.frame = frame;
	}

	@Override
	protected WindowListener onSubscribe(final Observer<WindowEvent> observer) {
		checkNotNull(observer, "observer");

		final WindowListener listener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				onNext(e);
			}
		};

		frame.addWindowListener(listener);

		return listener;
	}

	@Override
	protected void onClose(final WindowListener source) {
		checkNotNull(source, "source");

		frame.removeWindowListener(source);
	}

}
