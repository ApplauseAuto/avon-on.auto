package com.applause.auto.test.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestData;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.PinView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class LoginRelaunchAppAndEnterPinTest extends BaseTest {

  /**
   * Jira URL: https://appauto.atlassian.net/browse/AVON-
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1939495
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_LOGIN, TestNGGroups.ALL_PLATFORMS},
      description = "C1939495")
  public void testLoginRelaunchAppAndEnterPin() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Close and re-open app.");
    PinView pinView = closeAndReopenApp(PinView.class);

    LOGGER.info("# Enter PIN to gain access again.");
    dashboardView = pinView.enterPinToLogin(TestData.VALID_PIN_CODE);

    LOGGER.info("## Verify dashboard is displayed.");
    Assert.assertTrue(dashboardView.dashboardTitleIsDisplayed(), LOGGER.getLastErrorMessage());
  }
}
