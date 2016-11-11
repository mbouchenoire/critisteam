package com.mbouchenoire.critisteam.error;

/**
 * This exception is thrown by the library when the client
 * provides a Steam App Id which does not exist.
 *
 * @author mbouchenoire
 */
public class SteamAppNotFoundException extends SteamReviewsException {

    public SteamAppNotFoundException(int appId, Throwable cause) {
        super("Steam App with id '" + appId + "' not found.", cause);
    }
}
