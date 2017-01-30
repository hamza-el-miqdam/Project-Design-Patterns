package com.components;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class Task {
    private final long id;
    private final String description;
    private final Date date;
    private Date dateLimite = null;
    private boolean done;

    public Date getDate() {
        return date;
    }

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        Calendar now = new GregorianCalendar();
        Date nowdate = now.getTime();
        this.dateLimite = nowdate;
        this.date = nowdate;
    }

    public long getId() {
        return id;
    }

    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public Date getDateLimite() {
        return dateLimite;
    }
}
