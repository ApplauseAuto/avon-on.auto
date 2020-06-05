package com.applause.auto.test;

import static com.applause.auto.pageframework.testdata.TestConstants.Timeouts.THREE_SECONDS_MILLI;

import com.applause.auto.framework.test.listeners.TestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.util.drivers.DriverWrapper;
import com.applause.auto.framework.pageframework.util.drivers.DriverWrapperManager;
import com.applause.auto.framework.pageframework.util.drivers.RunUtil;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.queryhelpers.DeviceElementQueryHelper;
import com.applause.auto.framework.pageframework.util.synchronization.MobileNativeSyncHelper;
import com.applause.auto.framework.pageframework.util.synchronization.WebSyncHelper;
import com.applause.auto.pageframework.chunks.NotificationAlert;
import com.applause.auto.pageframework.testdata.CustomerConfig;
import com.applause.auto.pageframework.testdata.ExcelReader;
import com.applause.auto.pageframework.testdata.Market;
import com.applause.auto.pageframework.testdata.TestConstants.TestData;
import com.applause.auto.pageframework.testdata.TestConstants.Timeouts;
import com.applause.auto.pageframework.utils.LogHelper;
import com.applause.auto.pageframework.views.LoginView;
import com.applause.auto.pageframework.views.PinView;
import com.applause.auto.pageframework.views.SelectMarketView;

import io.appium.java_client.AppiumDriver;

@Listeners(TestListener.class)
public class BaseTest {

  protected static final LogHelper LOGGER = LogHelper.getInstance();
  protected static DriverWrapper driverWrapper;
  protected static AppiumDriver<?> driver;
  protected static WebSyncHelper webSyncHelper;
  protected static MobileNativeSyncHelper syncHelper;
  protected static DeviceElementQueryHelper queryHelper;
  protected static EnvironmentUtil env;
  protected static String market;

  @SuppressWarnings("unused")
  private static CustomerConfig config;

  static {
    config = new CustomerConfig();
    env = EnvironmentUtil.getInstance();
  }

  protected static SelectMarketView launchApp() {
    dismissAlert();
    return DeviceViewFactory.create(SelectMarketView.class);
  }

  private static void dismissAlert() {
    if (env.getIsMobileIOS()) {
      if (syncHelper.isElementDisplayed("**/XCUIElementTypeAlert", THREE_SECONDS_MILLI)) {
        DeviceChunkFactory.create(NotificationAlert.class).tapAllowButton();
      }
    }
  }

  /** Get a new Appium driver at the start of each test. */
  @BeforeClass(alwaysRun = true)
  public void beforeSuite() {
    Market.setMarketName(setMarketName(System.getProperty("market")));

    // TODO: Not necessary, as it's not being used
    /*Market.setLanguageStrings(
    ExcelReader.getLanguageStringsForMarket(System.getProperty("market")));*/

    ExcelReader.setDetailsForMarket(System.getProperty("market"));
    RunUtil.getInstance().setRunId();
    env = EnvironmentUtil.getInstance();
    driverWrapper = new DriverWrapper(EnvironmentUtil.getDriver(), env.getDriverProvider());
    driver = (AppiumDriver<?>) driverWrapper.getDriver();
    syncHelper = (MobileNativeSyncHelper) driverWrapper.getSyncHelper();
    queryHelper = (DeviceElementQueryHelper) driverWrapper.getQueryHelper();

    LOGGER.info("Test case setup complete.");
  }

  /** Destroy the driver at the end of the test. */
  @AfterMethod(alwaysRun = true)
  public void afterMethod(ITestResult result) {
    try {
      if (!result.isSuccess()) {
        LOGGER.info(driver.getPageSource());
      }

      driver.quit();
    } catch (Throwable th) {
      LOGGER.error("Something happened during driver session release");
    }

    DriverWrapperManager.getInstance().deregisterDriver(driverWrapper);
    LOGGER.info("Test case teardown complete.");
  }

  protected <T extends AbstractDeviceView> T closeAndReopenApp(final Class clazz) {
    String appId =
        EnvironmentUtil.getInstance().getIsMobileAndroid()
            ? TestData.BUNDLE_ID_ANDROID
            : TestData.BUNDLE_ID_IOS;

    LOGGER.info("@ Closing app");
    for (int i = 0; i < 10; i++) {
      try {
        driver.terminateApp(appId);
        LOGGER.info("App state: %s", driver.queryAppState(appId).name());
        break;
      } catch (Exception e) {
      }

      syncHelper.suspend(Timeouts.ONE_SECOND_MILLI);
    }

    LOGGER.info("@ Reopening app");
    driver.activateApp(appId);

    T expectedView = null;

    if (clazz.getSimpleName().equals(LoginView.class.getSimpleName())) {
      expectedView = (T) DeviceViewFactory.create(LoginView.class);
    } else if (clazz.getSimpleName().equals(PinView.class.getSimpleName())) {
      expectedView = (T) DeviceViewFactory.create(PinView.class);
    }

    return expectedView;
  }

  private String setMarketName(String market) {
    LOGGER.info("Set correct market name for: %s", market);

    if (market.contains("_")) {
      return market.replace("_", " ");
    }

    return market;
  }
}
