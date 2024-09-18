package org.amal.postapispring;

import org.springframework.stereotype.Service;

@Service("fakePostService")
public class FakePostService implements PostService{

  @Override
  public String doSomething() {
    return "In FakePostService";
  }
}
