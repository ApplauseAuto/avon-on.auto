package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidNativeCameraView.class)
@IosImplementation(IosNativeCameraView.class)
public abstract class NativeCameraView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getDeclineButton"));
  }

  /**
   * Closes 'Camera' view
   *
   * @return ShareYourLookView
   */
  public ShareYourLookView closeCameraView() {
    LOG.info("Tapping 'Decline' button");
    getDeclineButton().tap();
    return DeviceViewFactory.create(ShareYourLookView.class);
  }

  @MobileElementLocator(
      android = "//*[@resource-id='com.avon.avonon.uat:id/cancel']",
      iOS = "//*[text()[contains(., 'Decline', 'Deny')]]")
  protected Button getDeclineButton() {
    return new Button(getLocator(this, "getDeclineButton"));
  }
}

class AndroidNativeCameraView extends NativeCameraView {}

class IosNativeCameraView extends NativeCameraView {}
