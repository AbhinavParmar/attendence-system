package com.shivam.hrms.attendance.attendancesystem.service;

import com.shivam.hrms.attendance.attendancesystem.dao.EmployeeAttendanceRepository;
import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {
    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;
    @Autowired
    public EmployeeAttendanceServiceImpl(EmployeeAttendanceRepository employeeAttendanceRepository) {
        employeeAttendanceRepository = employeeAttendanceRepository;
    }

    @Override
    public List<EmployeeAttendance> findAll() {
        return employeeAttendanceRepository.findAll();
    }

    @Override
    public EmployeeAttendance findById(Long theId) {

        Optional<EmployeeAttendance> result = employeeAttendanceRepository.findById(Math.toIntExact(theId));
        EmployeeAttendance employeeAttendance = null;
        if (result.isPresent()) {
            employeeAttendance = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find any attendance for Employee ID - " + theId);
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
    public EmployeeAttendance deleteById(Long theId) {
        employeeAttendanceRepository.deleteById(Math.toIntExact(theId));
        return null;
    }

}