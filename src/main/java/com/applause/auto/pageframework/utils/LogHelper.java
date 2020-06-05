package com.applause.auto.pageframework.utils;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import com.applause.auto.framework.pageframework.util.logger.LogController;

/** This class is singleton and only one instance can exist at any moment in time. */
public class LogHelper {

  private static final LogController logger =
      new LogController(MethodHandles.lookup().lookupClass());
  private static LogHelper instance;
  private static String lastErrorMessage;
  private static List<String> allErrorMessages = new ArrayList<>();
  private int testStepCount = 1;
  private int testStepSubCount = 1;
  private boolean stepCountPreviously, stepSubCountPreviously;

  private LogHelper() {}

  public static LogHelper getInstance() {
    if (instance == null) {
      instance = new LogHelper();
    }

    return instance;
  }

  /**
   * Prints log of specified text. If the log starts with '#', then the hashtag will be replaced
   * with an incremental number. This is primarily used to log correct step numbers for test steps.
   *
   * @param logText
   */
  public void info(String logText, Object... args) {
    String oldText = logText;

    if (logText.startsWith("# ")) {
      logger.info("");

      if (stepSubCountPreviously) {
        testStepCount++;
      }

      logText = logText.replace("#", testStepCount + ".");
      testStepCount++;
      testStepSubCount = 1;
      stepCountPreviously = true;
      stepSubCountPreviously = false;
    } else if (logText.startsWith("## ")) {
      logger.info("");

      if (stepCountPreviously) {
        testStepCount--;
      }

      logText = logText.replace("##", testStepCount + "." + testStepSubCount + ".");
      testStepSubCount++;
      stepCountPreviously = false;
      stepSubCountPreviously = true;
    } else if (logText.startsWith("@ ")) {
      logger.info("");
      logText = logText.replace("@", "==");
      logText += " ==";
    }

    if (args.length > 0) {
      if (!logText.contains("%s")) {
        throw new IllegalArgumentException(
            "String format has been specified without any format specifier (eg, '%s').");
      }

      logger.info(String.format(logText, args));
    } else {
      logger.info(logText);
    }

    if (oldText.startsWith("#")) {
      logger.info("---------------------------");
    }
  }

  /**
   * Logs text with debug state
   *
   * @param logText
   */
  public void debug(String logText) {
    logger.debug(logText);
  }

  /**
   * Logs text with error state
   *
   * @param logText
   */
  public void error(String logText, Object... args) {
    lastErrorMessage = logText;

    if (args.length > 0) {
      lastErrorMessage = String.format(logText, args);
      logger.error(lastErrorMessage);
    } else {
      logger.error(logText);
    }

    allErrorMessages.add(lastErrorMessage);
  }

  /**
   * Logs text with warn state
   *
   * @param logText
   */
  public void warn(String logText) {
    logger.warn(logText);
  }

  /** @return Last error message logged */
  public String getLastErrorMessage() {
    return lastErrorMessage;
  }

  /** @return list of error messages stored in test run */
  public List<String> getAllErrorMessages() {
    return allErrorMessages;
  }

  /** Reset test step count */
  public void setTestStepCounter() {
    testStepCount = 1;
    testStepSubCount = 1;
    stepCountPreviously = false;
    stepSubCountPreviously = false;
    allErrorMessages = new ArrayList<>();
  }

  public void pointInCode() {
    logger.info("Here - " + System.currentTimeMillis());
  }
}
