package com.swoop.devtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentTime {
  @JsonProperty("airportCode")
  public String airportCode;

  @JsonProperty("airportName")
  public String airportName;

  @JsonProperty("queueId")
  public String queueId;

  @JsonProperty("queueName")
  public String queueName;

  @JsonProperty("projectedWaitTime")
  public Integer projectedWaitTime;

  @JsonProperty("projectedMinWaitMinutes")
  public Integer projectedMinWaitMinutes;

  @JsonProperty("projectedMaxWaitMinutes")
  public Integer projectedMaxWaitMinutes;

  @JsonProperty("localTime")
  public String localTimeString;

  @JsonProperty("time")
  public String timeString;
}