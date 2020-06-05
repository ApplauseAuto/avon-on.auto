package com.applause.auto.pageframework.chunks;

import java.lang.invoke.MethodHandles;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.testdata.TestConstants.Timeouts;
import com.applause.auto.pageframework.views.PlaceAnOrderView;

@AndroidImplementation(AndroidErrorPopupChunk.class)
@IosImplementation(IosErrorPopupChunk.class)
public abstract class ErrorPopupChunk extends AbstractDeviceChunk {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  public ErrorPopupChunk() {
    super(EnvironmentUtil.getInstance().getIsMobileAndroid() ? "dialogTitle" : "");
  }

  @Override
  protected void waitUntilVisible() {}

  /**
   * Close Error Popup
   *
   * @return PlaceAnOrderView
   */
  public PlaceAnOrderView tapOk() {
    if (syncHelper.isElementDisplayed(
        getLocator(this, "getOkButton"), Timeouts.THREE_SECONDS_MILLI)) {
      logger.info("Press 'Ok' button");
      getOkButton().pressButton();
    }
    return DeviceViewFactory.create(PlaceAnOrderView.class);
  }

  @MobileElementLocator(android = "", iOS = "button_0")
  protected Button getOkButton() {
    return new Button(getLocator(this, "getOkButton"));
  }
}

class AndroidErrorPopupChunk extends ErrorPopupChunk {
  public AndroidErrorPopupChunk() {
    super();
  }
}

class IosErrorPopupChunk extends ErrorPopupChunk {

  public IosErrorPopupChunk() {
    super();
  }
}
