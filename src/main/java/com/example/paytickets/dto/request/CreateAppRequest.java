package com.example.paytickets.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class CreateAppRequest {

    @NotNull
    private int routeNumber;

    @NotNull
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private Date timeDeparture;


}
