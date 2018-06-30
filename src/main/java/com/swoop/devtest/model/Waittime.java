package com.swoop.devtest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swoop.devtest.model.CurrentTime;

public class Waittime {
  @JsonProperty("success")
  boolean success;

  @JsonProperty("current")
  List<CurrentTime> current;

  public CurrentTime getCurrentTime() {
    return current.get(0);
  }
}