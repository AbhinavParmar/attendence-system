package com.shivam.hrms.attendance.attendancesystem.service;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeAttendanceService {

    List<EmployeeAttendance> findAll();

    EmployeeAttendance findById(Long employeeId);

    EmployeeAttendance save(EmployeeAttendance theEmployee);

    EmployeeAttendance updateById(EmployeeAttendance employeeAttendance);

    EmployeeAttendance deleteById(Long employeeId);

    List<EmployeeAttendance> getByKeyword(String keyword);

    //Custom query
    List<EmployeeAttendance> getByDate(LocalDate attendanceDate);

    List<EmployeeAttendance> getByDateRange(LocalDate fromAttendanceDate, LocalDate toAttendanceDate);
}
