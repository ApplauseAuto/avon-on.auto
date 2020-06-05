package com.applause.auto.test.dashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.chunks.MainPromoChunk;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class DashboardMainPromoTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_DASHBOARD, TestNGGroups.ALL_PLATFORMS},
      description = "C1292898")
  public void testDashboardMainPromo() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("## Verify main promo is displayed");
    MainPromoChunk mainPromo = dashboardView.getMainPromo();
    Assert.assertTrue(mainPromo.promoTitleIsDisplayed(), "Main promo is not displayed.");

    LOGGER.info("## Verify the main promo can be shared");
    Assert.assertTrue(mainPromo.promoCanBeShared(), "Main promo can't be shared.");
  }
}
