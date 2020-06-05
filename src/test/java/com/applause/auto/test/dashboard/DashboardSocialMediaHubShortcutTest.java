package com.applause.auto.test.dashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.chunks.ShortcutMenuChunk;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.socialsharinghub.SocialSharingHubView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class DashboardSocialMediaHubShortcutTest extends BaseTest {

  /**
   * ID: AVON-54
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-54
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1833815
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_DASHBOARD, TestNGGroups.ALL_PLATFORMS},
      description = "C1833815")
  public void testDashboardItemsAreDisplayed() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap the '+' icon");
    ShortcutMenuChunk shortcutMenuChunk = dashboardView.openShortcutMenu();

    LOGGER.info("## Verify 'Add an Order' option is displayed.");
    Assert.assertTrue(
        shortcutMenuChunk.placeAnOrderOptionIsDisplayed(), "Place an Order is not displayed.");

    LOGGER.info("## Verify 'Social media hub' option is displayed.");
    Assert.assertTrue(
        shortcutMenuChunk.socialMediaHubOptionIsDisplayed(), "Social media hub is not displayed");

    LOGGER.info("## Verify 'Share your look' option is displayed.");
    Assert.assertTrue(
        shortcutMenuChunk.shareYourLookOptionIsDisplayed(), "Share your look is not displayed");

    LOGGER.info("# Tap 'Social media hub' option.");
    SocialSharingHubView socialSharingHubView = shortcutMenuChunk.tapSocialMediaHubButton();

    LOGGER.info("## Verify that 'Social media hub' view is displayed.");
    Assert.assertTrue(
        socialSharingHubView.mainPromoIsDisplayed(),
        "The 'Social media hub' view is not displayed");

    LOGGER.info("# Tap 'X' close button");
    dashboardView = socialSharingHubView.closeSocialSharingHubView();
  }
}
