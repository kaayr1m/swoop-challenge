package com.swoop.devtest;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.swoop.devtest.model.MathResult;

@RestController
@RequestMapping("/math")
public class MathController {

  @RequestMapping("/add")
  public MathResult getAdd(@RequestParam(name="n1") double n1, @RequestParam(name="n2") double n2) {
    return new MathResult(n1 + n2);
  }

  @RequestMapping(value="/add", 
  method = RequestMethod.POST,
  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public MathResult postAdd(@RequestBody MultiValueMap<String, String> formData) {
    return new MathResult(Double.parseDouble(formData.getFirst("n1"))+ Double.parseDouble(formData.getFirst("n2")));
  }

 }