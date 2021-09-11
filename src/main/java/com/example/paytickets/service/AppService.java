package com.example.paytickets.service;

import com.example.paytickets.converter.ApplicationRequestToRouteInfo;
import com.example.paytickets.dto.request.CreateAppRequest;
import com.example.paytickets.dto.response.AppResponse;
import com.example.paytickets.entity.PaymentStatus;
import com.example.paytickets.entity.RouteInfoEntity;
import com.example.paytickets.http.HttpPayment;
import com.example.paytickets.repository.RouteInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class AppService {

    @Autowired
    private  RouteInfoRepo routeInfoRepo;
    @Autowired
    private  ApplicationRequestToRouteInfo applicationRequestToRouteInfo;
    @Autowired
    private HttpPayment httpPayment;



    public void save(RouteInfoEntity routeInfoEntity){
        routeInfoRepo.save(routeInfoEntity);
    }

    public AppResponse save(CreateAppRequest request){

        RouteInfoEntity routeInfoEntity = routeInfoRepo.save(applicationRequestToRouteInfo.convert(request));

        return new AppResponse<>(routeInfoEntity.getId());
    }

    public AppResponse getStatus(Long id) {
        String status = routeInfoRepo.getPaymentStatus(id);
        return new AppResponse<>(status);
    }




}
