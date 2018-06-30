package com.swoop.devtest.model;

public class MathResult extends Result {
  private final Double result;

  public MathResult(Double r) {
    super(true);
    this.result = r;
  }

  public Double getResult() {
    return result;
  }
}