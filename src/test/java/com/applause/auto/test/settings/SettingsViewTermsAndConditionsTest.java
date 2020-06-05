package com.applause.auto.test.settings;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.SettingsView;
import com.applause.auto.pageframework.views.termsandconditions.SettingsTermsAndConditionsView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class SettingsViewTermsAndConditionsTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_SETTINGS, TestNGGroups.ALL_PLATFORMS},
      description = "C1451577")
  public void testSettingsViewTermsAndConditions() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Settings from main menu.");
    SettingsView settingsView = mainMenuView.openSettings();

    LOGGER.info("# Select Terms and Conditions.");
    SettingsTermsAndConditionsView settingsTermsAndConditionsView =
        settingsView.openTermsAndConditions();

    LOGGER.info("## Verify terms and conditions text is displayed.");
    Assert.assertFalse(
        settingsTermsAndConditionsView.getTermsAndConditionsTextString().isEmpty(),
        "Terms and Condition text is not displayed.");
  }
}
