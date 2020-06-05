package com.applause.auto.pageframework.utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.TestException;

import com.applause.auto.framework.pageframework.device.AbstractDeviceChunk;
import com.applause.auto.framework.pageframework.device.DeviceChunkFactory;
import com.applause.auto.framework.pageframework.devicecontrols.BaseDeviceControl;
import com.applause.auto.framework.pageframework.util.DeviceControl;
import com.applause.auto.framework.pageframework.util.drivers.DriverWrapperManager;
import com.applause.auto.framework.pageframework.util.environment.EnvironmentUtil;
import com.applause.auto.framework.pageframework.util.queryhelpers.DeviceElementQueryHelper;
import com.applause.auto.framework.pageframework.util.queryhelpers.WebElementQueryHelper;
import com.applause.auto.framework.pageframework.util.screenshots.MobileScreenshotManager;
import com.applause.auto.framework.pageframework.util.synchronization.MobileNativeSyncHelper;
import com.applause.auto.pageframework.testdata.TestConstants.TestData;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Helper {

  protected static final LogHelper LOGGER = LogHelper.getInstance();
  private static Dimension deviceSize = null;

  /** Hides the keyboard for android and ios */
  public static void hideKeyboardIfPresent() {
    if (getEnv().getIsMobileAndroid()) {
      getDriver().hideKeyboard();
    }
  }

  /** Swipe Left */
  public static void swipeLeft() {
    refreshDeviceSize();

    Dimension size = deviceSize;
    int startX = (int) (size.width * 0.8);
    int endX = (int) (size.width * 0.2);
    int startY = size.height / 2;
    LOGGER.info("Swiping left...");
    new TouchAction(getDriver())
        .press(PointOption.point(startX, startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(endX, 0))
        .release()
        .perform();
  }

  /** Android Swipe Left */
  public static void androidSwipeLeft() {
    refreshDeviceSize();
    Dimension size = deviceSize;

    int startX = (int) (size.width * 0.25);
    int middleX = (int) (size.width * 0.5);
    int endX = 0;
    int y = size.height / 2;
    LOGGER.info("Swiping left...");
    new TouchAction(getDriver())
        .longPress(PointOption.point(startX, y))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(middleX, y))
        .moveTo(PointOption.point(endX, y))
        .release()
        .perform();
  }

  public static void swipeLeft(double y) {
    Dimension size = deviceSize;
    int startX = (int) (size.width * 0.8);
    int endX = (int) (size.width * 0.2);
    double startY;
    if (y < 1) {
      startY = size.height * y;
    } else {
      startY = y;
    }
    LOGGER.info("Swiping left...");
    new TouchAction(getDriver())
        .press(PointOption.point(startX, (int) startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(endX, 0))
        .release()
        .perform();
  }

  public static void swipeLeftSlow(double y) {
    Dimension size = deviceSize;
    int startX = (int) (size.width * 0.55);
    int endX = (int) (size.width * 0.45);
    double startY;
    if (y < 1) {
      startY = size.height * y;
    } else {
      startY = y;
    }
    LOGGER.info("Swiping left...");
    new TouchAction(getDriver())
        .press(PointOption.point(startX, (int) startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(endX, 0))
        .release()
        .perform();
  }

  public static void swipeDownSlow(double y) {
    Dimension size = deviceSize;
    int startX = (int) (size.width * 0.55);
    int endX = (int) (size.width * 0.45);
    double startY;
    if (y < 1) {
      startY = size.height * y;
    } else {
      startY = y;
    }
    LOGGER.info("Swiping left...");
    new TouchAction(getDriver())
        .press(PointOption.point(startX, (int) startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(endX, 0))
        .release()
        .perform();
  }

  /** Swipe Right */
  public static void swipeRight() {
    refreshDeviceSize();
    Dimension size = deviceSize;
    swipeRight(0.5);
  }

  /** Android Swipe Right */
  public static void androidSwipeRight() {
    refreshDeviceSize();
    Dimension size = deviceSize;

    int startX = (int) (size.width * 0.5);
    int middleX = 0;
    int endX = (int) (size.width * 0.25);
    int y = size.height / 2;
    LOGGER.info("Swiping right...");
    new TouchAction(getDriver())
        .longPress(PointOption.point(startX, y))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(middleX, y))
        .moveTo(PointOption.point(endX, y))
        .release()
        .perform();
  }

  public static void swipeRight(double y) {
    Dimension size = deviceSize;
    int startX = (int) (size.width * 0.2);
    int endX = (int) (size.width * 0.8);
    double startY;
    if (y < 1) {
      startY = size.height * y;
    } else {
      startY = y;
    }
    LOGGER.info("Swiping right...");
    new TouchAction(getDriver())
        .press(PointOption.point(startX, (int) startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(endX, 0))
        .release()
        .perform();
  }

  public static void swipeRightSlow(double y) {
    Dimension size = deviceSize;
    int startX = (int) (size.width * 0.45);
    int endX = (int) (size.width * 0.55);
    double startY;
    if (y < 1) {
      startY = size.height * y;
    } else {
      startY = y;
    }
    LOGGER.info("Swiping right...");
    new TouchAction(getDriver())
        .press(PointOption.point(startX, (int) startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
        .moveTo(PointOption.point(endX, 0))
        .release()
        .perform();
  }

  /**
   * Performs a quick swipe action towards the left of a specified element
   *
   * @param element - Specified BaseDeviceControl element
   */
  public static void swipeLeftToRightOnElement(BaseDeviceControl element) {
    swipeHorizontallyOnElement(element, Direction.LEFT);
  }

  public static void swipeDownOnElement(BaseDeviceControl element) {
    swipeVerticallyOnElement(element, Direction.DOWN);
  }

  /**
   * Performs a quick swipe action towards the right of a specified element
   *
   * @param element - Specified BaseDeviceControl element
   */
  public static void swipeRightToLeftOnElement(BaseDeviceControl element) {
    swipeHorizontallyOnElement(element, Direction.RIGHT);
  }

  /**
   * Performs a quick swipe towards the left on given element position
   *
   * @param element - Specified BaseDeviceControl element
   */
  public static void swipeLeftOnElementPosition(BaseDeviceControl element) {
    swipeHorizontallyOnElementPosition(element, Direction.LEFT);
  }

  /**
   * Performs a quick swipe towards the right on given element position
   *
   * @param element - Specified BaseDeviceControl element
   */
  public static void swipeRightOnElementPosition(BaseDeviceControl element) {
    swipeHorizontallyOnElementPosition(element, Direction.RIGHT);
  }

  /**
   * Performs a quick swipe action towards a set direction of a specified element
   *
   * @param element - Specified BaseDeviceControl element
   * @param direction - Horizontal direction that the element will be swiped towards. Can only be
   *     'left' or 'right'.
   */
  private static void swipeHorizontallyOnElement(BaseDeviceControl element, Direction direction) {
    if (direction.equals(Direction.DOWN) || direction.equals(Direction.UP)) {
      Assert.fail(
          "'"
              + direction
              + "' is not a valid horizontal direction. Please specify 'left' or 'right'.");
    }

    LOGGER.debug("Swiping " + direction + " on [" + element + "] element.");
    int elementX = element.getCenterX();
    int elementY = element.getCenterY();
    int elementHalfWidth = element.getDimension().width;
    int swipeDirection =
        direction.equals(Direction.LEFT)
            ? (int) (elementHalfWidth * 0.8)
            : (int) (elementHalfWidth * 0.2);
    LOGGER.info("Swiping...");
    doSwipeAction(elementX, elementY, swipeDirection, elementY, 1000);
  }

  private static void doSwipeAction(int startx, int starty, int endx, int endy, int duration) {
    (new TouchAction(getDriver()))
        .press(PointOption.point(startx, starty))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis((long) duration)))
        .moveTo(PointOption.point(endx, endy))
        .release()
        .perform();
  }

  /**
   * Swipe horizontally on element position
   *
   * @param element - Specified BaseDeviceControl element
   * @param direction - Horizontal direction in which the scroll will be executed
   */
  private static void swipeHorizontallyOnElementPosition(
      BaseDeviceControl element, Direction direction) {
    if (direction.equals(Direction.DOWN) || direction.equals(Direction.UP)) {
      Assert.fail(
          "'"
              + direction
              + "' is not a valid horizontal direction. Please specify 'left' or 'right'.");
    }
    Dimension size = getDriver().manage().window().getSize();
    int startX = (int) (size.width * 0.2);
    int endX = (int) (size.width * 0.8);
    int elementY = element.getCenterY();
    // Appium java client has bug where
    // iOS coordinates are relative, but Android are absolute
    LOGGER.info("Swiping...");
    if (getEnv().getIsMobileIOS()) {
      if (direction.equals(Direction.LEFT)) {
        new TouchAction(getDriver())
            .press(PointOption.point(startX, elementY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis((long) 1000)))
            .moveTo(PointOption.point(startX - endX, elementY))
            .release()
            .perform();
      } else {
        new TouchAction(getDriver())
            .press(PointOption.point(endX, elementY))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis((long) 1000)))
            .moveTo(PointOption.point(endX - startX, elementY))
            .release()
            .perform();
      }
    } else {
      LOGGER.debug("x1 " + startX + "x2 " + endX);
      if (direction.equals(Direction.RIGHT)) {
        getDeviceControl().swipe(startX, elementY, endX, elementY, 1000);
      } else {
        getDeviceControl().swipe(endX, elementY, startX, elementY, 1000);
      }
    }
  }

  private static void swipeVerticallyOnElement(BaseDeviceControl element, Direction direction) {
    if (direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)) {
      Assert.fail(
          "'" + direction + "' is not a valid vertical direction. Please specify 'up' or 'down'.");
    }

    LOGGER.debug("Swiping " + direction + " on [" + element + "] element.");
    int elementX = element.getCenterX();
    int elementY = element.getCenterY();
    int elementHalfHeight = element.getDimension().height;
    int swipeDirection =
        direction.equals(Direction.UP)
            ? (int) (elementHalfHeight * 0.8)
            : (int) (elementHalfHeight * 0.2);
    LOGGER.info("Swiping...");
    int swipeLimit = 0;
    while (!element.getMobileElement().isDisplayed() && swipeLimit < 30) {
      swipeLimit++;
      getDeviceControl().swipe(elementX, elementY, elementX, swipeDirection, 1000);
    }
  }

  public static String generateRandomString() {

    return "test_" + System.currentTimeMillis() / 1000;
  }

  public static void scrollToElementByXpath(String xPath) {
    JavascriptExecutor js = getDriver();
    WebElement element = getDriver().findElement(By.xpath(xPath));
    HashMap<String, String> scrollToObject = new HashMap();
    scrollToObject.put("element", ((RemoteWebElement) element).getId());
    js.executeScript("mobile: scrollTo", new Object[] {scrollToObject});
  }

  public static MobileElement androidScrollTo(
      String UiSelector, String scrollSelector, int maxSwipes) {
    LOGGER.info("Scrolling to: " + UiSelector);
    return getAndroidDriver()
        .findElement(
            MobileBy.AndroidUIAutomator(
                String.format(
                    "new UiScrollable(%s).setMaxSearchSwipes(%s)" + ".scrollIntoView(%s);",
                    scrollSelector, maxSwipes, UiSelector)));
  }

  public static MobileElement androidScrollTo(String UiSelector) {
    return androidScrollTo(
        UiSelector, "new UiSelector().className(\"android.widget.ScrollView\").instance(0)", 20);
  }

  public static MobileElement androidScrollTo(String UiSelector, int index) {
    return androidScrollTo(
        UiSelector,
        String.format(
            "new UiSelector().className(\"android.widget.ScrollView\").instance(%s)", index),
        10);
  }

  public static MobileElement androidScrollToText(String s, String scrollSelector) {
    LOGGER.info("Scrolling to: " + s);
    return getAndroidDriver()
        .findElement(
            MobileBy.AndroidUIAutomator(
                String.format(
                    "new UiScrollable(%s)" + ".scrollIntoView(new UiSelector().text(\"%s\"));",
                    scrollSelector, s)));
  }

  public static MobileElement androidScrollToText(String s) {
    return androidScrollToText(
        s, "new UiSelector().className(\"android.widget.ScrollView\").instance(0)");
  }

  public static void setIosPickerWheelValue(String value) {
    getIosDriver().findElementByIosClassChain("**/XCUIElementTypePickerWheel").sendKeys(value);
    getIosDriver()
        .findElementByIosClassChain("**/XCUIElementTypeButton[$name contains[c] 'Done'$][1]")
        .click();
    getSyncHelper().waitForElementToDisappear("**/XCUIElementTypePickerWheel");
  }

  public static void refreshDeviceSize() {
    deviceSize = getDriver().manage().window().getSize();
  }

  public static MobileElement androidScrollToWithRetries(String UiSelector, int retries) {
    Boolean found = false;
    int count = 0;
    MobileElement ret = null;
    while (!found && count < retries) {
      try {
        ret =
            androidScrollTo(
                UiSelector,
                "new UiSelector().className(\"android.widget.ScrollView\").instance(0)",
                10);
        found = true;
      } catch (TimeoutException | NoSuchElementException e) {
        LOGGER.debug("Retrying...");
      }
      count++;
    }
    return ret;
  }

  public static void jsScrollDown() {
    JavascriptExecutor js = getDriver();
    HashMap<String, String> scrollObject = new HashMap<>();
    scrollObject.put("direction", "down");
    js.executeScript("mobile: scroll", scrollObject);
  }

  public static void jsScrollUp() {
    JavascriptExecutor js = getDriver();
    HashMap<String, String> scrollObject = new HashMap<>();
    scrollObject.put("direction", "up");
    js.executeScript("mobile: scroll", scrollObject);
  }

  public static IOSDriver<MobileElement> getIOSDriver() {
    IOSDriver<MobileElement> IOSDriver = (IOSDriver<MobileElement>) getDriver();
    return IOSDriver;
  }

  public static <T> T selectRandomFromList(List<T> list) {
    int size = list.size();
    return list.get(new Random().nextInt(size));
  }

  public static <T> T selectRandomFromList(T[] list) {
    int size = list.length;
    return list[new Random().nextInt(size)];
  }

  /**
   * Scrolls down with a maximum swipeLimit
   *
   * @param swipeLimit
   */
  public static void scrollDown(int swipeLimit) {
    LOGGER.info("Scrolling down to element.");
    refreshDeviceSize();
    for (int i = 0; i < swipeLimit; i++) {
      scrollDownAlgorithm();
      getSyncHelper().suspend(500);
    }
  }

  /**
   * Scrolls down with a maximum swipeLimit
   *
   * @param swipeLimit
   */
  public static void scrollDown(int swipeLimit, double startX, double startY, double endY) {
    refreshDeviceSize();
    for (int i = 0; i < swipeLimit; i++) {
      scrollDownAlgorithm(startX, startY, endY);
      getSyncHelper().suspend(500);
    }
  }

  /**
   * Scroll up
   *
   * @param swipeLimit the swipe limit
   * @param startX - position where to start swipe on vertical line
   */
  public static void scrollUp(int swipeLimit, double startX) {
    LOGGER.info("Scrolling up to element.");
    refreshDeviceSize();
    for (int i = 0; i < swipeLimit; i++) {
      scrollUpAlgorithm(startX);
      getSyncHelper().suspend(1000);
    }
  }

  /**
   * Scroll Up on Small Screens
   *
   * @param swipeLimit
   * @param startX
   */
  public static void smallScreenScrollUp(int swipeLimit, double startX) {
    LOGGER.info("Scrolling up to element.");
    if (!getEnv().getIsTablet()) {
      refreshDeviceSize();
      for (int i = 0; i < swipeLimit; i++) {
        scrollUpAlgorithm(startX);
        getSyncHelper().suspend(500);
      }
    }
  }

  /**
   * Scroll Down on Small Screens
   *
   * @param swipeLimit
   * @param startX
   */
  public static void smallScreenScrollDown(int swipeLimit, double startX) {
    LOGGER.info("Scrolling down to element.");
    if (!getEnv().getIsTablet()) {
      refreshDeviceSize();
      for (int i = 0; i < swipeLimit; i++) {
        scrollDownAlgorithm(startX, 0.4, 0.1);
        getSyncHelper().suspend(500);
      }
    }
  }

  /**
   * Scroll from Point A to Point B
   *
   * @param start
   * @param end
   */
  public static void scrollFrom(Point start, Point end) {
    LOGGER.info(String.format("swipe from (%s,%s) to (%s,%s)", start.x, start.y, end.x, end.y));
    getDeviceControl().swipe(start.x, start.y, end.x, end.y, 1000);
  }

  /**
   * Scrolls down with a maximum swipeLimit
   *
   * @param swipeLimit
   */
  public static void scrollToBottom(int swipeLimit) {
    LOGGER.info("Scrolling to bottom of page");
    refreshDeviceSize();

    for (int i = 0; i < swipeLimit; i++) {
      scrollDownAlgorithm(0.1, 0.8, 0.2);
      getSyncHelper().suspend(500);
    }
  }

  public static void scrollDownToElement(String locator, int retries) {
    refreshDeviceSize();
    int count = 0;
    while (!getSyncHelper().isElementDisplayed(locator) && count < retries) {
      scrollDownAlgorithm();
      getSyncHelper().suspend(2000);
      count++;
    }

    if (!getSyncHelper().isElementDisplayed(locator)) {
      throw new NotFoundException("Could not scroll to locator [" + locator + "].");
    }
  }

  public static void scrollDownToElementCloseToMiddle(String locator, int retries) {
    LOGGER.info("Scrolling to bottom of page close to middle");
    refreshDeviceSize();
    int count = 0;
    while (!getSyncHelper().isElementDisplayed(locator) && (count < retries)) {
      scrollDownCloseToMiddleAlgorithm();
      getSyncHelper().suspend(2000);
      count++;
    }

    if (!getSyncHelper().isElementDisplayed(locator)) {
      throw new NotFoundException("Could not scroll to locator [" + locator + "].");
    }
  }

  /**
   * @param locator
   * @param retries
   * @param startX - position where to start swipe on vertical line
   */
  public static void scrollUpToElement(String locator, int retries, double startX) {
    refreshDeviceSize();
    int count = 0;
    while (!getSyncHelper().isElementDisplayed(locator) && count < retries) {
      scrollUpAlgorithm(startX);
      getSyncHelper().suspend(1500);
      count++;
    }
  }

  /** public void tap on element */
  public static void tapOnElement(BaseDeviceControl element) {
    int x = element.getCenterX();
    int y = element.getCenterY();
    getDeviceControl().doCoordinateTap(x, y);
  }

  /** public void tap on coordinates */
  public static void tapOnCoordinates(int x, int y) {
    LOGGER.info(String.format("Tapping on X=[%s], Y=[%s] coordinates", x, y));
    getDeviceControl().doCoordinateTap(x, y);
  }

  /**
   * public void tap on element with relative offset
   *
   * @param element BaseDeviceControl element
   * @param xRelativeOffset offset from element center to the right in percents of element width.
   *     Value should be from 0 to 1
   * @param yRelativeOffset offset from element center to the top in percents of element height.
   *     Value should be from 0 to 1
   */
  public static void tapOnElementWithOffset(
      BaseDeviceControl element, double xRelativeOffset, double yRelativeOffset) {
    if (xRelativeOffset < 0 || xRelativeOffset > 1) {
      throw new InvalidArgumentException(
          "xRelativeOffset value must be a double value from 0 to 1.");
    }

    if (yRelativeOffset < 0 || yRelativeOffset > 1) {
      throw new InvalidArgumentException(
          "yRelativeOffset value must be a double value from 0 to 1.");
    }

    int x = element.getCenterX();
    int y = element.getCenterY();
    Dimension dimension = element.getDimension();
    int xAbsoluteOffset = (int) (dimension.width * xRelativeOffset);
    int yAbsoluteOffset = (int) (dimension.height * yRelativeOffset);
    getDeviceControl().doCoordinateTap(x + xAbsoluteOffset, y + yAbsoluteOffset);
  }

  /**
   * Scroll Down By start and end Coordinates
   *
   * @param pStartY
   * @param pEndY
   */
  public static void scrollDownByCoordinates(double pStartY, double pEndY) {
    refreshDeviceSize();
    scrollDownAlgorithm(0.1, pStartY, pEndY);
  }

  /**
   * Generates collection of chunk classes
   *
   * @param collectionLocator
   * @param indexStartsFrom
   * @param chunkClass
   * @return Collection of chunk classes
   */
  public static List<? extends AbstractDeviceChunk> getCollectionOfChunks(
      String collectionLocator,
      int indexStartsFrom,
      Class<? extends AbstractDeviceChunk> chunkClass) {
    int resultsCount = 0;

    try {
      resultsCount = getQueryHelper().getMobileElementCount(collectionLocator);
    } catch (InvalidSelectorException e) {
      resultsCount = getDriver().findElements(By.xpath(collectionLocator)).size();
    }

    return (List<? extends AbstractDeviceChunk>)
        IntStream.rangeClosed(indexStartsFrom, resultsCount)
            .mapToObj(
                value ->
                    DeviceChunkFactory.create(chunkClass, collectionLocator + "[" + value + "]"))
            .collect(Collectors.toList());
  }

  /**
   * Generates collection of chunk classes
   *
   * @param collectionLocator
   * @param chunkClass
   * @return Collection of chunk classes
   */
  public static List<? extends AbstractDeviceChunk> getCollectionOfChunks(
      String collectionLocator, Class<? extends AbstractDeviceChunk> chunkClass) {
    return getCollectionOfChunks(collectionLocator, 1, chunkClass);
  }

  /** @return true, if Webview is displayed */
  public static boolean webviewIsDisplayed() {
    LOGGER.info("Checking Webview is displayed.");
    String webviewLocator =
        EnvironmentUtil.getInstance().getIsMobileIOS()
            ? "//XCUIElementTypeWebView"
            : "//android.webkit.WebView | //android.widget.FrameLayout[@package='com.android.chrome']";
    return getSyncHelper().isElementDisplayed(webviewLocator);
  }

  /** Switches to Webview */
  public static void switchToWebview() {
    LOGGER.info("Switching to Webview.");

    for (Object context : getDriver().getContextHandles()) {
      if (!context.toString().equals(TestData.NATIVE_APP_CONTEXT_NAME)) {
        getDriver().context(context.toString());
        return;
      }
    }

    throw new TestException("Couldn't find webview.");
  }

  /** Switches to NATIVE APP */
  public static void switchToNativeApp() {
    LOGGER.info("Switching to NATIVE_APP.");
    getDriver().context(TestData.NATIVE_APP_CONTEXT_NAME);
  }

  /*
   * Private methods
   */
  private static void scrollDownAlgorithm() {
    double pStartY = 0;
    double pEndY = 0;
    if (getEnv().getIsMobileIOS()) {
      pStartY = 0.8;
      pEndY = 0.2;
    } else { // Android scrolls faster so the start and end must be gentler
      pStartY = 0.80;
      pEndY = 0.20;
    }
    scrollDownAlgorithm(0.1, pStartY, pEndY);
  }

  private static void scrollDownCloseToMiddleAlgorithm() {
    double pStartY = 0;
    double pEndY = 0;
    if (getEnv().getIsMobileIOS()) {
      pStartY = 0.6;
      pEndY = -0.3;
    } else { // Android scrolls faster so the start and end must be gentler
      pStartY = 0.60;
      pEndY = 0.40;
    }
    scrollDownAlgorithm(0.1, pStartY, pEndY);
  }

  private static void scrollDownAlgorithm(double startX, double pStartY, double pEndY) {
    Dimension size = deviceSize;
    int startY = (int) (size.getHeight() * pStartY);
    int endY = (int) (size.getHeight() * pEndY);
    startX = (int) (size.getWidth() * startX);
    LOGGER.info("Swiping Down...");
    try {
      new TouchAction(getDriver())
          .press(PointOption.point((int) startX, startY))
          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
          .moveTo(PointOption.point((int) startX, endY))
          .release()
          .perform();
    } catch (WebDriverException wex) {
      LOGGER.warn("Swipe cause error, probably nothing to swipe: " + wex.getMessage());
    }
  }

  /**
   * Scroll Up Algorithm
   *
   * @param startXrelativeCooficient - position where to start swipe on vertical line
   */
  private static void scrollUpAlgorithm(double startXrelativeCooficient) {
    Dimension size = deviceSize;
    int startY = (int) (size.getHeight() * 0.4);
    int endY = (int) (size.getHeight() * 0.8);
    int startX = (int) (size.getWidth() * startXrelativeCooficient);
    LOGGER.info("Swiping Down...");
    try {
      new TouchAction(getDriver())
          .press(PointOption.point(startX, startY))
          .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
          .moveTo(PointOption.point(startX, endY))
          .release()
          .perform();
    } catch (WebDriverException wex) {
      LOGGER.warn("Swipe cause error, probably nothing to swipe: " + wex.getMessage());
    }
  }

  public static Boolean regexMatches(String pattern, String text) {
    return regexMatches(pattern, text, true);
  }

  public static Boolean regexMatches(String pattern, String text, Boolean caseInsensitive) {
    LOGGER.info(String.format("Trying to match %s on %s", pattern, text));
    Pattern p;
    if (caseInsensitive) {
      p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    } else {
      p = Pattern.compile(pattern);
    }
    return p.matcher(text).find();
  }

  public static AppiumDriver getDriver() {
    return (AppiumDriver) DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getDriver();
  }

  private static MobileNativeSyncHelper getSyncHelper() {
    return (MobileNativeSyncHelper)
        DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getSyncHelper();
  }

  private static DeviceElementQueryHelper getQueryHelper() {
    return (DeviceElementQueryHelper)
        DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getQueryHelper();
  }

  private static WebElementQueryHelper getWebQueryHelper() {
    return (WebElementQueryHelper)
        DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getQueryHelper();
  }

  public static EnvironmentUtil getEnv() {
    return EnvironmentUtil.getInstance();
  }

  private static MobileScreenshotManager getSnapshotManager() {
    return (MobileScreenshotManager)
        DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getSnapshotManager();
  }

  private static DeviceControl getDeviceControl() {
    return DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getDeviceControl();
  }

  private static AndroidDriver<MobileElement> getAndroidDriver() {
    return (AndroidDriver) DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getDriver();
  }

  private static IOSDriver<MobileElement> getIosDriver() {
    return (IOSDriver) DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getDriver();
  }
}
