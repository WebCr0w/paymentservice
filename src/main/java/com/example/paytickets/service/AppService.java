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

    public void paymentService(){
        Thread run = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        String x = "1";
                        Long id = Long.valueOf(11);

                        if (routeInfoRepo.getPaymentStatus(id) == null || routeInfoRepo.getPaymentStatus(id).equals("NEW") || routeInfoRepo.getPaymentStatus(id).equals("PROCESSED") ) {

                            RouteInfoEntity statusUpdate = routeInfoRepo.getById(id);

                            String getStatus = httpPayment.getStatus().getResult();
                            statusUpdate.setPaymentStatus(PaymentStatus.valueOf(getStatus));
                            routeInfoRepo.save(statusUpdate);
                        } else id++;



                        Thread.sleep(1000);

                    } catch (InterruptedException | IOException ex){}
                }
            }
        });
        run.start();
    }

    public AppResponse setStatus(Long id) throws IOException {

        if (routeInfoRepo.getPaymentStatus(id) == null || routeInfoRepo.getPaymentStatus(id).equals("NEW") || routeInfoRepo.getPaymentStatus(id).equals("PROCESSED") ) {

            RouteInfoEntity statusUpdate = routeInfoRepo.getById(id);

            String getStatus = httpPayment.getStatus().getResult();
            statusUpdate.setPaymentStatus(PaymentStatus.valueOf(getStatus));
            routeInfoRepo.save(statusUpdate);
            String status = routeInfoRepo.getPaymentStatus(id);
            return new AppResponse<>(status);
        } else{

            String status = routeInfoRepo.getPaymentStatus(id);
            return new AppResponse<>(status);
        }
    }
}
