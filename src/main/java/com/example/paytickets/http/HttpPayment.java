package com.example.paytickets.http;

import com.example.paytickets.dto.response.AppResponse;
import com.example.paytickets.entity.PaymentStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.apache.http.client.fluent.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class HttpPayment {

    public AppResponse<String> getStatus() throws IOException {

        Request request = Request.Get("http://localhost:4567/randomstatus");
        HttpEntity resp = request.execute().returnResponse().getEntity();

        BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getContent()));
        StringBuilder response = new StringBuilder();
        String line;

        while((line = rd.readLine()) != null){
            response.append(line);
            response.append('\r');
        }


        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.toString(), AppResponse.class);

    }
}
