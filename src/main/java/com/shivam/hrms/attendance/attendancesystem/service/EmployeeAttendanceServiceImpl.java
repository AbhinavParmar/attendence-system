package com.shivam.hrms.attendance.attendancesystem.service;

import com.shivam.hrms.attendance.attendancesystem.dao.EmployeeAttendanceRepository;
import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;

    @Override
    public List<EmployeeAttendance> findAll() {
        return employeeAttendanceRepository.findAll();
    }

    @Override
    public EmployeeAttendance findById(Long employeeId) {

        Optional<EmployeeAttendance> result = employeeAttendanceRepository.findById(employeeId);
        EmployeeAttendance employeeAttendance = null;
        if (result.isPresent()) {
            employeeAttendance = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find any attendance for Employee ID - " + employeeId);
        }
        return employeeAttendance;
    }

    @Override
    public EmployeeAttendance save(EmployeeAttendance employeeAttendance) {
        employeeAttendanceRepository.save(employeeAttendance);
        return employeeAttendance;
    }

    @Override
    public EmployeeAttendance updateById(EmployeeAttendance employeeAttendance) {
        return new EmployeeAttendance();
    }

    @Override
    public EmployeeAttendance deleteById(Long employeeId) {
        employeeAttendanceRepository.deleteById(employeeId);
        return null;
    }
/*
    @Override
    public List<EmployeeAttendance> findByKeyword(String keyword) {
        employeeAttendanceRepository.findByKeyword(keyword);
        return null;
    }*/

    public List<EmployeeAttendance> getByKeyword(String keyword){
        List<EmployeeAttendance> employeeAttendanceByKeywordResult
                = employeeAttendanceRepository.findByKeyword(keyword);
        return employeeAttendanceByKeywordResult;
    }

    public List<EmployeeAttendance> getByDate(LocalDate attendanceDate){
        List<EmployeeAttendance> employeeAttendanceByDateResult
                = employeeAttendanceRepository.findByDate(attendanceDate);
        return employeeAttendanceByDateResult;
    }

    @Override
    public List<EmployeeAttendance> getByDateRange(LocalDate fromAttendanceDate, LocalDate toAttendanceDate) {
        List<EmployeeAttendance> employeeAttendanceByDateRangeResult
                = employeeAttendanceRepository.findByDateRange(fromAttendanceDate,toAttendanceDate);

        return employeeAttendanceByDateRangeResult;
    }


}