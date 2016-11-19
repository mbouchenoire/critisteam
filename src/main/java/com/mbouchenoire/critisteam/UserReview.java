package com.mbouchenoire.critisteam;

/**
 * @author mbouchenoire
 */
public class UserReview {

    private final String id;
    private final UserProfile profile;
    private final double hoursOnRecord;
    private final boolean isRecommendation;
    private final String content;
    private final int ratingsNumber;
    private final int helpfulRatingsNumber;
    private final int funnyRatingsNumber;

    public UserReview(final String id, final UserProfile profile, double hoursOnRecord, boolean isRecommendation,
                      final String content, int ratingsNumber, int helpfulRatingsNumber, int funnyRatingsNumber) {

        if (id == null || "".equals(id))
            throw new IllegalArgumentException("User review id cannot be null.");

        if (profile == null)
            throw new IllegalArgumentException("User review profile cannot be null.");

        if (hoursOnRecord < 0)
            throw new IllegalArgumentException("User hours on record cannot be negative.");

        if (content == null || "".equals(content))
            throw new IllegalArgumentException("User review content cannot be null.");

        if (ratingsNumber < 0)
            throw new IllegalArgumentException("User review ratings number cannot be negative.");

        if (helpfulRatingsNumber < 0)
            throw new IllegalArgumentException("User review helpful ratings number cannot be negative.");

        if (funnyRatingsNumber < 0)
            throw new IllegalArgumentException("User review funny ratings number cannot be negative.");

        this.id = id;
        this.profile = profile;
        this.hoursOnRecord = hoursOnRecord;
        this.isRecommendation = isRecommendation;
        this.content = content;
        this.ratingsNumber = ratingsNumber;
        this.helpfulRatingsNumber = helpfulRatingsNumber;
        this.funnyRatingsNumber = funnyRatingsNumber;
    }

    public String getId() {
        return id;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public double getHoursOnRecord() {
        return hoursOnRecord;
    }

    public boolean isRecommendation() {
        return isRecommendation;
    }

    public String getContent() {
        return content;
    }

    public int getRatingsNumber() {
        return ratingsNumber;
    }

    public int getHelpfulRatingsNumber() {
        return helpfulRatingsNumber;
    }

    public int getFunnyRatingsNumber() {
        return funnyRatingsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserReview that = (UserReview) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return id;
    }
}
