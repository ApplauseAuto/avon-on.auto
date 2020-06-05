package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidShareYourLookView.class)
@IosImplementation(IosShareYourLookView.class)
public abstract class ShareYourLookView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  /**
   * Verifies 'Share Your Look' view exists
   *
   * @return boolean
   */
  public boolean shareYourLookViewIsDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "getTakePictureButton"));
  }

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getTakePictureButton"));
  }

  /**
   * Tap 'Close' button
   *
   * @return DashboardView
   */
  public DashboardView closeShareYourlookView() {
    LOG.info("Tapping 'Close' button.");
    getCloseButton().pressButton();
    return DeviceViewFactory.create(DashboardView.class);
  }

  /**
   * Tap 'Camera' button
   *
   * @return NativeCameraView
   */
  public NativeCameraView tapCameraButton() {
    LOG.info("Tapping 'Camera' button.");
    getTakePictureButton().pressButton();
    return DeviceViewFactory.create(NativeCameraView.class);
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/closeIv.*\")",
      iOS = "closeReverse")
  protected Button getCloseButton() {
    return new Button(getLocator(this, "getCloseButton"));
  }

  @MobileElementLocator(android = "landingTakePictureBtn", iOS = "camera")
  protected Button getTakePictureButton() {
    return new Button(getLocator(this, "getTakePictureButton"));
  }
}

class AndroidShareYourLookView extends ShareYourLookView {}

class IosShareYourLookView extends ShareYourLookView {}
