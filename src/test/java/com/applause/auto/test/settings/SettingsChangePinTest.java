package com.applause.auto.test.settings;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.*;
import com.applause.auto.test.BaseTest;

public class SettingsChangePinTest extends BaseTest {

  private String oldPinCode = "1111";
  private String newPinCode = "2222";

  @Test(
      groups = {TestNGGroups.CATEGORY_SETTINGS, TestNGGroups.ALL_PLATFORMS},
      description = "C1451576")
  public void testSettingsChangePin() {
    LOGGER.info("# Launch the app.");
    SelectMarketView selectMarketView = launchApp();

    LOGGER.info("# Go to the login view.");
    LoginView loginView =
        selectMarketView
            .selectMarketAndLanguage(Market.getMarketName(), Market.getlanguage())
            .continueToLoginView();

    LOGGER.info("# Log into the app.");
    PinView pinView = loginView.login(Market.getAccountnumber(), Market.getPassword());

    LOGGER.info("# Create a pin.");
    DashboardView dashboardView = pinView.enterAndConfirmPin(oldPinCode);

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Settings from main menu.");
    SettingsView settingsView = mainMenuView.openSettings();

    LOGGER.info("# Change Pin.");
    pinView = settingsView.openCreatePinOption();

    LOGGER.info("# Enter old pin.");
    pinView.enterPin(oldPinCode);

    LOGGER.info("# Enter new pin.");
    pinView.enterPin(newPinCode);

    LOGGER.info("# Confirm new pin.");
    pinView.enterPin(newPinCode);

    LOGGER.info("## Verify that 'Confirmation' message is displayed.");
    Assert.assertFalse(
        pinView.getPinConfirmationSuccess().isEmpty(),
        "Pin confirmation message is not displayed.");

    LOGGER.info("# Tap 'OK' button.");
    pinView.tapOkButton();

    LOGGER.info("## Verify that 'Settings' view is displayed.");
    Assert.assertTrue(settingsView.settingTitleElementExists(), "Settings view is not displayed.");
  }
}
