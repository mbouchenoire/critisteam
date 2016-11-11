package com.mbouchenoire.critisteam;

/**
 * Represents the label that summarizes the game reviews,
 * such as "Positive", "Mixed"...
 *
 * @author mbouchenoire
 * @see UserReviewsSummary
 */
public enum UserReviewsSummaryLabel {
    OVERHWELMINGLY_POSITIVE(1, "Overhwelmingly Positive"),
    VERY_POSITIVE(2, "Very Positive"),
    POSITIVE(3, "Positive"),
    MOSTLY_POSITIVE(4, "Mostly Positive"),
    MIXED(5, "Mixed"),
    MOSTLY_NEGATIVE(6, "Mostly Negative"),
    NEGATIVE(7, "Negative"),
    VERY_NEGATIVE(8, "Very Negative"),
    OVERHWELMINGLY_NEGATIVE(9, "Overwhelmingly Negative");

    private final int rank;
    private final String text;

    UserReviewsSummaryLabel(int rank, final String text) {
        this.rank = rank;
        this.text = text;
    }

    /**
     * Represents the rank of the label. This rank allows labels to be compared between
     * each others.
     *
     * @return the rank of the label, 1 beeing the best.
     */
    public int getRank() {
        return rank;
    }

    public String getText() {
        return text;
    }

    public boolean isBetterThan(final UserReviewsSummaryLabel other) {
        return this.getRank() < other.getRank();
    }

    @Override
    public String toString() {
        return text;
    }

    public static final UserReviewsSummaryLabel fromRank(int rank) {
        for(UserReviewsSummaryLabel label: UserReviewsSummaryLabel.values()) {
            if (label.getRank() == rank) {
                return label;
            }
        }

        throw new IllegalArgumentException("Unknown user reviews label with rank '" + rank + "'.");
    }

    public static final UserReviewsSummaryLabel fromText(final String text) {
        for(UserReviewsSummaryLabel label: UserReviewsSummaryLabel.values()) {
            if (label.getText().equalsIgnoreCase(text)) {
                return label;
            }
        }

        throw new IllegalArgumentException("Unknown user reviews label with text '" + text + "'.");
    }
}
