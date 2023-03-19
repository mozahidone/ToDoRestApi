package com.mozahidone.camunda.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CalculateResultService implements JavaDelegate {

  private static final Logger LOGGER = LoggerFactory.getLogger(CalculateResultService.class);

  @Override
  public void execute(DelegateExecution execution) {
    LOGGER.info("calculating result of the exam");
  }

}
