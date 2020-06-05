package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Button;
import com.applause.auto.pageframework.chunks.AvonDialogChunk;
import com.applause.auto.pageframework.chunks.ErrorPopupChunk;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidPlaceAnOrderView.class)
@IosImplementation(IosPlaceAnOrderView.class)
public abstract class PlaceAnOrderView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getCloseButton"));
  }

  /**
   * Taps on 'X' close button
   *
   * @return
   */
  public abstract void tapCloseButton();

  /**
   * Taps on 'Cancel' button
   *
   * @return PlaceAnOrderView
   */
  public void tapCancel() {
    LOG.info("Tapping 'Cancel' button.");
    DeviceChunkFactory.create(AvonDialogChunk.class).tapCancelButton();
  }

  /**
   * Click on 'Ok' button
   *
   * @return DashboardView
   */
  public DashboardView tapOk() {
    LOG.info("Tapping 'Ok' button");
    DeviceChunkFactory.create(AvonDialogChunk.class).tapOkButton();
    return DeviceViewFactory.create(DashboardView.class);
  }

  @MobileElementLocator(android = "//android.widget.ImageButton", iOS = "close")
  protected Button getCloseButton() {
    return new Button(getLocator(this, "getCloseButton"));
  }
}

class AndroidPlaceAnOrderView extends PlaceAnOrderView {
  @Override
  public void tapCloseButton() {
    LOG.info("Press 'Close' button");
    getCloseButton().pressButton();
  }
}

class IosPlaceAnOrderView extends PlaceAnOrderView {
  @Override
  public void tapCloseButton() {
    LOG.info("Close error popup");
    ErrorPopupChunk errorPopupChunk = DeviceChunkFactory.create(ErrorPopupChunk.class);
    PlaceAnOrderView placeAnOrderView = errorPopupChunk.tapOk();

    LOG.info("Press 'Close' button");
    getCloseButton().pressButton();
  }
}
