package com.applause.auto.pageframework.testdata;

import java.util.HashMap;

public class Market {

  private static HashMap<String, String> languageStrings = new HashMap<String, String>();
  private static String marketName;
  private static String language;
  private static String accountnumber;
  private static String password;

  public static String getAccountnumber() {
    return accountnumber;
  }

  public static void setAccountnumber(String accountnumber) {
    Market.accountnumber = accountnumber;
  }

  public static String getPassword() {
    return password;
  }

  public static void setPassword(String password) {
    Market.password = password;
  }

  public static String getMarketName() {
    return marketName;
  }

  public static void setMarketName(String marketName) {
    Market.marketName = marketName;
  }

  public static String getlanguage() {
    return language;
  }

  public static void setlanguage(String language) {
    Market.language = language;
  }

  public static HashMap<String, String> getLanguageStrings() {
    return languageStrings;
  }

  public static void setLanguageStrings(HashMap<String, String> languageStrings) {
    Market.languageStrings = languageStrings;
  }
}
