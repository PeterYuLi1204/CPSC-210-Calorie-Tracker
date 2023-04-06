package model;

import java.util.Calendar;
import java.util.Date;

// Represents a calorie tracker application event.
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    // EFFECTS: Creates an event with the given description and the current date/time stamp
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: Returns the date of this event (includes time)
    public Date getDate() {
        return dateLogged;
    }

    // EFFECTS: Returns the description of this event.
    public String getDescription() {
        return description;
    }

    // EFFECTS: Returns true if the two objects are both events, have the same date logged and description,
    //          false otherwise
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged) && this.description.equals(otherEvent.description));
    }

    // EFFECTS: Returns the hashcode for this event
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: Returns a formatted string with the date logged and description of this event
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
