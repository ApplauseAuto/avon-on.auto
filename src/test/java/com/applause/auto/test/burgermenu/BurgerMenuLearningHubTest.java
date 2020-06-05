package com.applause.auto.test.burgermenu;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class BurgerMenuLearningHubTest extends BaseTest {

  /**
   * ID: AVON-57
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-57
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1833820
   */
  @Test(
      groups = {
        TestNGGroups.CATEGORY_BURGER_MENU,
        TestNGGroups.ALL_PLATFORMS,
        TestNGGroups.MARKET_UNITEDKINGDOM,
        TestNGGroups.MARKET_SOUTHAFRICA,
        TestNGGroups.MARKET_ARGENTINA,
        TestNGGroups.MARKET_CHILE,
        TestNGGroups.MARKET_URUGUAY
      },
      description = "C1833820")
  public void testBurgerMenuLearningHubView() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap the Burger menu icon.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("## Verify 'Burger Menu' is displayed.");
    Assert.assertTrue(mainMenuView.burgerMenuIsDisplayed(), "The 'Burger menu' is not displayed");

    LOGGER.info("# Tap 'Learning Hub' button.");
    mainMenuView.openLearningHub();

    LOGGER.info("## Verify Social 'Learning Hub' web view is displayed.");
    Assert.assertTrue(Helper.webviewIsDisplayed(), "'Learning Hub' web view  is not displayed");
  }
}
