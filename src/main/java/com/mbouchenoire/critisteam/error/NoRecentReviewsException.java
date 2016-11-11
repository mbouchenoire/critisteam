package com.mbouchenoire.critisteam.error;

/**
 * This exception is thrown by when the client request recent reviews but none exist.
 *
 * @author mbouchenoire
 */
public class NoRecentReviewsException extends SteamReviewsException {

    public NoRecentReviewsException(int appId) {
        super("The is no recent reviews for the app with id '" + appId + "'.", null);
    }
}
