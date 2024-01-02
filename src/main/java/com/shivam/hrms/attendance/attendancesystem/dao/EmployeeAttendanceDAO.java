package com.shivam.hrms.attendance.attendancesystem.dao;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;

import java.util.List;

public interface EmployeeAttendanceDAO {
    List<EmployeeAttendance> findAll();
 /*   EmployeeAttendance findByID(int employeeId);*/

}
