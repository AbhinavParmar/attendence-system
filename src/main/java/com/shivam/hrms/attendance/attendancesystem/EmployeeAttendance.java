package com.shivam.hrms.attendance.attendancesystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeAttendance {
    String name;
    String department;
    String time;
    String image;
}
