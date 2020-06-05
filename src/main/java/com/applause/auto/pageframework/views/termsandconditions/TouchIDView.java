package com.applause.auto.pageframework.views.termsandconditions;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.util.logger.LogController;

@AndroidImplementation(AndroidTouchIDView.class)
@IosImplementation(IosTouchIDView.class)
public abstract class TouchIDView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getEnableTouchIdButton"));
  }

  /** Tap 'Enable TouchId' button */
  public void tapEnableTouchIdButton() {
    getEnableTouchIdButton().tap();
  }

  @MobileElementLocator(android = "", iOS = "**/XCUIElementTypeButton[1]")
  protected Button getEnableTouchIdButton() {
    return new Button(getLocator(this, "getEnableTouchIdButton"));
  }

  @MobileElementLocator(android = "", iOS = "**/XCUIElementTypeButton[2]")
  protected Button getUsePinCodeButton() {
    return new Button(getLocator(this, "getUsePinCodeButton"));
  }
}

class AndroidTouchIDView extends TouchIDView {}

class IosTouchIDView extends TouchIDView {}
