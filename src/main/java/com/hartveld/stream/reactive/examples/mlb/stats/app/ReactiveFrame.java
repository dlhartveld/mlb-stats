package com.hartveld.stream.reactive.examples.mlb.stats.app;

import com.hartveld.stream.reactive.Observable;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class ReactiveFrame extends JFrame {

	public final Observable<WindowEvent> windowClosingEvents;

	public ReactiveFrame(final String title) {
		super(title);

		this.windowClosingEvents = new WindowClosingEventSubject(this);
	}

}
