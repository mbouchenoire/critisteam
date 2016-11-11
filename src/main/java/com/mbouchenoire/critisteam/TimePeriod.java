package com.mbouchenoire.critisteam;

/**
 * Represents the (current) two time periods available when you want
 * to filter reviews : Recent and Overall. According to Valve's definition
 * of "Recent" (in this context), it includes "reviews within the past 30
 * days (as long as there are enough reviews posted within those 30 days
 * and as long as the game has been available on Steam for at least 45 days)".
 *
 * @author mbouchenoire
 */
public enum TimePeriod {
    RECENT("Recent"),
    OVERALL("Overall");

    private final String text;

    TimePeriod(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static final TimePeriod fromText(final String text) {
        for(TimePeriod timePeriod: TimePeriod.values()) {
            if (timePeriod.getText().equalsIgnoreCase(text)) {
                return timePeriod;
            }
        }

        throw new IllegalArgumentException("Unknown time period with text '" + text + "'.");
    }
}
