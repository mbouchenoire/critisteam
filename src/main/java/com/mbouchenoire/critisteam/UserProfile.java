package com.mbouchenoire.critisteam;

/**
 * Represents an user profile which reviewed a game.
 *
 * @author mbouchenoire
 */
public class UserProfile {

    private final String name;
    private final int ownedGamesNumber;
    private final int reviewsNumber;

    public UserProfile(final String name, int ownedGamesNumber, int reviewsNumber) {
        if (name == null || "".equals(name))
            throw new IllegalArgumentException("Invalid user name.");

        if (ownedGamesNumber < 1)
            throw new IllegalArgumentException("User must own at least one game.");

        if (reviewsNumber < 0)
            throw new IllegalArgumentException("User reviews number cannot be negative.");

        this.name = name;
        this.ownedGamesNumber = ownedGamesNumber;
        this.reviewsNumber = reviewsNumber;
    }

    /**
     * Returns the name of the user profile
     *
     * @return the name of the user profile
     */
    public String getName() {
        return name;
    }

    /**
     * Return the number of owned games of the user profile
     *
     * @return the number of owned games of the user profile
     */
    public int getOwnedGamesNumber() {
        return ownedGamesNumber;
    }

    /**
     * Returns the number of reviews posted by the user profile
     *
     * @return the number of reviews posted by the user profile
     */
    public int getReviewsNumber() {
        return reviewsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (ownedGamesNumber != that.ownedGamesNumber) return false;
        if (reviewsNumber != that.reviewsNumber) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ownedGamesNumber;
        result = 31 * result + reviewsNumber;
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
