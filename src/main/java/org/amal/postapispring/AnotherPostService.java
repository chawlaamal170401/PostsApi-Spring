package org.amal.postapispring;

import org.springframework.stereotype.Service;

@Service("anotherPostService")
public class AnotherPostService implements PostService{
  @Override
  public String doSomething() {
    return "In Another Post Service";
  }
}
