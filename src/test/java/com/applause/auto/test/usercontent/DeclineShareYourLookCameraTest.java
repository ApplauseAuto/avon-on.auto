package com.applause.auto.test.usercontent;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.NativeCameraView;
import com.applause.auto.pageframework.views.ShareYourLookView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class DeclineShareYourLookCameraTest extends BaseTest {

  /**
   * ID: AVON-90
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-90
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/2247090
   */
  @Test(
      groups = {
        TestNGGroups.CATEGORY_SHARE_YOUR_LOOK,
        TestNGGroups.USER_CONTENT,
        TestNGGroups.ALL_PLATFORMS
      },
      description = "C2247090")
  public void testDeclineShareYourLookCamera() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap the Burger menu icon.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("## Verify 'Burger Menu' is displayed.");
    Assert.assertTrue(mainMenuView.burgerMenuIsDisplayed(), "The 'Burger menu' is not displayed");

    LOGGER.info("# Tap 'Share Your Look' button.");
    ShareYourLookView shareYourLookView = mainMenuView.openShareYourLook();

    LOGGER.info("## Verify 'Share Your Look' view is displayed.");
    Assert.assertTrue(
        shareYourLookView.shareYourLookViewIsDisplayed(),
        "'Share Your Look' view is not displayed");

    LOGGER.info("# Tap 'Camera' Button");
    NativeCameraView nativeCameraView = shareYourLookView.tapCameraButton();

    LOGGER.info("# Tap 'Deny/Decline' button");
    shareYourLookView = nativeCameraView.closeCameraView();

    LOGGER.info("## Verify 'Share Your Look' view is displayed.");
    Assert.assertTrue(
        shareYourLookView.shareYourLookViewIsDisplayed(),
        "'Share Your Look' view is not displayed");
  }
}
