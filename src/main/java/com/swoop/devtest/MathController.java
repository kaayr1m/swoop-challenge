package com.swoop.devtest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swoop.devtest.Result;

@RestController
public class MathController {

  @RequestMapping("/add")
  public Result add(@RequestParam(name="n1") double n1, @RequestParam(name="n2") double n2) {
    return new Result(n1 + n2);
  }
 }