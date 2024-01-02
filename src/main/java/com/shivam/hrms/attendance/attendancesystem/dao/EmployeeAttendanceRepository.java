package com.shivam.hrms.attendance.attendancesystem.dao;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
 /*   EmployeeAttendance findAllById(Integer employeeId);*/

    //  public List<Employee_Attendance> findByEmployeeIDByOrderDescDate();

    //Custom Search Query
    @Query(value = "select * from employee_attendance attendance where attendance.employee_name like %:keyword% or attendance.office_location like %:keyword%", nativeQuery = true)
    List<EmployeeAttendance> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from employee_attendance attendance where attendance.attendance_date = :attendanceDate", nativeQuery = true)
    List<EmployeeAttendance> findByDate(@Param("attendanceDate") LocalDate attendanceDate);

    @Query(value = "select * from employee_attendance attendance where attendance.attendance_date BETWEEN :fromAttendanceDate AND :toAttendanceDate", nativeQuery = true)
    List<EmployeeAttendance> findByDateRange(@Param("fromAttendanceDate") LocalDate fromAttendanceDate, @Param("toAttendanceDate") LocalDate toAttendanceDate);


}
