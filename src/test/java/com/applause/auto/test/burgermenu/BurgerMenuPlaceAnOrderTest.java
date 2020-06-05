package com.applause.auto.test.burgermenu;

import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.PlaceAnOrderView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class BurgerMenuPlaceAnOrderTest extends BaseTest {

  @Test(
      groups = {TestNGGroups.CATEGORY_BURGER_MENU, TestNGGroups.ALL_PLATFORMS},
      description = "C1683725")
  public void testBurgerMenuPlaceAnOrder() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Select Place an Order from main menu");
    PlaceAnOrderView placeAnOrderView = mainMenuView.tapPlaceAnOrder();

    LOGGER.info("# Click 'x' close button");
    placeAnOrderView.tapCloseButton();

    LOGGER.info("# Click 'Cancel' button");
    placeAnOrderView.tapCancel();

    LOGGER.info("# Click 'x' close button");
    placeAnOrderView.tapCloseButton();

    LOGGER.info("# Click 'Ok' button");
    dashboardView = placeAnOrderView.tapOk();
  }
}
