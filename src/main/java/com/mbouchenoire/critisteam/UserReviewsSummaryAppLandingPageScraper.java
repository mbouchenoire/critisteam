package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.SteamReviewsException;
import com.mbouchenoire.critisteam.error.NoRecentReviewsException;
import com.mbouchenoire.critisteam.error.NotSteamAppLandingPageHtmlException;
import com.mbouchenoire.critisteam.error.SteamAppNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements the {@link UserReviewsSummaryRepository} interface using web scraping.
 * This class will retrieve the required {@link UserReviewsSummary} by parsing
 * the HTML of the Steam App landing page (store.steampowered.com/app/{appId}).
 *
 * @author mbouchenoire
 */
public final class UserReviewsSummaryAppLandingPageScraper implements UserReviewsSummaryRepository {

    private static final String BASE_URL = "http://store.steampowered.com/app/";

    private final HtmlRetriever htmlRetriever;

    public UserReviewsSummaryAppLandingPageScraper(final HtmlRetriever htmlRetriever) {
        if (htmlRetriever == null)
            throw new NullPointerException("htmlRetriever");

        this.htmlRetriever = htmlRetriever;
    }

    @Override
    public final UserReviewsSummary getSummary(int appId, TimePeriod timePeriod) throws SteamReviewsException {
        final String url = BASE_URL + appId;

        try {
            final String html = htmlRetriever.retrieve(url);
            final Map<TimePeriod, UserReviewsSummary> summaries = scrap(html);
            final UserReviewsSummary summary = summaries.get(timePeriod);

            if (summary == null) {
                throw new NoRecentReviewsException(appId);
            } else {
                return summary;
            }
        } catch (IOException ioe) {
            throw new SteamReviewsException("Error while obtaining app landing page HTML", ioe);
        } catch (NotSteamAppLandingPageHtmlException nglphe) {
            throw new SteamAppNotFoundException(appId, nglphe);
        }
    }

    private static final Map<TimePeriod, UserReviewsSummary> scrap(final String html) throws NotSteamAppLandingPageHtmlException {
        if (html == null || "".equals(html.trim()))
            throw new IllegalArgumentException("The HTML to scrap cannot be null.");

        final Document page = Jsoup.parse(html);
        final Elements summaryRows = page.select(".user_reviews_summary_row");

        if (summaryRows.size() == 0)
            throw new NotSteamAppLandingPageHtmlException();

        if (summaryRows == null)
            throw new IllegalStateException("There should always be at least one summary row.");

        final Map<TimePeriod, UserReviewsSummary> summaries = new HashMap<TimePeriod, UserReviewsSummary>();

        for(Element summaryRow: summaryRows) {
            final Map.Entry<TimePeriod, UserReviewsSummary> summary = parse(summaryRow);
            summaries.put(summary.getKey(), summary.getValue());
        }

        return summaries;
    }

    private static final Map.Entry<TimePeriod, UserReviewsSummary> parse(final Element summaryRow) {
        if (summaryRow == null)
            throw new IllegalArgumentException("The summary row to parse cannot be null.");

        final String text = summaryRow.attr("data-store-tooltip");
        final int indexOfPercentage = text.indexOf("%");
        final int indexOfReviewsNumberFirstDigit = text.indexOf("of the") + "of the".length() + 1;
        final int indexOfReviewsNumberLastDigit = text.indexOf("user reviews") - 2;

        final String strPositiveReviewsPercentage = text.substring(0, indexOfPercentage);
        final String strReviewsNumber = text.substring(indexOfReviewsNumberFirstDigit, indexOfReviewsNumberLastDigit + 1);

        final int positivePercentage = Integer.parseInt(strPositiveReviewsPercentage);
        final int reviewsNumber = Integer.parseInt(strReviewsNumber.replace(",", ""));

        final Element timePeriodElement = summaryRow.select(".subtitle").first();
        final String timePeriodText = timePeriodElement.text().replace(":", "");
        final TimePeriod timePeriod = TimePeriod.fromText(timePeriodText);

        final Element labelElement = summaryRow.select(".game_review_summary").first();
        final String labelText = labelElement.text();
        final UserReviewsSummaryLabel label = UserReviewsSummaryLabel.fromText(labelText);

        final UserReviewsSummary summary = new UserReviewsSummary(reviewsNumber, positivePercentage, label);

        return new AbstractMap.SimpleEntry<TimePeriod, UserReviewsSummary>(timePeriod, summary);
    }
}
