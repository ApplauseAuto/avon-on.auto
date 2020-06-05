package com.applause.auto.test.notifications;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.applause.auto.pageframework.chunks.NotificationRow;
import com.applause.auto.pageframework.testdata.TestConstants.TestNGGroups;
import com.applause.auto.pageframework.views.DashboardView;
import com.applause.auto.pageframework.views.MainMenuView;
import com.applause.auto.pageframework.views.NotificationsView;
import com.applause.auto.test.BaseTest;
import com.applause.auto.test.helper.LoginHelper;

public class NotificationsDeleteAndUndoDeleteNotificationTest extends BaseTest {

  @Test(
      enabled = false,
      groups = {TestNGGroups.CATEGORY_NOTIFICATIONS, TestNGGroups.ALL_PLATFORMS},
      description = "C1735295")
  public void testNotificationsDeleteAndUndoDeleteNotification() {
    DashboardView dashboardView = LoginHelper.successfullyLogin();

    LOGGER.info("# Tap hamburger menu and open Main Menu.");
    MainMenuView mainMenuView = dashboardView.openMainMenu();

    LOGGER.info("# Open 'Notifications' option.");
    NotificationsView notificationsView = mainMenuView.openNotifications();

    LOGGER.info("# Find any notification, swipe to the left, and tap the 'Delete' button.");
    NotificationRow notification = notificationsView.getRandomNotification();
    String notificationTitle = notification.getTitle();
    String notificationDescription = notification.getDescription();
    notification.delete();

    LOGGER.info("## Verify notification text changes to 'Notification deleted'.");
    Assert.assertTrue(notification.isDeleted(), "'Notification deleted' message is not displayed");

    LOGGER.info("# Tap 'Undo' button.");
    notification.undoDelete();

    LOGGER.info("## Verify notification is restored.");
    Assert.assertEquals(
        notification.getTitle(), notificationTitle, "Notification title is not the same.");
    Assert.assertEquals(
        notification.getDescription(),
        notificationDescription,
        "Notification description is not the same.");
  }
}
