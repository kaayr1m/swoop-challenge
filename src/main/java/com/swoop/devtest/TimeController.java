package com.swoop.devtest;

import com.swoop.devtest.model.CurrentTime;
import com.swoop.devtest.model.TimeResult;
import com.swoop.devtest.network.ThirdParty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/time")
public class TimeController {

  private ThirdParty thirdParty;

  @Autowired
  public TimeController(ThirdParty thirdParty) {
    this.thirdParty = thirdParty;
  }

  @RequestMapping("/now")
  public TimeResult now() {
    try {
      CurrentTime c = thirdParty.getTime();
      if (c != null) {
        return new TimeResult(c.timeString);
      }
		  return new TimeResult("");
    } catch (Exception e) {
      return new TimeResult("");
    }
  }
 }