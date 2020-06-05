package com.applause.auto.pageframework.testdata;

public class TestConstants {

  /** Test Groups */
  public static final class TestNGGroups {
    // groups
    public static final String DISABLED = "disabled";
    public static final String DEBUG = "debug";
    public static final String ALL_PLATFORMS = "all_platforms";
    public static final String ONLY_ANDROID = "only_android";
    public static final String ONLY_IOS = "only_ios";

    // categories
    public static final String CATEGORY_LOGIN = "login";
    public static final String CATEGORY_ONBOARDING = "onboarding";
    public static final String CATEGORY_BURGER_MENU = "burger_menu";
    public static final String CATEGORY_DASHBOARD = "dashboard";
    public static final String CATEGORY_MARKET_AND_LANGUAGE = "market_and_language";
    public static final String CATEGORY_HELP_AND_SUPPORT = "help_and_support";
    public static final String CATEGORY_NOTIFICATIONS = "notifications";
    public static final String CATEGORY_SETTINGS = "settings";
    public static final String CATEGORY_SOCIAL_HUB = "social_hub";
    public static final String CATEGORY_SHARE_YOUR_LOOK = "user_content";
    public static final String USER_CONTENT = "user_content";

    // markets
    public static final String MARKET_ARGENTINA = "argentina";
    public static final String MARKET_CHILE = "chile";
    public static final String MARKET_COLOMBIA = "colombia";
    public static final String MARKET_ECUADOR = "ecuador";
    public static final String MARKET_EGYPT = "egypt";
    public static final String MARKET_FINLAND = "finland";
    public static final String MARKET_GEORGIA = "georgia";
    public static final String MARKET_GREECE = "greece";
    public static final String MARKET_INDIA = "india";
    public static final String MARKET_KAZAKHSTAN = "kazakhstan";
    public static final String MARKET_KYRGYZSTAN = "kyrgyzstan";
    public static final String MARKET_MEXICO = "mexico";
    public static final String MARKET_MOROCCO = "morocco";
    public static final String MARKET_PERU = "peru";
    public static final String MARKET_PHILIPPINES = "philippines";
    public static final String MARKET_PORTUGAL = "portugal";
    public static final String MARKET_RUSSIA = "russia";
    public static final String MARKET_SAUDIARABIA = "saudi_arabia";
    public static final String MARKET_SOUTHAFRICA = "south_africa";
    public static final String MARKET_SPAIN = "spain";
    public static final String MARKET_TURKEY = "turkey";
    public static final String MARKET_UKRAINE = "ukraine";
    public static final String MARKET_UNITEDKINGDOM = "united_kingdom";
    public static final String MARKET_URUGUAY = "uruguay";
  }

  public static final class Timeouts {
    public static final int HALF_SECOND_MILLI = 500;
    public static final int ONE_SECOND_MILLI = 1000;
    public static final int TWO_SECONDS_MILLI = 2000;
    public static final int THREE_SECONDS_MILLI = 3000;
    public static final int TEN_SECONDS_MILLI = 10000;
    public static final int FIFTY_SECONDS_MILLI = 50000;
  }

  public static final class Scroll {
    public static final int X_COORDINATE = 20;
    public static final int ONE_TIME = 1;
    public static final int INITIAL_BROCHURE_ICON_NUMBER = 1;
    public static final int MAX_SCROLL_COUNT = 10;
  }

  public static final class TestData {
    public static final String SEARCH_TEXT = "Jumper";
    public static final String ACCOUNT_NUMBER = "308";
    public static final String PASSWORD = "testteam";
    public static final String VALID_PIN_CODE = "1111";
    public static final String INVALID_PIN_CODE = "0000";
    public static final String NATIVE_APP_CONTEXT_NAME = "NATIVE_APP";
    public static final String BUNDLE_ID_IOS = "com.avon.on.uat";
    public static final String BUNDLE_ID_ANDROID = "com.avon.avonon.uat";
  }

  public static final class ErrorMessages {
    public static final String INCORRECT_CREDENTIALS =
        "You seem to have entered the incorrect account number or password";
    public static final String INCORRECT_CREDENTIALS_SPANISH =
        "Lo sentimos, tus datos de acceso no son correctos. Inténtalo nuevamente.";

    public static final String PIN_CODE_DOES_NOT_MATCH = "PIN code did not match";
  }

  public static final class Labels {
    public static final String IM_BROCHURES = "IM Brochures";
  }

  public static final class Excel {
    public static final String FILE_LOCATION = "./src/main/resources/MarketsLanguages.xlsx";
    public static final int DEFAULT_LANGUAGE_SHEET = 0;
    public static final int ELEMENT_STRINGS_SHEET = 1;

    // MarketDefaultLanguageSheet
    public static final int MARKET_COLUMN = 0;
    public static final int DEFAULT_LANGUAGE_COLUMN = 1;
    public static final int ACCOUNT_COLUMN = 3;
    public static final int PASSWORD_COLUMN = 4;
  }
}
