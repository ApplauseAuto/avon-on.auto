package com.applause.auto.test.settings;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.PrivacyPolicyView;
import com.applause.auto.pageframework.views.SettingsView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class SettingsViewPrivacyPolicyTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_SETTINGS, TestNGGroups.ALL_PLATFORMS},
      description = "C1451578")
  public void testSettingsViewPrivacyPolicy() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Settings from main menu.");
    SettingsView settingsView = mainMenuView.openSettings();

    LOGGER.info("# Select Privacy policy.");
    PrivacyPolicyView privacyPolicyView = settingsView.openPrivacyPolicy();

    LOGGER.info("## Verify privacy policy message is displayed.");
    Assert.assertFalse(
        privacyPolicyView.getPrivacyPolicyTextString().isEmpty(),
        "Privacy policy message is not displayed.");
  }
}
