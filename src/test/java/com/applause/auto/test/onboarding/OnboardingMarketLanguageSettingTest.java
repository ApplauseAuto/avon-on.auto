package com.applause.auto.test.onboarding;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.SelectMarketView;
import com.applause.auto.test.BaseTest;

public class OnboardingMarketLanguageSettingTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_ONBOARDING, TestNGGroups.ALL_PLATFORMS},
      description = "C1361625")
  public void testOnboardingMarketLanguageSetting() {
    LOGGER.info("# Launch the app.");
    SelectMarketView selectMarketView = launchApp();

    LOGGER.info("# Set the market.");
    selectMarketView.selectMarket(Market.getMarketName());

    LOGGER.info("## Verify the language is preselected correctly.");
    Assert.assertEquals(
        selectMarketView.getCurrentLanguage(),
        Market.getlanguage(),
        "The language was not correctly set for the market.");

    if (Market.getMarketName().equals("Saudi Arabia")) {
      LOGGER.info("# This market has 2 languages - try to select English.");
      selectMarketView.selectLanguage("English.");

      LOGGER.info("## Verify the language is preselected correctly.");
      Assert.assertEquals(
          selectMarketView.getCurrentLanguage(),
          "English",
          "The language was not correctly set for the market.");
    }
  }
}
