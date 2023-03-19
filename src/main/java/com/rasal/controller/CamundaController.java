package com.rasal.controller;

import com.rasal.camunda.Context;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/camunda")
@Slf4j
public class CamundaController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/start")
    public String getMapping() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("context", new Context());
        ProcessInstanceWithVariables processInstance = runtimeService.createProcessInstanceByKey("my_project_project")
                .setVariables(variables)
                .executeWithVariablesInReturn();

        List<String> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list()
                .stream()
                .map(a -> Pair.of(a.getId().split(":")[1], a.getActivityName()))
                .sorted(Comparator.comparing(Pair::getLeft))
                .map(Pair::getRight)
                .collect(Collectors.toList());
        log.info("activities ran in order of: {}", historicActivityInstanceList);

        return processInstance.getVariables().getValue("context", Context.class).response;
    }
}
