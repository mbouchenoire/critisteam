package com.mbouchenoire.critisteam;

/**
 * Represents what an user can see from the landing page
 * of a game on the steam store.
 *
 * @author mbouchenoire
 * @see UserReviewsSummaryLabel
 */
public final class UserReviewsSummary {

    private final int reviewsNumber;
    private final int positivePercentage;
    private final UserReviewsSummaryLabel label;

    public UserReviewsSummary(int reviewsNumber, int positivePercentage, final UserReviewsSummaryLabel label) {
        super();

        if (label == null)
            throw new IllegalArgumentException("Label cannot be null.");

        this.reviewsNumber = reviewsNumber;
        this.positivePercentage = positivePercentage;
        this.label = label;
    }

    /**
     * Returns the number of reviews for this game at a specific language (or not).
     *
     * @return the number of reviews for this game
     */
    public int getReviewsNumber() {
        return reviewsNumber;
    }

    /**
     * Returns the percentage of positive reviews for this game at a specific language or time span (see the API)
     *
     * @return the percentage of positive reviews for this game
     */
    public int getPositivePercentage() {
        return positivePercentage;
    }

    /**
     * Returns the label of the reviews summary (positive, mixed..) as an enumeration value.
     *
     * @return the label of the reviews summary
     */
    public UserReviewsSummaryLabel getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserReviewsSummary that = (UserReviewsSummary) o;

        if (reviewsNumber != that.reviewsNumber) return false;
        if (positivePercentage != that.positivePercentage) return false;
        return label == that.label;

    }

    @Override
    public int hashCode() {
        int result = reviewsNumber;
        result = 31 * result + positivePercentage;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%d%% - %s (%d reviews)", positivePercentage, label, reviewsNumber);
    }
}
