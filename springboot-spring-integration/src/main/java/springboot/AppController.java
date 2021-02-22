package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/integration")
public class AppController {

  @Autowired
  private IntegrationGateway integrationGateway;

  @GetMapping("/initial")
  public String initial(@RequestParam("name") String name) throws ExecutionException, InterruptedException {
    //Future<String> f = integrationGateway.sendMessage(name);
    CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> integrationGateway.sendMessage(name));
    return cf.getNow("processing");
  }

  @PostMapping("/transformer")
  public String transformer(@RequestBody Student student) {
    return integrationGateway.sendMessage(student);
  }

  @PostMapping("/headerEnricher")
  public String headerEnricher(@RequestBody Student student) {
    return integrationGateway.headerEnricher(student);
  }
}