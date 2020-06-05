package com.applause.auto.test.settings;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.*;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class SettingsChangeMarketTest extends BaseTest {

  private String market =
      System.getProperty("market").equals("UnitedKingdom") ? "Ukraine" : "United Kingdom";

  @Test(
      groups = {TestNGGroups.CATEGORY_SETTINGS, TestNGGroups.ALL_PLATFORMS},
      description = "C1451580")
  public void testSettingsChangeMarket() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Settings from main menu.");
    SettingsView settingsView = mainMenuView.openSettings();

    LOGGER.info("# Select 'Market' option.");
    ChangeMarketView changeMarketView = settingsView.openMarketOption();

    LOGGER.info("# Change market.");
    SelectMarketView selectMarketView = changeMarketView.selectMarket(market);

    LOGGER.info("## Verify that selected market is populated.");
    Assert.assertEquals(
        market,
        selectMarketView.getCurrentMarket(),
        "Current market value "
            + selectMarketView.getCurrentMarket().equals(market)
            + " not equals to "
            + market);

    LOGGER.info("# Tap 'Continue' button.");
    LoginView loginView = selectMarketView.continueToLoginView();

    LOGGER.info("## Verify that 'Login' view is displayed.");
    Assert.assertTrue(loginView.loginButtonIsDisplayed(), "The 'Login' view is not displayed");
  }
}
