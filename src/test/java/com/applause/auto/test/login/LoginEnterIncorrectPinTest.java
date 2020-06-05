package com.applause.auto.test.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.pageframework.chunks.AvonDialogChunk;
import com.applause.auto.pageframework.testdata.TestConstants.TestData;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.PinView;
import com.applause.auto.pageframework.views.SelectMarketView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class LoginEnterIncorrectPinTest extends BaseTest {

  /**
   * Jira URL: https://appauto.atlassian.net/browse/AVON-49
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1755693
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_LOGIN, TestNGGroups.ALL_PLATFORMS},
      description = "C1755693")
  public void testLoginEnterIncorrectPin() {
    LOGGER.info("# Launch the app, login with a pin, and close and reopen the app.");
    LoginHelper.successfullyLogin();
    PinView pinView = closeAndReopenApp(PinView.class);

    LOGGER.info("# Enter the incorrect pin 4 times.");
    pinView.enterPin(TestData.INVALID_PIN_CODE);
    pinView.enterPin(TestData.INVALID_PIN_CODE);

    if (EnvironmentUtil.getInstance().getIsMobileAndroid()) {
      /**
       * TODO: Android allows one extra attempt before it kicks the user out. This is different from
       * iOS. Is this expected?
       */
      pinView.enterPin(TestData.INVALID_PIN_CODE);
      LoginView loginView = pinView.enterPin(TestData.INVALID_PIN_CODE);

      LOGGER.info("## Verify user is navigated back to the Login view.");
      Assert.assertTrue(
          loginView.loginButtonIsDisplayed(),
          "Login button is not displayed, indicating user has not been redirected to correct page, after incorrect pin attempts.");
    } else {
      pinView.enterPin(TestData.INVALID_PIN_CODE);
      DeviceChunkFactory.create(AvonDialogChunk.class).tapOkButton();

      LOGGER.info("## Verify user is navigated back to the Select Market view.");
      Assert.assertTrue(
          DeviceViewFactory.create(SelectMarketView.class).marketSelectDropdownIsDisplayed(),
          "Market select dropdown is not displayed, indicating user has not been redirected to correct page, after incorrect pin attempts.");
    }
  }
}
