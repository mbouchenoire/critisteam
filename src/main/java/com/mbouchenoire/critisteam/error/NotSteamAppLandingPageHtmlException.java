package com.mbouchenoire.critisteam.error;

/**
 * This exception is thrown by the web scraper when the HTML it's parsing
 * is not from a Steam App landing page (such as store.steampowered.com/app/730).
 *
 * @author mbouchenoire
 */
public class NotSteamAppLandingPageHtmlException extends SteamReviewsException {

    public NotSteamAppLandingPageHtmlException() {
        super("The provided HTML is not from a Steam App landing page.", null);
    }
}
