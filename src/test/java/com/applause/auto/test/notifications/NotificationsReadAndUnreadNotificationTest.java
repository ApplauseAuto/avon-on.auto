package com.applause.auto.test.notifications;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.chunks.NotificationRow;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.NotificationDetailsView;
import com.applause.auto.pageframework.views.NotificationsView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class NotificationsReadAndUnreadNotificationTest extends BaseTest {

  @Test(
      enabled = false,
      groups = {TestNGGroups.CATEGORY_NOTIFICATIONS, TestNGGroups.ALL_PLATFORMS},
      description = "C1735295")
  public void testNotificationsReadAndUnreadNotification() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Open 'Notifications' option.");
    NotificationsView notificationsView = mainMenuView.openNotifications();

    LOGGER.info("# Find an unread notification, and tap on the notification.");
    NotificationRow notification = notificationsView.getFirstNotification();
    String notificationTitle = notification.getTitle();
    String notificationDescription = notification.getDescription();
    NotificationDetailsView notificationDetailsView = notification.tap();

    LOGGER.info("## Verify details in view match selected notification row.");
    Assert.assertEquals(
        notificationDetailsView.getTitle(),
        notificationTitle,
        "Title in notification view doesn't match selected notification title.");
    Assert.assertEquals(
        notificationDetailsView.getDescription(),
        notificationDescription,
        "Description in notification view doesn't match selected notification description.");

    LOGGER.info("# Tap 'Back' button.");
    notificationsView = notificationDetailsView.tapBack();
    notification.swipeLeftToRightOnElement();

    /** TODO: Unable to get read/unread state of notification row for the row’s element locator. */
  }
}
