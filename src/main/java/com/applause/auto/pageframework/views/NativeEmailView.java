package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;

@AndroidImplementation(AndroidNativeEmailView.class)
@IosImplementation(IosNativeEmailView.class)
public abstract class NativeEmailView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  /**
   * Return predefined email value from 'To' text element
   *
   * @return String
   */
  public String getToEmailString() {
    return getToTextElement().getStringValue();
  }

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getToTextElement"));
  }

  @MobileElementLocator(
      android = "com.google.android.gm:id/to",
      iOS = "**/XCUIElementTypeTextView[2]")
  protected Text getToTextElement() {
    return new Text(getLocator(this, "getToTextElement"));
  }
}

class AndroidNativeEmailView extends NativeEmailView {}

class IosNativeEmailView extends NativeEmailView {}
