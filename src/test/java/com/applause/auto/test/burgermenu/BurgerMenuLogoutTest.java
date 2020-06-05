package com.applause.auto.test.burgermenu;

import org.testng.annotations.Test;

import com.applause.auto.pageframework.chunks.ModelPopupChunk;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class BurgerMenuLogoutTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_BURGER_MENU, TestNGGroups.ALL_PLATFORMS},
      description = "C1683726")
  public void testBurgerMenuLogout() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Logout from main menu and verify modal popup is displayed.");
    ModelPopupChunk modelPopupChunk = mainMenuView.clickOnLogout();

    LOGGER.info("# Logout from app, and verify correct view is displayed.");
    modelPopupChunk.clickOnContinue();
  }
}
