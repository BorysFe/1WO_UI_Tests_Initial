package portalPages.polls;

public enum RegionsAndLanguages {

    SELECT_POLL_REGION("js-entity-region"),
    SELECT_POLL_LANGUAGE("js-entity-language"),
    CREATE_POLL_BUTTON("js-create-new-entity"),

    SELECT_WIDGET_REGION("regionId"),
    SELECT_WIDGET_LANGUAGE("poll.locale"),

    REGION_AFRICA("Africa"),
    REGION_ASIA("Asia"),
    REGION_ANZ("ANZ"),
    REGION_EASTERN_EUROPE("Eastern Europe"),
    REGION_INDIA("India"),
    REGION_LATIN_AMERICA("Latin America"),
    REGION_MIDDLE_EAST("Middle East"),
    REGION_WESTERN_EUROPE("Western Europe"),
    REGION_US("U.S"),
    REGION_RUSSIA("Russia"),
    REGION_ALL("All regions"),

    LANGUAGE_ENGLISH("English"),
    LANGUAGE_VALUE_ENGLISH("eu"),

    LANGUAGE_POLISH("Polish"),
    LANGUAGE_VALUE_POLISH("pl"),
    LANGUAGE_RUSSIAN("Russian"),
    LANGUAGE_VALUE_RUSSIAN("ru"),
    LANGUAGE_UKRAINIAN("Ukrainian"),
    LANGUAGE_VALUE_UKRAINIAN("uk"),

    LANGUAGE_SIMPLIFIED_CHINESE("Simplified Chinese"),
    LANGUAGE_VALUE_SIMPLIFIED_CHINESE("cn"),
    LANGUAGE_TRADITIONAL_CHINESE("Traditional Chinese"),
    LANGUAGE_VALUE_TRADITIONAL_CHINESE("tw"),
    LANGUAGE_JAPANESE("Japanese"),
    LANGUAGE_VALUE_JAPANESE("ja"),
    LANGUAGE_KOREAN("Korean"),
    LANGUAGE_VALUE_KOREAN("ko"),
    LANGUAGE_INDONESIAN("Indonesian"),
    LANGUAGE_VALUE_INDONESIAN("id"),
    LANGUAGE_THAI("Thai"),
    LANGUAGE_VALUE_THAI("th"),
    LANGUAGE_VIETNAMESE("Vietnamese"),
    LANGUAGE_VALUE_VIETNAMESE("vi"),

    LANGUAGE_HINDI("Hindi"),
    LANGUAGE_VALUE_HINDI("hi"),
    LANGUAGE_TAMIL("Tamil"),
    LANGUAGE_VALUE_TAMIL("ta"),
    LANGUAGE_KANNADA("Kannada"),
    LANGUAGE_VALUE_KANNADA("kn"),
    LANGUAGE_TELUGU("Telugu"),
    LANGUAGE_VALUE_TELUGU("te"),
    LANGUAGE_GUJARATI("Gujarati"),
    LANGUAGE_VALUE_GUJARATI("gu"),
    LANGUAGE_BENGALI("Bengali"),
    LANGUAGE_VALUE_BENGALI("bn"),
    LANGUAGE_MALAYALAM("Malayalam"),
    LANGUAGE_VALUE_MALAYALAM("ml"),
    LANGUAGE_MARATHI("Marathi"),
    LANGUAGE_VALUE_MARATHI("mr"),

    LANGUAGE_SPANISH("Spanish"),
    LANGUAGE_VALUE_SPANISH("es"),
    LANGUAGE_FRENCH("French"),
    LANGUAGE_VALUE_FRENCH("fr"),
    LANGUAGE_PORTUGUESE("Portuguese"),
    LANGUAGE_VALUE_PORTUGUESE("pt"),

    LANGUAGE_ARABIC("Arabic"),
    LANGUAGE_VALUE_ARABIC("ar"),
    LANGUAGE_TURKISH("Turkish"),
    LANGUAGE_VALUE_TURKISH("tr"),
    LANGUAGE_HEBREW("Hebrew"),
    LANGUAGE_VALUE_HEBREW("he"),

    LANGUAGE_GERMAN("German"),
    LANGUAGE_VALUE_GERMAN("de"),
    LANGUAGE_SWEDISH("Swedish"),
    LANGUAGE_VALUE_SWEDISH("sv"),
    LANGUAGE_GREEK("Greek"),
    LANGUAGE_VALUE_GREEK("el"),
    LANGUAGE_ITALIAN("Italian"),
    LANGUAGE_VALUE_ITALIAN("it"),
    LANGUAGE_DUTCH("Dutch"),
    LANGUAGE_VALUE_DUTCH("nl");

    private String pollRegionAndLanguage;

    RegionsAndLanguages(final String title) {
        this.pollRegionAndLanguage = title;
    }

    @Override
    public String toString() {
        return pollRegionAndLanguage;
    }
}
