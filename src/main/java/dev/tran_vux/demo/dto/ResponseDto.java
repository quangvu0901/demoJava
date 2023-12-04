package dev.tran_vux.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseDto {  // Data Transfer Object
    private boolean status;
    private String message;
}