package org.amal.postapispring;

import org.springframework.stereotype.Service;

@Service("anotherPostService")
public class AnotherPostService implements PostService{
  @Override
  @TimeMonitoring
  public String doSomething() {
    for(int i=0; i<100000000; i++){}
    return "In Another Post Service";
  }
}
