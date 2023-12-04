package dev.tran_vux.demo.entity;
import lombok.Data;

@Data
public class Hotel {
    private String hotelId;

    private String hotelName;

    private Integer rate;

    private boolean status = true;

}
