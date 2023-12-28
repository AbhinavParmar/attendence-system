package com.shivam.hrms.attendance.attendancesystem.dao;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Integer> {
 /*   EmployeeAttendance findAllById(Integer employeeId);*/

    //  public List<Employee_Attendance> findByEmployeeIDByOrderDescDate();

}
