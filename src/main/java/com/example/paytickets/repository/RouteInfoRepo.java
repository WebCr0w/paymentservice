package com.example.paytickets.repository;

import com.example.paytickets.entity.PaymentStatus;
import com.example.paytickets.entity.RouteInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteInfoRepo extends JpaRepository<RouteInfoEntity, Long> {

    @Query("select paymentStatus from RouteInfoEntity where id=:id")
    String getPaymentStatus(@Param("id") Long id);

    RouteInfoEntity findFirstByPaymentStatus(PaymentStatus status);


    @Query("SELECT id FROM RouteInfoEntity WHERE paymentStatus = null OR paymentStatus =:p1 order by id")
    List<Long> getIdByStatusPayment(PaymentStatus p1);


}