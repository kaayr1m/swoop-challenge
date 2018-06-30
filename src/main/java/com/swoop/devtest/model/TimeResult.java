package com.swoop.devtest.model;

public class TimeResult extends Result {
  private final String result;

  public TimeResult(String resulString) {
    super(resulString != "");
    this.result = resulString;
  }

  public String getResult() {
    return result;
  }
}