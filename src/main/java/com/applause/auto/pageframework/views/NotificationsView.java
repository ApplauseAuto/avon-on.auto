package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Random;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.chunks.NotificationRow;
import com.applause.auto.pageframework.utils.Helper;

@AndroidImplementation(AndroidNotificationsView.class)
@IosImplementation(IosNotificationsView.class)
public abstract class NotificationsView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getFirstNotificationItem"));
  }

  /**
   * Verifies number of unread notifications match ("## new")
   *
   * @return boolean
   */
  public boolean numberOfUnreadNotificationsIsDisplayed() {
    return getUnreadNotificationsText().getStringValue().matches("^[\\d]{0,3} [\\w]+$");
  }

  /**
   * Verifies 'Notifications' view is displayed
   *
   * @return boolean
   */
  public boolean notificationsViewIsDisplayed() {
    return getFirstNotificationItem().isTextDisplayed();
  }

  /** @return First notification */
  public NotificationRow getFirstNotification() {
    return notifications().get(0);
  }

  /** @return First notification */
  public NotificationRow getRandomNotification() {
    int randomNotificationIndex = new Random().nextInt(notifications().size());
    return notifications().get(randomNotificationIndex);
  }

  @MobileElementLocator(
      android = "(//*[contains(@resource-id, 'id/containerCl')])",
      iOS = "(//XCUIElementTypeCell[contains(@name, 'NotificationsInboxCell_index_')])")
  protected List<NotificationRow> notifications() {
    return (List<NotificationRow>)
        Helper.getCollectionOfChunks(getLocator(this, "notifications"), NotificationRow.class);
  }

  @MobileElementLocator(android = "unReadTextCountTv", iOS = "**/XCUIElementTypeStaticText[1]")
  protected Text getUnreadNotificationsText() {
    return new Text(getLocator(this, "getUnreadNotificationsText"));
  }

  @MobileElementLocator(
      android = "//*[@resource-id='com.avon.avonon.uat:id/containerCl'][1]",
      iOS = "NotificationsInboxCell_index_0")
  protected Text getFirstNotificationItem() {
    return new Text(getLocator(this, "getFirstNotificationItem"));
  }
}

class AndroidNotificationsView extends NotificationsView {}

class IosNotificationsView extends NotificationsView {}
