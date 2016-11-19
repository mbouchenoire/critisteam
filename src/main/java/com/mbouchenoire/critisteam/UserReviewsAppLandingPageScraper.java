package com.mbouchenoire.critisteam;

import com.mbouchenoire.critisteam.error.NotSteamAppLandingPageHtmlException;
import com.mbouchenoire.critisteam.error.SteamAppNotFoundException;
import com.mbouchenoire.critisteam.error.SteamReviewsException;
import com.mbouchenoire.critisteam.utils.HtmlScrapingUtils;
import com.mbouchenoire.critisteam.utils.HttpHeadersUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author mbouchenoire
 */
public class UserReviewsAppLandingPageScraper implements UserReviewsRepository {

    private static final String BASE_URL = "http://store.steampowered.com/app/{appId}/#app_reviews_hash";

    private final HtmlRetriever htmlRetriever;

    public UserReviewsAppLandingPageScraper(final HtmlRetriever htmlRetriever) {
        if (htmlRetriever == null)
            throw new IllegalArgumentException("HtmlRetriever cannot be null.");

        this.htmlRetriever = htmlRetriever;
    }

    @Override
    public Collection<UserReview> getReviews(int appId, SteamSupportedLanguage language) throws SteamReviewsException {
        if (appId < 1)
            throw new IllegalArgumentException("Steam App Id cannot be inferior to 1.");

        final String url = BASE_URL.replace("{appId}", Integer.toString(appId));

        try {
            final Map<String, String> headers = HttpHeadersUtils.acceptLanguageOnly(language);
            final String html = htmlRetriever.retrieve(url, headers);
            return scrap(html);
        } catch (IOException ioe) {
            throw new SteamReviewsException("Error while obtaining app landing page HTML", ioe);
        } catch (NotSteamAppLandingPageHtmlException nglphe) {
            throw new SteamAppNotFoundException(appId, nglphe);
        }
    }

    public Collection<UserReview> scrap(final String html) throws NotSteamAppLandingPageHtmlException {
        final Collection<UserReview> reviews = new ArrayList<UserReview>();

        final Document doc = Jsoup.parse(html);
        final Elements reviewBoxes = doc.getElementsByClass("review_box");

        for(int i = 0; i < reviewBoxes.size(); i++) {
            final Element reviewBox = reviewBoxes.get(i);

            final Element header = reviewBox.getElementsByClass("header").first();

            if (header == null)
                break;

            final String headerText = header.text();
            final int ratingsNumber = parseRatingsNumber(headerText);
            final int helpfulRatingsNumber = parseHelpfulRatingsNumber(headerText);
            final int funnyRatingsNumber = parseFunnyRatingsNumber(headerText);

            final Element personaElement = reviewBox.select(".leftcol").first();
            final UserProfile persona = parsePersona(personaElement);

            final Element commentElement = reviewBox.select(".rightcol").first();

            final Element voteHeader = commentElement.select(".vote_header").first();
            final String rawRecommended = voteHeader.getElementsByClass("title").first().text();
            final boolean isRecommendation = parseRecommendation(rawRecommended);
            final String rawHoursOnRecord = voteHeader.getElementsByClass("hours").first().text();
            final double hoursOnRecord = parseHoursOnRecord(rawHoursOnRecord);

            final String content = commentElement.getElementsByClass("content").text();

            final String reviewIdAttributeValue = personaElement.parent().attr("id");
            final String userReviewId = parseReviewIdFromAttributeId(reviewIdAttributeValue);

            final UserReview review = new UserReview(
                    userReviewId,
                    persona,
                    hoursOnRecord,
                    isRecommendation,
                    content,
                    ratingsNumber,
                    helpfulRatingsNumber,
                    funnyRatingsNumber
            );

            reviews.add(review);
        }

        return reviews;
    }

    private static String parseReviewIdFromAttributeId(final String attributeId) {
        final int reviewId = HtmlScrapingUtils.findNumbers(attributeId)[0];
        return Integer.toString(reviewId);
    }

    private static int parseRatingsNumber(final String headerText) {
        return HtmlScrapingUtils.findNumbers(headerText)[1];
    }

    private static int parseHelpfulRatingsNumber(final String headerText) {
        return HtmlScrapingUtils.findNumbers(headerText)[0];
    }

    private static int parseFunnyRatingsNumber(final String headerText) {
        final int[] numbers = HtmlScrapingUtils.findNumbers(headerText);
        return numbers[numbers.length - 1];
    }

    private static UserProfile parsePersona(final Element personaElement) {
        final String name = personaElement.getElementsByClass("persona_name").first()
                .getElementsByTag("a").first().text();

        final String rawProductsInAccount = personaElement.getElementsByClass("num_owned_games").first()
                .getElementsByTag("a").first().text();
        final int ownedGamesNumber = parseOwnedGamesNumber(rawProductsInAccount);

        final String rawReviewsNumber = personaElement.getElementsByClass("num_reviews").first()
                .getElementsByTag("a").first().text();
        final int reviewsNumber = parseReviewsNumber(rawReviewsNumber);

        return new UserProfile(name, ownedGamesNumber, reviewsNumber);
    }

    private static int parseOwnedGamesNumber(final String rawProductsInAccountNumber) {
        return HtmlScrapingUtils.findNumbers(rawProductsInAccountNumber)[0];
    }

    private static int parseReviewsNumber(final String rawReviewsNumber) {
        return HtmlScrapingUtils.findNumbers(rawReviewsNumber)[0];
    }

    private static boolean parseRecommendation(final String rawRecommended) {
        return rawRecommended.split(" ").length == 1;
    }

    private static double parseHoursOnRecord(final String rawHoursOnRecord) {
        // Not an elegant solution to a localization problem
        if (rawHoursOnRecord.contains(",") && rawHoursOnRecord.contains(".")) {
            return HtmlScrapingUtils.findDoubles(rawHoursOnRecord.replace(",", ""))[0];
        } else {
            return HtmlScrapingUtils.findDoubles(rawHoursOnRecord)[0];
        }
    }
}
