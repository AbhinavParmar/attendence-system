package com.shivam.hrms.attendance.attendancesystem.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCheckInCheckOut {


    private Long employeeId;

    private String employeeName;

    private String opCode;

    private LocalDateTime currentTimeStamp;

}