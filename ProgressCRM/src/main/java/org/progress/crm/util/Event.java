package org.progress.crm.util;

public class Event {

    public int id;
    public String title;
    public String url;
    public String Class;
    public String start;
    public String end;

    public Event(int id, String title, String url, String Class, String start, String end) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.Class = Class;
        this.start = start;
        this.end = end;
    }
}
