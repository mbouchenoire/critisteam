package com.mbouchenoire.critisteam;

import java.util.Locale;

/**
 * @author mbouchenoire
 */
public enum SteamSupportedLanguage {
    BULGARIAN("bulgarian", "bg"),
    CZECH("czech", "cs"),
    DANISH("danish", "da"),
    DUTCH("dutch", "nl"),
    ENGLISH("english", "en"),
    FINNISH("finish", "fi"),
    FRENCH("french", "fr"),
    GERMAN("german", "de"),
    GREEK("greek", "el"),
    HUNGARIAN("hungarian", "hu"),
    ITALIAN("italian", "it"),
    JAPANESE("japanese", "ja"),
    KOREAN("korean", "ko"),
    NORWEGIAN("norwegian", "no"),
    POLISH("polish", "pl"),
    PORTUGUESE("portuguese", "pt"),
    PORTUGUESE_BRAZIL("brazilian", "pt"),
    ROMANIAN("romanian", "ro"),
    RUSSIAN("russian", "ru"),
    SIMPLIFIED_CHINESE("schinese", "zh"),
    SPANISH("spanish", "es"),
    SWEDISH("swedish", "sv"),
    TRADITIONAL_CHINESE("tchinese", "zh"),
    THAI("thai", "th"),
    TURKISH("turkish", "tr"),
    UKRAINIAN("ukrainian", "uk");

    private final String code;
    private final String iso639_1;

    SteamSupportedLanguage(final String code, final String iso639_1) {
        this.code = code;
        this.iso639_1 = iso639_1;
    }

    public String getCode() {
        return code;
    }

    public String getIso639_1() {
        return iso639_1;
    }

    public static SteamSupportedLanguage defaultLanguage() {
        return fromLocale(Locale.getDefault());
    }

    public static SteamSupportedLanguage fromLocale(final Locale locale) {
        final String localeIso3Language = locale.getLanguage();

        for(SteamSupportedLanguage language: SteamSupportedLanguage.values()) {
            if (language.getIso639_1().equals(localeIso3Language)) {
                return language;
            }
        }

        throw new IllegalArgumentException("Unsupported locale (" + locale + ")");
    }
}
