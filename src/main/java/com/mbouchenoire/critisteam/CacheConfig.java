package com.mbouchenoire.critisteam;

import java.util.concurrent.TimeUnit;

/**
 * @author mbouchenoire
 */
public class CacheConfig {

    public static final CacheConfig NONE = new CacheConfig(0, TimeUnit.NANOSECONDS);

    private final int duration;
    private final TimeUnit timeUnit;

    public CacheConfig(int duration, final TimeUnit timeUnit) {
        if (duration < 0)
            throw new IllegalArgumentException("Duration must be positive.");

        if (timeUnit == null)
            throw new IllegalArgumentException("TimeUnit cannot be null.");

        this.duration = duration;
        this.timeUnit = timeUnit;
    }

    public int getDuration() {
        return duration;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheConfig that = (CacheConfig) o;

        if (duration != that.duration) return false;
        return timeUnit == that.timeUnit;

    }

    @Override
    public int hashCode() {
        int result = duration;
        result = 31 * result + (timeUnit != null ? timeUnit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return duration + " " + timeUnit.toString();
    }
}
