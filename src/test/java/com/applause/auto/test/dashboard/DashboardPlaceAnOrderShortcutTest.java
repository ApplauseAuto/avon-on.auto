package com.applause.auto.test.dashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.pageframework.chunks.ErrorPopupChunk;
import com.applause.auto.pageframework.chunks.ShortcutMenuChunk;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.PlaceAnOrderView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class DashboardPlaceAnOrderShortcutTest extends BaseTest {

  /**
   * ID: AVON-53
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-53
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1833814
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_DASHBOARD, TestNGGroups.ALL_PLATFORMS},
      description = "C1833814")
  public void testDashboardPlaceAnOrderShortcut() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap the '+' icon");
    ShortcutMenuChunk shortcutMenuChunk = dashboardView.openShortcutMenu();

    LOGGER.info("## Verify 'Add an Order' option is displayed.");
    Assert.assertTrue(
        shortcutMenuChunk.placeAnOrderOptionIsDisplayed(),
        "Place an Order option is not displayed.");

    LOGGER.info("## Verify 'Social media hub' option is displayed.");
    Assert.assertTrue(
        shortcutMenuChunk.socialMediaHubOptionIsDisplayed(),
        "Social media hub option is not displayed");

    LOGGER.info("## Verify 'Share your look' option is displayed.");
    Assert.assertTrue(
        shortcutMenuChunk.shareYourLookOptionIsDisplayed(),
        "Share your look option is not displayed");

    LOGGER.info("# Tap 'Add an Order' option.");
    PlaceAnOrderView placeAnOrderView = shortcutMenuChunk.tapPlaceAnOrderButton();

    LOGGER.info("## Verify that 'Place An Order' view is displayed.");
    if (env.getIsMobileIOS()) {
      ErrorPopupChunk errorPopupChunk = DeviceChunkFactory.create(ErrorPopupChunk.class);
      placeAnOrderView = errorPopupChunk.tapOk();
    }
    Assert.assertTrue(Helper.webviewIsDisplayed(), "The 'Place An Order' view is not displayed");

    LOGGER.info("# Tap 'x' close button");
    placeAnOrderView.tapCloseButton();

    LOGGER.info("# Click 'Ok' button");
    dashboardView = placeAnOrderView.tapOk();
  }
}
