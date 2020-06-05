package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.chunks.NotificationAlert;

@AndroidImplementation(AndroidNativeCallUsView.class)
@IosImplementation(IosNativeCallUsView.class)
public abstract class NativeCallUsView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {}

  /**
   * Return string value from the 'Phone Number' text element
   *
   * @return String
   */
  public abstract String getPhoneNumberString();

  @MobileElementLocator(android = "com.samsung.android.dialer:id/digits", iOS = "")
  public Text getPhoneNumberText() {
    return new Text(getLocator(this, "getPhoneNumberText"));
  }
}

class AndroidNativeCallUsView extends NativeCallUsView {

  @Override
  public String getPhoneNumberString() {
    return getPhoneNumberText().getStringValue();
  }
}

class IosNativeCallUsView extends NativeCallUsView {

  @Override
  public String getPhoneNumberString() {
    return DeviceChunkFactory.create(NotificationAlert.class).getTitleTextString();
  }
}
