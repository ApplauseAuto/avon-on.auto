package com.applause.auto.test.settings;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.ChangeLanguageView;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.SettingsView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class SettingsChangeLanguageTest extends BaseTest {

  @Test(
      groups = {
        TestNGGroups.CATEGORY_SETTINGS,
        TestNGGroups.ALL_PLATFORMS,
        TestNGGroups.MARKET_EGYPT,
        TestNGGroups.MARKET_KYRGYZSTAN,
        TestNGGroups.MARKET_SAUDIARABIA,
        TestNGGroups.MARKET_KAZAKHSTAN,
        TestNGGroups.MARKET_MOROCCO
      },
      description = "C1451581")
  public void testSettingsChangeLanguage() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Settings from main menu.");
    SettingsView settingsView = mainMenuView.openSettings();

    LOGGER.info("# Select 'Language' option.");
    ChangeLanguageView changeLanguageView = settingsView.openLanguageOption();
    String predefinedLanguage = changeLanguageView.getCurrentlySelectedLanguageString();

    LOGGER.info("# Change language.");
    changeLanguageView.changeLanguage();

    LOGGER.info("## Verify that language text changed.");
    Assert.assertNotEquals(
        predefinedLanguage,
        changeLanguageView.getCurrentlySelectedLanguageString(),
        "The language text not changed");
  }
}
