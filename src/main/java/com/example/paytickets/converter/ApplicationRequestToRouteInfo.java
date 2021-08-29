package com.example.paytickets.converter;

import com.example.paytickets.dto.request.CreateAppRequest;
import com.example.paytickets.entity.RouteInfoEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRequestToRouteInfo implements Converter<CreateAppRequest, RouteInfoEntity> {


    @Override
    public RouteInfoEntity convert(CreateAppRequest createAppRequest) {
        RouteInfoEntity routeInfoEntity = new RouteInfoEntity();

        routeInfoEntity.setRouteNumber(createAppRequest.getRouteNumber());
        routeInfoEntity.setTimeDeparture(createAppRequest.getTimeDeparture());



        return routeInfoEntity;
    }
}
