package com.applause.auto.test.notifications;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.NotificationsView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class NotificationsViewNotificationsTest extends BaseTest {

  @Test(
      enabled = false,
      groups = {TestNGGroups.CATEGORY_NOTIFICATIONS, TestNGGroups.ALL_PLATFORMS},
      description = "C1643652")
  public void testNotificationsViewNotifications() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("## Verify notifications icon is displayed.");
    Assert.assertTrue(
        dashboardView.notificationsIconIsDisplayed(), "The notifications icon is not displayed");

    if (env.getIsMobileAndroid()) {
      LOGGER.info("## Verify number of unread notifications is displayed.");
      Assert.assertTrue(dashboardView.numberOfUnreadNotificationsIsDisplayed());
    }

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Open 'Notifications' option.");
    NotificationsView notificationsView = mainMenuView.openNotifications();

    LOGGER.info("## Verify 'Notifications view' is displayed.");
    Assert.assertTrue(
        notificationsView.notificationsViewIsDisplayed(), "The notification view is not displayed");

    LOGGER.info("## Verify Number of unread notifications match (## new).");
    Assert.assertTrue(
        notificationsView.numberOfUnreadNotificationsIsDisplayed(),
        "The number of unread messages doesn't match (## new)");
  }
}
