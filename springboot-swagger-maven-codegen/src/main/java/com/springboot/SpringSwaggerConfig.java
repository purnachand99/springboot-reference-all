package com.springboot;

import com.swagger.client.codegen.rest.api.EmployeeProfileControllerApi;
import com.swagger.client.codegen.rest.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.Api;

@Configuration
public class SpringSwaggerConfig {

  @Value("${employee.profiles.host.uri}")
  private String hostInfoBasePath;

  @Bean
  public ApiClient apiClient() {
    return new ApiClient().setBasePath(hostInfoBasePath);
  }

  @Bean
  public EmployeeProfileControllerApi employeeProfileControllerApi() {
    return new EmployeeProfileControllerApi(apiClient());
  }
}
