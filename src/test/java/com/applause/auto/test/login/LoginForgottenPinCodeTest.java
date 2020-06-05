package com.applause.auto.test.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.PinView;
import com.applause.auto.pageframework.views.SelectMarketView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class LoginForgottenPinCodeTest extends BaseTest {

  /**
   * Jira URL: https://appauto.atlassian.net/browse/AVON-51
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1755694
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_LOGIN, TestNGGroups.ALL_PLATFORMS},
      description = "C1755694")
  public void testLoginForgottenPinCode() {
    LOGGER.info("# Launch the app, login with a pin, and close and reopen the app.");
    LoginHelper.successfullyLogin();
    PinView pinView = closeAndReopenApp(PinView.class);

    LOGGER.info("# Tap 'Forgotten PIN code?' button.");

    if (EnvironmentUtil.getInstance().getIsMobileAndroid()) {
      LoginView loginView = pinView.tapForgottenPinCode();

      LOGGER.info("## Verify user is navigated back to the Login view.");
      Assert.assertTrue(
          loginView.loginButtonIsDisplayed(),
          "Login button is not displayed, indicating user has not been redirected to correct page, after incorrect pin attempts.");
    } else {
      SelectMarketView selectMarketView = pinView.tapForgottenPinCode();

      LOGGER.info("## Verify user is navigated back to the Select Market view.");
      Assert.assertTrue(
          selectMarketView.marketSelectDropdownIsDisplayed(),
          "Market Select dropdown is not displayed, indicating user has not been redirected to correct page, after incorrect pin attempts.");
    }
  }
}
