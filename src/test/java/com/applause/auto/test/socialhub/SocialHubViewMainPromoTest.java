package com.applause.auto.test.socialhub;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.socialsharinghub.SocialSharingHubView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class SocialHubViewMainPromoTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_DASHBOARD, TestNGGroups.ALL_PLATFORMS},
      description = "C1292898")
  public void testSocialHubViewMainPromo() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Open main promo.");
    SocialSharingHubView socialSharingHubView = dashboardView.getMainPromo().viewPromo();

    LOGGER.info("## Verify main promo is displayed.");
    Assert.assertTrue(socialSharingHubView.mainPromoIsDisplayed(), "Main promo is not displayed.");

    LOGGER.info("## Verify the main promo can be shared.");
    Assert.assertTrue(socialSharingHubView.mainPromoCanBeShared(), "Main promo can not be shared.");
  }
}
