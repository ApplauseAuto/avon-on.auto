package com.applause.auto.pageframework.views;

import com.applause.auto.framework.pageframework.device.AbstractDeviceView;
import com.applause.auto.framework.pageframework.device.factory.AndroidImplementation;
import com.applause.auto.framework.pageframework.device.factory.IosImplementation;
import com.applause.auto.pageframework.utils.LogHelper;

@AndroidImplementation(AndroidLearningHubView.class)
@IosImplementation(IosLearningHubView.class)
public abstract class LearningHubView extends AbstractDeviceView {

  protected static final LogHelper LOG = LogHelper.getInstance();

  @Override
  protected void waitUntilVisible() {}
}

class AndroidLearningHubView extends LearningHubView {}

class IosLearningHubView extends LearningHubView {}
