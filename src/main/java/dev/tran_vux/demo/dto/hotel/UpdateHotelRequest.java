package dev.tran_vux.demo.dto.hotel;

import lombok.Data;

@Data
public class UpdateHotelRequest {
    private String hotelName;

    private boolean status;

    private Integer rate;

}
