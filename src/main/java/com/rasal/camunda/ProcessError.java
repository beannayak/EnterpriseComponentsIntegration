package com.rasal.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ProcessError implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        ((Context) delegateExecution.getVariable("context")).response = "Error processing";
    }
}
