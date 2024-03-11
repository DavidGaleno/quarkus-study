package galeno.david.services;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloService {

  public String oi() {
    Optional<String> teste;
    return "Hello";
  }
}
