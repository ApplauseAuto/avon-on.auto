package com.applause.auto.pageframework.chunks;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.BaseDeviceControl;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.pageframework.utils.Helper;
import com.applause.auto.pageframework.utils.LogHelper;
import com.applause.auto.pageframework.views.NotificationDetailsView;

@AndroidImplementation(AndroidNotificationRow.class)
@IosImplementation(IosNotificationRow.class)
public abstract class NotificationRow extends AbstractDeviceChunk {

  protected static LogHelper LOG = LogHelper.getInstance();

  public NotificationRow(String selector) {
    super(selector);
  }

  @Override
  protected void waitUntilVisible() {}

  /** @return Notification title */
  public String getTitle() {
    return titleElement().getStringValue().trim();
  }

  /** @return Notification description */
  public String getDescription() {
    return descriptionElement().getStringValue().trim();
  }

  /**
   * Taps specified notification
   *
   * @return NotificationDetailsView
   */
  public NotificationDetailsView tap() {
    LOG.info("Tapping notification [%s]", getTitle());
    getMobileElement().click();
    return DeviceViewFactory.create(NotificationDetailsView.class);
  }

  /** Swipes left to right on a notification */
  public void swipeLeftToRightOnElement() {
    Helper.swipeLeftToRightOnElement(baseElement());
  }

  /** Swipes right to left on a notification, and taps 'Delete' button */
  public abstract void delete();

  /** @return true, if 'Undo' button is displayed */
  public abstract boolean isDeleted();

  /** Taps 'Undo' button */
  public abstract void undoDelete();

  protected BaseDeviceControl baseElement() {
    return new BaseDeviceControl(selector);
  }

  @MobileElementLocator(
      android = "//android.widget.TextView[contains(@resource-id, 'id/titleTv')]",
      iOS = "//XCUIElementTypeStaticText[@name='titleLabel']")
  protected Text titleElement() {
    return new Text(selector + getLocator(this, "titleElement"));
  }

  @MobileElementLocator(
      android = "//android.widget.TextView[contains(@resource-id, 'id/contentTv')]",
      iOS = "//XCUIElementTypeStaticText[@name='messageLabel']")
  protected Text descriptionElement() {
    return new Text(selector + getLocator(this, "descriptionElement"));
  }
}

class AndroidNotificationRow extends NotificationRow {

  public AndroidNotificationRow(String selector) {
    super(selector);
  }

  public void delete() {
    BaseDeviceControl element = baseElement();
    Helper.swipeRightToLeftOnElement(element);
    Helper.tapOnElementWithOffset(element, 0.6, 0);
  }

  public void undoDelete() {
    LOG.info("Tapping 'Undo' button.");
    androidUndoButton().tap();
  }

  public boolean isDeleted() {
    return syncHelper.isElementDisplayed(getLocator(this, "androidUndoButton"));
  }

  @MobileElementLocator(android = "//android.widget.TextView[contains(@resource-id, 'id/undoTv')]")
  protected Button androidUndoButton() {
    return new Button(selector + getLocator(this, "androidUndoButton"));
  }
}

class IosNotificationRow extends NotificationRow {

  public IosNotificationRow(String selector) {
    super(selector);
  }

  public void delete() {
    BaseDeviceControl element = baseElement();
    Helper.swipeRightToLeftOnElement(element);
    deleteButton().tap();
    syncHelper.waitForElementToDisappear(selector + getLocator(this, "deleteButton"));
  }

  public void undoDelete() {
    LOG.info("Tapping 'Undo' button.");
    iosUndoButton().tap();
    syncHelper.waitForElementToDisappear(getLocator(this, "iosUndoButton"));
  }

  public boolean isDeleted() {
    return syncHelper.isElementDisplayed(getLocator(this, "iosUndoButton"));
  }

  @MobileElementLocator(iOS = "//XCUIElementTypeButton[@name='delete']")
  protected Button deleteButton() {
    return new Button(selector + getLocator(this, "deleteButton"));
  }

  @MobileElementLocator(iOS = "//XCUIElementTypeButton[@name='Undo']")
  protected Button iosUndoButton() {
    return new Button(getLocator(this, "iosUndoButton"));
  }
}
