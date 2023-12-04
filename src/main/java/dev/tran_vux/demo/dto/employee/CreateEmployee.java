package dev.tran_vux.demo.dto.employee;
import lombok.Data;

@Data
public class CreateEmployee {
    private String employeeId;

    private String roomId;

    private String name;

    private Integer age;

    private String phone;

    private Integer roll;
}
