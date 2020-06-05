package com.applause.auto.pageframework.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.applause.auto.framework.pageframework.devicecontrols.BaseDeviceControl;
import com.applause.auto.framework.pageframework.util.drivers.DriverWrapperManager;
import com.applause.auto.framework.pageframework.util.screenshots.MobileScreenshotManager;

import io.appium.java_client.AppiumDriver;

/** Class implements work with elements screenshots and comparison */
public class ElementScreenshotter {
  private static Logger logger = Logger.getLogger(ElementScreenshotter.class);

  /**
   * Taking element image from current layout depends on element coordinates
   *
   * @param element the element for the snapshot
   * @return return Buffered image of selected element
   */
  public static BufferedImage getElementImage(BaseDeviceControl element) {
    logger.info("Getting screenshot");
    BufferedImage image = null;
    try {
      image = ImageIO.read(getDriver().getScreenshotAs(OutputType.FILE));
    } catch (IOException e) {
      e.printStackTrace();
    }

    Point point = element.getLocation();
    Dimension dimension = element.getDimension();

    if ((point.getX() < 0) && (point.getY() < 0)) {
      logger.error(element + "Element is not visible");
      Assert.fail("Element is not visible");
    }
    // for IOS driver we need to take different coordinates then on android
    // due to pixel ratio
    double imageSizeCoefficient =
        (image.getHeight() / getDriver().manage().window().getSize().getHeight());
    int x =
        isIosDriver(element.getDriver())
            ? (int) (point.getX() * imageSizeCoefficient)
            : point.getX();
    int y =
        isIosDriver(element.getDriver())
            ? (int) (point.getY() * imageSizeCoefficient)
            : point.getY();
    int width = getWidth(dimension, imageSizeCoefficient);
    int height = getHeight(dimension, imageSizeCoefficient);

    if (System.getProperty("browser") != null) {
      if (System.getProperty("browser").toLowerCase().contains("ios_ss")
          && getDriver().getOrientation().equals(ScreenOrientation.LANDSCAPE)) {
        return image.getSubimage(y, x, height, width);
      }
    }

    if ((x == 0 && y == 0) && (width == dimension.getWidth() && height == dimension.getHeight())) {
      return image;
    }

    getSnapshotManager()
        .takeRemoteDeviceScreenshot("Element_Snapshot_" + System.currentTimeMillis());

    if (element.getDriver().getOrientation().equals(ScreenOrientation.LANDSCAPE)
        && !isIosDriver(element.getDriver())) {
      try {
        return image.getSubimage(x, y, width, height);
      } catch (RasterFormatException e) {
        return image.getSubimage(y, x, height, width);
      }
    }

    return image.getSubimage(x, y, width, height);
  }

  /**
   * Checking initial element image is equals with current element image
   *
   * @param initialImage image before the action
   * @param elementToTakePicture new image
   * @return return boolean result of image comparison
   */
  public static boolean checkElementHasChanged(
      BufferedImage initialImage, BaseDeviceControl elementToTakePicture) {
    try {
      new WebDriverWait(elementToTakePicture.getDriver(), 5)
          .until(ExpectedConditions.visibilityOf(elementToTakePicture.getMobileElement()));
    } catch (TimeoutException | NoSuchElementException e) {
    }
    BufferedImage img2 = getElementImage(elementToTakePicture);
    return getDifference(initialImage, img2) != 0;
  }

  /**
   * Checking initial element image is equals with current element image
   *
   * @param initialImage base image
   * @param imageToCompare image to compare it with
   * @return return boolean result of image comparison
   */
  public static boolean checkImageAreSimilar(
      BufferedImage initialImage, BufferedImage imageToCompare) {
    return getDifference(initialImage, imageToCompare) == 0;
  }

  /**
   * Checking platform for pixel ratio calculation
   *
   * @param driver
   * @return boolean result is IOS driver
   */
  private static boolean isIosDriver(AppiumDriver driver) {
    return driver
        .getCapabilities()
        .getCapability("platformName")
        .toString()
        .toLowerCase()
        .contains("ios");
  }

  private static int getHeight(Dimension dimension, double imageSizeCoefficient) {
    return isIosDriver(getDriver())
        ? (int) (dimension.getHeight() * imageSizeCoefficient)
        : dimension.getHeight();
  }

  private static int getWidth(Dimension dimension, double imageSizeCoefficient) {
    return isIosDriver(getDriver())
        ? (int) (dimension.getWidth() * imageSizeCoefficient)
        : dimension.getWidth();
  }

  /**
   * Gets image difference in percent.
   *
   * @param image1 the image 1
   * @param image2 the image 2
   * @return the image difference in percent
   */
  public static double getImageDifferenceInPercent(BufferedImage image1, BufferedImage image2) {
    double onePercent = (image1.getHeight() * image1.getWidth()) / 100;
    int diffInPixels = getDifference(image1, image2);
    return diffInPixels / onePercent;
  }

  /**
   * Gets image difference in percent.
   *
   * @param image1 the image 1
   * @param baseDeviceControl the base device control
   * @return the image difference in percent
   */
  public static double getImageDifferenceInPercent(
      BufferedImage image1, BaseDeviceControl baseDeviceControl) {
    double onePercent = (image1.getHeight() * image1.getWidth()) / 100;
    int diffInPixels = getDifference(image1, getElementImage(baseDeviceControl));
    return diffInPixels / onePercent;
  }

  @SuppressWarnings("rawtypes")
  private static AppiumDriver getDriver() {
    return (AppiumDriver) DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getDriver();
  }

  private static MobileScreenshotManager getSnapshotManager() {
    return (MobileScreenshotManager)
        DriverWrapperManager.getInstance().getPrimaryDriverWrapper().getSnapshotManager();
  }

  public static int getDifference(BufferedImage img1, BufferedImage img2) {
    // convert images to pixel arrays...
    final int w = img1.getWidth(), h = img1.getHeight(), highlight = Color.MAGENTA.getRGB();
    final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
    final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < p1.length; i++) {
      if (p1[i] != p2[i]) {
        result.add(highlight);
      }
    }
    return result.size();
  }
}
