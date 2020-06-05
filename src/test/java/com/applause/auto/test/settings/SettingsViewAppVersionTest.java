package com.applause.auto.test.settings;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.SettingsView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class SettingsViewAppVersionTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_SETTINGS, TestNGGroups.ALL_PLATFORMS},
      description = "C1451579")
  public void testSettingsViewAppVersion() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Settings from main menu.");
    SettingsView settingView = mainMenuView.openSettings();

    LOGGER.info("# Text is displayed for App Version.");
    Assert.assertFalse(
        settingView.getAppVersionTextString().isEmpty(), "App version text is not displayed.");

    LOGGER.info("# Click x button, and verify dashboard is displayed again.");
    dashboardView = settingView.closeSettings();
  }
}
