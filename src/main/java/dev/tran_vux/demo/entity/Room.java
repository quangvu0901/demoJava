package dev.tran_vux.demo.entity;
import lombok.Data;

@Data
public class Room {
    private String roomId;

    private String employeeId;

    private Integer number;

    private double price;

    private String type;

    private boolean status = true;
}
