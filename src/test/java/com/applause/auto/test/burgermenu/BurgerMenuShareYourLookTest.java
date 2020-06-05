package com.applause.auto.test.burgermenu;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.ShareYourLookView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class BurgerMenuShareYourLookTest extends BaseTest {

  /**
   * ID: AVON-58
   *
   * <p>Jira URL: https://appauto.atlassian.net/browse/AVON-57
   *
   * <p>TestRail URL: https://appauto.testrail.net/index.php?/cases/view/1833821
   */
  @Test(
      groups = {TestNGGroups.CATEGORY_BURGER_MENU, TestNGGroups.ALL_PLATFORMS},
      description = "C1833821")
  public void testBurgerMenuShareYourLookView() {
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
  }
}
