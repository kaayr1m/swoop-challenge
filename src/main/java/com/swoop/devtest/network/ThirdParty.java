package com.swoop.devtest.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swoop.devtest.model.CurrentTime;
import com.swoop.devtest.model.Waittime;

import org.springframework.stereotype.Service;

@Service
public class ThirdParty {

  public CurrentTime getTime() throws Exception {

    // Mocked: https://waittimeqa.api.aero/waittime/v1/current/den
    String url = "http://5b37a1746223c40014605873.mockapi.io/mock/waittime";

    HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();

    int responseCode = conn.getResponseCode();
    if (responseCode == 200) {
      StringBuffer response = new StringBuffer();
      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();

      ObjectMapper mapper = new ObjectMapper();
      List<Waittime> waittime = mapper.readValue(response.toString(), new TypeReference<List<Waittime>>(){});
      
      return waittime.get(0).getCurrentTime();
    }

    return null;
  }

}