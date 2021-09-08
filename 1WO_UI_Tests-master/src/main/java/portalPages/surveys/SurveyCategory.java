package portalPages.surveys;

public enum SurveyCategory {

        CATEGORY_ART_CULTURE("Art & Culture"),
        CATEGORY_VALUE_ART_CULTURE("24"),
        CATEGORY_BUSINESS("Business"),
        CATEGORY_VALUE_BUSINESS("7"),
        CATEGORY_CONSUMER_PRODUCTS("Consumer products"),
        CATEGORY_VALUE_CONSUMER_PRODUCTS("23"),
        CATEGORY_EDUCATION("Education"),
        CATEGORY_VALUE_EDUCATION("39"),
        CATEGORY_ENTERTAINMENT("Entertainment"),
        CATEGORY_VALUE_ENTERTAINMENT("5"),
        CATEGORY_HEALTH("Health"),
        CATEGORY_VALUE_HEALTH("8"),
        CATEGORY_LIFESTYLE("Lifestyle"),
        CATEGORY_VALUE_LIFESTYLE("6"),
        CATEGORY_NEWS("News"),
        CATEGORY_VALUE_NEWS("37"),
        CATEGORY_POLITICS("Politics"),
        CATEGORY_VALUE_POLITICS("3"),
        CATEGORY_SCIENCE("Science"),
        CATEGORY_VALUE_SCIENCE("36"),
        CATEGORY_SILICON_VALLEY("Silicon Valley"),
        CATEGORY_VALUE_SILICON_VALLEY("38"),
        CATEGORY_SPORTS("Sports"),
        CATEGORY_VALUE_SPORTS("12"),
        CATEGORY_TECHNOLOGY("Technology"),
        CATEGORY_VALUE_TECHNOLOGY("1"),
        CATEGORY_WORLD("World"),
        CATEGORY_VALUE_WORLD("9"),
        CATEGORY_CROWDSOURCING("crowdsourcing"),
        CATEGORY_VALUE_CROWDSOURCING("42");

        private final String pollCategory;

        SurveyCategory(final String title) {
            this.pollCategory = title;
        }

        @Override
        public String toString() {
            return pollCategory;
        }
    }