package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidNotificationDetailsView.class)
@IosImplementation(IosNotificationDetailsView.class)
public abstract class NotificationDetailsView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "baseElement"));
  }

  /** @return Notification title */
  public String getTitle() {
    return titleElement().getStringValue().trim();
  }

  /** @return Notification description */
  public String getDescription() {
    return descriptionElement().getStringValue().trim();
  }

  /**
   * Taps 'Back' button
   *
   * @return NotificationsView
   */
  public NotificationsView tapBack() {
    LOG.info("Tapping 'Back' button.");
    backButton().tap();
    return DeviceViewFactory.create(NotificationsView.class);
  }

  @MobileElementLocator(
      android =
          "//android.widget.FrameLayout[contains(@resource-id, 'id/rootContainer') and descendant::android.widget.TextView[contains(@resource-id, 'id/fromTv')]]",
      iOS = "xxxx")
  protected Text baseElement() {
    return new Text(getLocator(this, "baseElement"));
  }

  @MobileElementLocator(
      android = "//android.widget.TextView[contains(@resource-id, 'id/titleTv')]",
      iOS = "xxxx")
  protected Text titleElement() {
    return new Text(getLocator(this, "titleElement"));
  }

  @MobileElementLocator(
      android = "//android.widget.TextView[contains(@resource-id, 'id/contentTv')]",
      iOS = "xxxx")
  protected Text descriptionElement() {
    return new Text(getLocator(this, "descriptionElement"));
  }

  @MobileElementLocator(
      android = "//android.widget.ImageButton[contains(@resource-id, 'id/backButtonIb')]",
      iOS = "xxxx")
  protected Button backButton() {
    return new Button(getLocator(this, "backButton"));
  }
}

class AndroidNotificationDetailsView extends NotificationDetailsView {}

class IosNotificationDetailsView extends NotificationDetailsView {}
