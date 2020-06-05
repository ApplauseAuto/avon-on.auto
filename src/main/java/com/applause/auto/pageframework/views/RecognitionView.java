package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.BaseDeviceControl;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidRecognitionView.class)
@IosImplementation(IosRecognitionView.class)
public abstract class RecognitionView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "baseElement"));
  }

  /** @return true, if view is displayed */
  public boolean isDisplayed() {
    return syncHelper.isElementDisplayed(getLocator(this, "baseElement"));
  }

  @MobileElementLocator(
      android = "new UiSelector().resourceIdMatches(\".*id/dialogFragmentIvFingerprint.*\")",
      iOS = "xxx")
  protected BaseDeviceControl baseElement() {
    return new BaseDeviceControl(getLocator(this, "baseElement"));
  }
}

class AndroidRecognitionView extends RecognitionView {}

class IosRecognitionView extends RecognitionView {}
