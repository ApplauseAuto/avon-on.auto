package com.applause.auto.test.dashboard;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class DashboardItemsTest extends BaseTest {

  /**
   * ID: AVON-5
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-5
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1363386
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_DASHBOARD, TestNGGroups.ALL_PLATFORMS},
      description = "C1363386")
  public void testDashboardItemsAreDisplayed() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("## Verify user can see name at the top.");
    Assert.assertTrue(!dashboardView.getUserName().isEmpty(), "Username should be displayed");

    LOGGER.info("## Verify user can see the title of the current campaign.");
    Assert.assertTrue(!dashboardView.getCampaignName().isEmpty(), "Campaign Should be displayed");

    LOGGER.info("## Verify user can see the deadline for ordering products.");
    Assert.assertTrue(!dashboardView.getDeadlineText().isEmpty(), "Deadline should be displayed.");

    LOGGER.info("## Verify user can see how many days are left of the campaign.");
    Assert.assertTrue(
        !dashboardView.getNumberOfDaysLeftText().isEmpty(), "Number of days should be displayed.");
  }
}
