package com.hartveld.stream.reactive.swing;

import com.hartveld.stream.reactive.subjects.BasicSubject;
import java.util.EventListener;
import java.util.EventObject;

public class EventSubject<Event extends EventObject, EventSource extends EventListener> extends BasicSubject<Event, EventSource> {

}
