package com.applause.auto.test.helpandsupport;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.HelpAndSupportView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.NativeEmailView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class HelpEmailTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_HELP_AND_SUPPORT, TestNGGroups.ONLY_ANDROID},
      description = "C1451585")
  public void testHelpEmail() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Help & Support from main menu/");
    HelpAndSupportView helpAndSupportView = mainMenuView.openHelpAndSupport();

    LOGGER.info("# Open 'Email Avon' option.");
    NativeEmailView emailView = helpAndSupportView.clickEmailText();

    LOGGER.info("## Verify that 'Avon' email is predefined.");
    Assert.assertFalse(
        emailView.getToEmailString().isEmpty(), "The predefined email is not displayed");
  }
}
