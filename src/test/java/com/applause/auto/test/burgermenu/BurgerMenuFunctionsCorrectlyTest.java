package com.applause.auto.test.burgermenu;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class BurgerMenuFunctionsCorrectlyTest extends BaseTest {

  /**
   * ID: AVON-37
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-37
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1683724
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_BURGER_MENU, TestNGGroups.ALL_PLATFORMS},
      description = "C1683724")
  public void testBurgerMenuFunctionsCorrectly() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("## Verify 'Burger Menu' icon is displayed.");
    Assert.assertTrue(
        dashboardView.burgerIconIsDisplayed(), "The 'Burger menu' icon is not displayed.");

    LOGGER.info("# Tap the Burger menu icon.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("## Verify 'Burger Menu' is displayed.");
    Assert.assertTrue(mainMenuView.burgerMenuIsDisplayed(), "The 'Burger menu' is not displayed.");

    LOGGER.info("## Verify user's full name is displayed.");
    Assert.assertTrue(
        mainMenuView.userFullNameIsDisplayed(), "The user's full name is not displayed.");

    LOGGER.info("## Verify deadline for ordering products is displayed.");
    Assert.assertTrue(
        mainMenuView.deadlineOfOrderingProductsIsDisplayed(),
        "The deadline for ordering products is not displayed.");

    LOGGER.info("# Tap 'Close' button.");
    dashboardView = mainMenuView.tapCloseButton();

    LOGGER.info("## Verify 'Dashboard' view is displayed.");
    Assert.assertTrue(dashboardView.dashboardTitleIsDisplayed(), LOGGER.getLastErrorMessage());

    LOGGER.info("# Tap the Burger menu icon.");
    mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Tap 'Home' button.");
    dashboardView = mainMenuView.tapHomeButton();

    LOGGER.info("## Verify 'Dashboard' view is displayed.");
    Assert.assertTrue(dashboardView.dashboardTitleIsDisplayed(), LOGGER.getLastErrorMessage());
  }
}
