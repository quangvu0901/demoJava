package dev.tran_vux.demo.dto.hotel;

import lombok.Data;

@Data
public class CreateHotelRequest {
    private String hotelId;

    private String hotelName;

    private Integer rate;
}
