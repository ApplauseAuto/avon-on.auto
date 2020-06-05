package com.applause.auto.test.helpandsupport;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.HelpAndSupportView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class HelpAndSupportTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_HELP_AND_SUPPORT, TestNGGroups.ALL_PLATFORMS},
      description = "C1451582")
  public void testHelpAndSupport() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Help & Support from main menu.");
    HelpAndSupportView helpAndSupportView = mainMenuView.openHelpAndSupport();

    LOGGER.info("## Verify the 'Frequently Asked Questions' option is displayed.");
    Assert.assertTrue(
        helpAndSupportView.isFAQItemDisplayed(),
        "The 'Frequently Asked Questions' option is not displayed");

    LOGGER.info("## Verify the 'Call Us' option is displayed.");
    Assert.assertTrue(
        helpAndSupportView.isCallUsItemDisplayed(), "The 'Call Us' option is not displayed");

    LOGGER.info("## Verify the 'Email Avon option' is displayed.");
    Assert.assertTrue(
        helpAndSupportView.isEmailAvonItemDisplayed(), "The 'Email Avon' option is not displayed");

    LOGGER.info("# Tap x button.");
    dashboardView = helpAndSupportView.closeHelpAndSupport();

    LOGGER.info("## Verify welcome message.");
    Assert.assertTrue(dashboardView.dashboardTitleIsDisplayed(), LOGGER.getLastErrorMessage());
  }
}
