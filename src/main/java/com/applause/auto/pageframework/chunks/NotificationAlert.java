package com.applause.auto.pageframework.chunks;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.pageframework.testdata.TestConstants.Timeouts;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidNotificationAlert.class)
@IosImplementation(IosNotificationAlert.class)
public abstract class NotificationAlert extends AbstractDeviceChunk {

  private static LogHelper LOG = LogHelper.getInstance();

  public NotificationAlert() {
    super(
        EnvironmentUtil.getInstance().getIsMobileAndroid()
            ? "new UiSelector().resourceIdMatches(\".*id/dialog_container.*\")"
            : "**/XCUIElementTypeAlert");
  }

  @Override
  protected void waitUntilVisible() {}

  /** Tap allow button. */
  public void tapAllowButton() {
    if (syncHelper.isElementDisplayed(
        getLocator(this, "ignoreButton"), Timeouts.TWO_SECONDS_MILLI)) {
      LOG.info("Tapping 'Ignore' alert button.");
      ignoreButton().tap();
    } else {
      LOG.info("Tapping 'Allow' alert button.");
      getAllowButton().tap();
    }
  }

  /**
   * Return string value from the 'Title' text element
   *
   * @return String
   */
  public String getTitleTextString() {
    return getTitleText().getStringValue();
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/permission_allow_button.*\")",
      iOS = "Allow")
  protected Button getAllowButton() {
    return new Button(getLocator(this, "getAllowButton"));
  }

  @MobileElementLocator(android = "xxx", iOS = "Ignore")
  protected Button ignoreButton() {
    return new Button(getLocator(this, "ignoreButton"));
  }

  @MobileElementLocator(android = "", iOS = "**/XCUIElementTypeAlert/**XCUIElementTypeStaticText")
  protected Text getTitleText() {
    return new Text(getLocator(this, "getTitleText"));
  }
}

class AndroidNotificationAlert extends NotificationAlert {

  public AndroidNotificationAlert() {
    super();
  }
}

class IosNotificationAlert extends NotificationAlert {

  public IosNotificationAlert() {
    super();
  }
}
