package com.applause.auto.test.helpandsupport;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.*;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class HelpFaqTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_HELP_AND_SUPPORT, TestNGGroups.ALL_PLATFORMS},
      description = "C1451583")
  public void testHelpFaq() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Help & Support from main menu.");
    HelpAndSupportView helpAndSupportView = mainMenuView.openHelpAndSupport();

    LOGGER.info("# Open FAQ option.");
    FAQView faqView = helpAndSupportView.clickFAQText();

    LOGGER.info("## Verify that categories are not empty.");
    Assert.assertFalse(faqView.hasListEmptyCategories(), "Categories are empty");

    LOGGER.info("# Select first category");
    MyAccountView myAccountView = faqView.openMyAccount();

    LOGGER.info("## Verify that questions are not empty.");
    Assert.assertFalse(myAccountView.hasListEmptyQuestions(), "Questions are empty");

    LOGGER.info("# Select first question.");
    myAccountView = myAccountView.openFirstQuestion();

    LOGGER.info("## Verify question answer is not empty.");
    Assert.assertFalse(
        myAccountView.getFirstQuestionDescriptionTextString().isEmpty(),
        "Question answer string is empty.");

    LOGGER.info("## Verify 'Was it useful' text label is not empty.");
    Assert.assertFalse(
        myAccountView.getWasThisUsefulTextString().isEmpty(), "'Was it useful' string is empty.");

    LOGGER.info("## Verify 'Yes' text label is not empty.");
    Assert.assertFalse(myAccountView.getYesTextString().isEmpty(), "'Yes' text label is empty.");

    LOGGER.info("## Verify 'No' text label is not empty.");
    Assert.assertFalse(myAccountView.getNoTextString().isEmpty(), "'No' text label is empty.");
  }
}
