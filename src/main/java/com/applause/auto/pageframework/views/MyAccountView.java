package com.applause.auto.pageframework.views;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.DeviceViewFactory;
import com.applause.auto.framework.pageframework.device.MobileElementLocator;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.framework.pageframework.devicecontrols.Text;
import com.applause.auto.framework.pageframework.util.logger.LogController;
import com.applause.auto.pageframework.utils.Helper;

@AndroidImplementation(AndroidMyAccountView.class)
@IosImplementation(IosMyAccountView.class)
public abstract class MyAccountView extends AbstractDeviceView {

  protected static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());

  @Override
  protected void waitUntilVisible() {
    syncHelper.waitForElementToAppear(getLocator(this, "getFirstQuestionText"));
  }

  /**
   * Checking for empty question items
   *
   * @return boolean
   */
  public boolean hasListEmptyQuestions() {
    logger.info("Checking for empty question items");
    boolean hasEmptyQuestion = false;

    for (Text category : getQuestionsList()) {
      if (category.getStringValue().isEmpty()) {
        hasEmptyQuestion = true;
        break;
      }
    }
    return hasEmptyQuestion;
  }

  /**
   * Press 'My Account' item from the list
   *
   * @return MyAccountView
   */
  public MyAccountView openFirstQuestion() {
    logger.info("Press close button");
    getFirstQuestionText().clickText();
    return DeviceViewFactory.create(MyAccountView.class);
  }

  /**
   * Return string value from the 'First Question Description' text element
   *
   * @return String
   */
  public String getFirstQuestionDescriptionTextString() {
    return getFirstQuestionDescriptionText().getStringValue();
  }

  /**
   * Return string value from the 'Was This Useful' text element
   *
   * @return String
   */
  public String getWasThisUsefulTextString() {
    Helper.scrollDownToElement(getLocator(this, "getWasThisUsefulText"), 5);
    return getWasThisUsefulText().getStringValue().trim();
  }

  /**
   * Return string value from the 'Yes' text element
   *
   * @return String
   */
  public String getYesTextString() {
    return getYesText().getStringValue();
  }

  /**
   * Return string value from the 'No' text element
   *
   * @return String
   */
  public String getNoTextString() {
    return getNoText().getStringValue();
  }

  public abstract List<Text> getQuestionsList();

  @MobileElementLocator(
      android =
          "//android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[2]",
      iOS = "**/*[$name=\"faqAnswerCell index 1\"$]/XCUIElementTypeTextView")
  protected Text getFirstQuestionDescriptionText() {
    return new Text(getLocator(this, "getFirstQuestionDescriptionText"));
  }

  @MobileElementLocator(
      android =
          "//android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView",
      iOS = "**/XCUIElementTypeCell[$name='faqCell index 0'$][1]/XCUIElementTypeStaticText")
  protected Text getFirstQuestionText() {
    return new Text(getLocator(this, "getFirstQuestionText"));
  }

  @MobileElementLocator(
      android = "usefulTv",
      iOS = "**/*[$name=\"faqAnswerCell index 1\"$]/XCUIElementTypeStaticText")
  protected Text getWasThisUsefulText() {
    return new Text(getLocator(this, "getWasThisUsefulText"));
  }

  @MobileElementLocator(android = "yesTv", iOS = "yesButton")
  protected Text getYesText() {
    return new Text(getLocator(this, "getYesText"));
  }

  @MobileElementLocator(android = "noTv", iOS = "noButton")
  protected Text getNoText() {
    return new Text(getLocator(this, "getNoText"));
  }
}

class AndroidMyAccountView extends MyAccountView {

  @Override
  public List<Text> getQuestionsList() {
    String locator =
        "//android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView";
    return IntStream.range(1, queryHelper.getMobileElementCount(locator))
        .mapToObj(
            value ->
                new Text(
                    String.format(
                        "//android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup"
                            + "[%s]"
                            + "/android.widget.TextView",
                        value)))
        .collect(Collectors.toList());
  }
}

class IosMyAccountView extends MyAccountView {

  @Override
  public List<Text> getQuestionsList() {
    String locator = "**/XCUIElementTypeCell[$name=\"faqCell index 0\"$]";
    return IntStream.rangeClosed(1, queryHelper.getMobileElementCount(locator))
        .mapToObj(
            value ->
                new Text(
                    String.format(
                        "**/XCUIElementTypeCell[$name=\"faqCell index 0\"$]"
                            + "[%s]"
                            + "/XCUIElementTypeStaticText",
                        value)))
        .collect(Collectors.toList());
  }
}
