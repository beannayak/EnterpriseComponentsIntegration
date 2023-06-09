package com.rasal.configurations;

import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class CamundaConfiguration {

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SpringProcessEngineConfiguration engineConfiguration(
          DataSource dataSource,
          PlatformTransactionManager transactionManager,
          @Value("classpath*:*.bpmn") Resource[] deploymentResources) {
    SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();

    configuration.setHistoryLevel(HistoryLevel.HISTORY_LEVEL_FULL);
    configuration.setProcessEngineName("engine");
    configuration.setDataSource(dataSource);
    configuration.setTransactionManager(transactionManager);
    configuration.setDatabaseSchemaUpdate("true");
    configuration.setJobExecutorActivate(false);
    configuration.setDeploymentResources(deploymentResources);

    return configuration;
  }

  @Bean
  public ProcessEngineFactoryBean engineFactory(SpringProcessEngineConfiguration engineConfiguration) {
    ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
    factoryBean.setProcessEngineConfiguration(engineConfiguration);
    return factoryBean;
  }

  @Bean
  public ProcessEngine processEngine(ProcessEngineFactoryBean factoryBean) throws Exception {
    return factoryBean.getObject();
  }

  @Bean
  public RepositoryService repositoryService(ProcessEngine processEngine) {
    return processEngine.getRepositoryService();
  }

  @Bean
  public RuntimeService runtimeService(ProcessEngine processEngine) {
    return processEngine.getRuntimeService();
  }

  @Bean
  public TaskService taskService(ProcessEngine processEngine) {
    return processEngine.getTaskService();
  }

  @Bean
  public HistoryService historyService(ProcessEngine processEngine) {
    return processEngine.getHistoryService();
  }

  @Bean
  public ManagementService managementService(ProcessEngine processEngine) {
    return processEngine.getManagementService();
  }
}