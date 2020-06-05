package com.applause.auto.test.helper;

import org.testng.Assert;

import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.testdata.TestConstants.TestData;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.PinView;
import com.applause.auto.pageframework.views.SelectMarketView;
import com.applause.auto.test.BaseTest;

public class LoginHelper extends BaseTest {

  /**
   * Logs in to specific market account
   *
   * @return DashboardView
   */
  public static DashboardView successfullyLogin() {
    LOGGER.info("# Login to app.");
    LOGGER.info("@ Launch the app");
    SelectMarketView selectMarketView = launchApp();

    LOGGER.info("@ Go to the login view");
    LoginView loginView =
        selectMarketView
            .selectMarketAndLanguage(Market.getMarketName(), Market.getlanguage())
            .continueToLoginView();

    LOGGER.info("@ Log into the app");
    PinView pinView = loginView.login(Market.getAccountnumber(), Market.getPassword());

    LOGGER.info("@ Create a pin");
    DashboardView dashboardView = pinView.enterAndConfirmPin(TestData.VALID_PIN_CODE);

    LOGGER.info("@ Verify dashboard title is displayed.");
    Assert.assertTrue(dashboardView.dashboardTitleIsDisplayed(), LOGGER.getLastErrorMessage());
    return dashboardView;
  }
}
