package com.applause.auto.test.socialhub;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.chunks.IMBrochuresChunk;
import com.applause.auto.pageframework.chunks.NativeShareOptionsChunk;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.socialsharinghub.SocialSharingHubView;
import com.applause.auto.pageframework.webviews.IMBrochureWebView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class SocialHubBrochuresViewTest extends BaseTest {

  /**
   * ID: AVON-40
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-40
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1683727
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_SOCIAL_HUB, TestNGGroups.ALL_PLATFORMS},
      description = "C1683727")
  public void testSocialHubBrochuresView() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("## Verify 'Burger Menu' icon is displayed.");
    Assert.assertTrue(
        dashboardView.burgerIconIsDisplayed(), "The 'Burger menu' icon is not displayed.");

    LOGGER.info("# Click the Burger menu icon.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("## Verify Social Media Hub view is displayed.");
    SocialSharingHubView socialSharingHubView = mainMenuView.openSocialMediaHub();

    LOGGER.info("# Scroll to 'IM Brochures'.");
    IMBrochuresChunk imBrochuresChunk = socialSharingHubView.scrollToIMBrochuresChunk();

    LOGGER.info("# Scroll left and right through the carousel to see all available brochures.");
    imBrochuresChunk.swipeBrochures();

    LOGGER.info("# Tap first 'Open IM Brochure' button.");
    IMBrochureWebView imBrochureWebView = imBrochuresChunk.tapFirstOpenIMBrochureButton();

    LOGGER.info("## Verify web view with brochure details is displayed.");
    Assert.assertTrue(
        Helper.webviewIsDisplayed(), "Web view with brochure details is not displayed.");

    LOGGER.info("# Close web view.");
    imBrochuresChunk = imBrochureWebView.closeBrochureWebView();

    LOGGER.info("# Tap 'Share me' button.");
    NativeShareOptionsChunk nativeShareOptionsChunk = imBrochuresChunk.tapFirstShareMeButton();

    LOGGER.info("## Verify native share options are displayed.");
    Assert.assertTrue(
        nativeShareOptionsChunk.shareOptionsDisplayed(),
        "The native share options are not displayed.");

    LOGGER.info("# Close share options.");
    socialSharingHubView = nativeShareOptionsChunk.tapCloseButton();

    LOGGER.info("# Tap 'Close' button on 'Social Sharing Hub' view.");
    dashboardView = socialSharingHubView.closeSocialSharingHubView();

    LOGGER.info("## Verify the 'Dashboard' view is displayed.");
    Assert.assertTrue(dashboardView.dashboardTitleIsDisplayed(), LOGGER.getLastErrorMessage());
  }
}
