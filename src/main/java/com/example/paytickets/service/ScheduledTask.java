package com.example.paytickets.service;

import com.example.paytickets.entity.PaymentStatus;
import com.example.paytickets.entity.RouteInfoEntity;
import com.example.paytickets.http.HttpPayment;
import com.example.paytickets.repository.RouteInfoRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Component
@Transactional
public class ScheduledTask {

    private final RouteInfoRepo routeInfoRepo;
    private final HttpPayment httpPayment;

    public ScheduledTask(RouteInfoRepo routeInfoRepo, HttpPayment httpPayment) {
        this.routeInfoRepo = routeInfoRepo;
        this.httpPayment = httpPayment;
    }

    @Scheduled(fixedRate = 60000)
    public void payment() throws IOException {

        List<Long> paymentList = routeInfoRepo.getIdByStatusPayment(PaymentStatus.PROCESSED);



            RouteInfoEntity statusUpdate = routeInfoRepo.getById(paymentList.get(0));

            String getStatus = httpPayment.getStatus().getResult();
            statusUpdate.setPaymentStatus(PaymentStatus.valueOf(getStatus));
            routeInfoRepo.save(statusUpdate);



    }

}
