package com.applause.auto.test.onboarding;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.PinView;
import com.applause.auto.pageframework.views.SelectMarketView;
import com.applause.auto.test.BaseTest;

public class OnboardingIncorrectPinTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_ONBOARDING, TestNGGroups.ALL_PLATFORMS},
      description = "C1292896")
  public void testOnboardingIncorrectPin() {
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
    pinView = pinView.enterUnmatchingPins();

    LOGGER.info("## Verify correct error message is displayed.");
    Assert.assertFalse(
        pinView.getPinConfirmationError().isEmpty(),
        "Pin confirmation error message is not displayed.");
  }
}
