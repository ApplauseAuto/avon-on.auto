package com.applause.auto.test.onboarding;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.SelectMarketView;
import com.applause.auto.test.BaseTest;

public class OnboardingForgottenLoginDetailsLinkTest extends BaseTest {

  /**
   * Jira URL: https://appauto.atlassian.net/browse/AVON-47
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1755690
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_ONBOARDING, TestNGGroups.ALL_PLATFORMS},
      description = "C1755690")
  public void testOnboardingForgottenLoginDetailsLink() {
    LOGGER.info("# Launch the app, select the relevant market/language, and click 'Continue'.");
    SelectMarketView selectMarketView = launchApp();
    LoginView loginView =
        selectMarketView
            .selectMarketAndLanguage(Market.getMarketName(), Market.getlanguage())
            .continueToLoginView();

    LOGGER.info("# Click 'Forgotten login details?' link.");
    loginView.tapForgottenLoginDetails();

    LOGGER.info("## Verify webview is opened.");
    Assert.assertTrue(Helper.webviewIsDisplayed(), "Webview is not present.");
  }
}
