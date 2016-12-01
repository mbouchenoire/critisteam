# CritiSteam
[![Build Status](https://travis-ci.org/mbouchenoire/critisteam.svg?branch=master)](https://travis-ci.org/mbouchenoire/critisteam)&nbsp;
[![Coverage Status](https://coveralls.io/repos/github/mbouchenoire/critisteam/badge.svg?branch=master)](https://coveralls.io/github/mbouchenoire/critisteam?branch=master)

A Java library to retrieve Steam reviews data.

## Basic usage
```java
import com.mbouchenoire.critisteam;
import com.mbouchenoire.critisteam.error.SteamReviewsException;

public class App {

    // You can get a Steam App Id by going on its Steam page and looking at the URL,
    // or by using other APIs such as go-ive/steam-api
    private static final int APP_ID = 730; // CS:GO

    public static void main(String[] args) throws SteamReviewsException {
        SteamReviewsApi reviewsApi = new SteamReviewsApi();
        
        // Retrieve reviews summary (number, positive percentage...)
        UserReviewsSummary reviewsSummary = reviewsApi.getSummary(APP_ID, TimePeriod.RECENT);

        reviewsSummary.getReviewsNumber(); // 53,124
        reviewsSummary.getPositivePercentage(); // 87%
        reviewsSummary.getLabel(); // VERY_POSITIVE
        
        // Retrieve individual reviews
        Collection<UserReview> reviews = reviewsApi.getReviews(APP_ID, SteamSupportedLanguage.ENGLISH);
        
        for(UserReview review: reviews) {
            review.getProfile(); // UserProfile class with owned games number, reviews number...
            review.getHoursOnRecord(); // 145 (hours played when the user posted the review)
            review.isRecommendation(); // true
            review.getContent(); // "This game is pretty cool but..."
            review.getRatingsNumber(); // 57 (the total number of ratings for this review)
            review.getHelpfulRatingsNumber(); // 48 (number of people who found this review helpful)
            review.getFunnyRatingsNumber(); // 20 (number of people who found this review funny)
        }
    }
}
```

### Cache management
A cache management solution is included within the API :
```java
// The CachedSteamReviewsApi class extends the "default" SteamReviewsApi class,
// making the cache management transparent to the client.
SteamReviewsApi reviewsApi = new CachedSteamReviewsApi(5, TimeUnit.MINUTES);
```
