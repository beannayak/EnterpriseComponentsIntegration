package com.rasal.workflow.camunda;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class StubHello implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        log.info("executing task: {}", delegateExecution.getCurrentActivityId());

        boolean shouldThrowError = ThreadLocalRandom.current().nextBoolean();
        if (shouldThrowError) {
            log.warn("Error occured in: {}", delegateExecution.getCurrentActivityName());
            throw new BpmnError("Generating Error");
        }

        if (delegateExecution.getCurrentActivityId().equals("say_hello_again")) {
            Context context = (Context) delegateExecution.getVariable("context");
            context.response = "processing completed";
        }
    }
}
