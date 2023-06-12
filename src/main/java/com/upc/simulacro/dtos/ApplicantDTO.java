package com.upc.simulacro.dtos;

import com.upc.simulacro.entitys.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicantDTO {
    private Long dni;
    private String   name;
    private  int age;
    private boolean bag;

    private Status status;
    private int salary;
    private double calification;
}
