package com.swoop.devtest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/time")
public class TimeController {

  @RequestMapping("/now")
  public String now() {
    return "timezone and current time";
  }
 }