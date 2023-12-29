package com.shivam.hrms.attendance.attendancesystem.service;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;

import java.util.List;


public interface EmployeeAttendanceService {

    List<EmployeeAttendance> findAll();

    EmployeeAttendance findById(Long employeeId);

    EmployeeAttendance save(EmployeeAttendance theEmployee);

    EmployeeAttendance updateById(EmployeeAttendance employeeAttendance);

    EmployeeAttendance deleteById(Long employeeId);

}
