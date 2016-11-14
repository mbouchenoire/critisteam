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
        UserReviewsSummary reviewsSummary = reviewsApi.getSummary(APP_ID, TimePeriod.RECENT);

        reviewsSummary.getReviewsNumber(); // 53,124
        reviewsSummary.getPositivePercentage(); // 87%
        reviewsSummary.getLabel(); // VERY_POSITIVE
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
