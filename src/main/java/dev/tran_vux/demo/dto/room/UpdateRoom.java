package dev.tran_vux.demo.dto.room;
import lombok.Data;

@Data
public class UpdateRoom {
    private String employeeId;

    private Integer number;

    private double price;

    private String type;

    private boolean status = true;
}
