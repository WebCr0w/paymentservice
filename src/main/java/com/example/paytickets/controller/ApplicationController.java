package com.example.paytickets.controller;


import com.example.paytickets.dto.request.CreateAppRequest;
import com.example.paytickets.dto.response.AppResponse;
import com.example.paytickets.entity.PaymentStatus;
import com.example.paytickets.http.HttpPayment;
import com.example.paytickets.service.AppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;


@RestController
public class ApplicationController {

    private final AppService appService;
    private final HttpPayment httpPayment;

    public ApplicationController(AppService appService, HttpPayment httpPayment) {
        this.appService = appService;
        this.httpPayment = httpPayment;
    }


    @PostMapping("/routeInfo")
    public ResponseEntity sendRouteInfo(@RequestBody @Valid CreateAppRequest createAppRequest){
      return ResponseEntity.ok(appService.save(createAppRequest));
    }

    @GetMapping("/getstatus")
    public ResponseEntity<AppResponse> getStatus(@RequestParam Long id) throws IOException {

        httpPayment.getStatus();
        return ResponseEntity.ok(appService.getStatus(id));

    }

    @GetMapping("/randomstatus")
    public ResponseEntity<AppResponse> getRandomStatus(){
        return ResponseEntity.ok(new AppResponse<>(PaymentStatus.generateRandomStatus().toString()));
    }

    @GetMapping("/setstatus")
    public ResponseEntity setStatus(@RequestParam Long id) throws IOException {


        return ResponseEntity.ok(appService.setStatus(id));
    }
    @PostMapping("/startpayment")
    public ResponseEntity startPay(){

        appService.paymentService();
        return ResponseEntity.ok("ZBC");
    }
}
