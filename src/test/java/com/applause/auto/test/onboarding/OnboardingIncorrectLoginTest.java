package com.applause.auto.test.onboarding;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.testdata.TestConstants.TestData;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.IncorrectCredentialsView;
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.SelectMarketView;
import com.applause.auto.test.BaseTest;

public class OnboardingIncorrectLoginTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_ONBOARDING, TestNGGroups.ALL_PLATFORMS},
      description = "C1292897")
  public void testOnboardingIncorrectLogin() {
    LOGGER.info("# Launch the app.");
    SelectMarketView selectMarketView = launchApp();

    LOGGER.info("# Go to the login view.");
    LoginView loginView =
        selectMarketView
            .selectMarketAndLanguage(Market.getMarketName(), Market.getlanguage())
            .continueToLoginView();

    LOGGER.info("# Log into the app.");
    IncorrectCredentialsView incorrectCredentialsView =
        loginView.attemptIncorrectLogin(TestData.ACCOUNT_NUMBER, "wrong");

    LOGGER.info("## Verify the message is correct.");
    Assert.assertFalse(
        incorrectCredentialsView.getErrorMessageString().isEmpty(),
        "No error message string is returned.");

    LOGGER.info("# Go back to login view and verify view is displayed.");
    loginView = incorrectCredentialsView.returnToLoginView();
  }
}
