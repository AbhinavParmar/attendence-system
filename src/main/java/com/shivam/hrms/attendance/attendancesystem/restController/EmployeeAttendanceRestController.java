package com.shivam.hrms.attendance.attendancesystem.restController;

import com.shivam.hrms.attendance.attendancesystem.dao.EmployeeAttendanceRepository;
import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import com.shivam.hrms.attendance.attendancesystem.service.EmployeeAttendanceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeAttendanceRestController {

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;

    // Logging Utils
    private static final Logger log = LoggerFactory.getLogger(EmployeeAttendanceRestController.class);

    //Endpoint - Get All Employee Attendance Request
    @GetMapping("/employeeAttendance")
    public List<EmployeeAttendance> getAllEmployeeAttendance() {

       List<EmployeeAttendance> employeeAttendanceList = employeeAttendanceService.findAll();

        return employeeAttendanceList;
    }

    //Endpoint - Get By Employee ID Attendance Request
    @GetMapping("/employeeAttendance/{employeeId}")
    public EmployeeAttendance getEmployeeAttendaneByID(@PathVariable Integer employeeId){
        EmployeeAttendance employeeAttendance = employeeAttendanceService.findById(Long.valueOf(employeeId));
        return employeeAttendance;
    }
    /*    @GetMapping("/employeeAttendance/{employeeID}")
    public EmployeeAttendance getAllAttendanceReport(@PathVariable int employeeId) {
       EmployeeAttendance employeeAttendance= employeeAttendanceService.findById(employeeId);
       return employeeAttendance;
    }*/

    //Endpoint - Post New Attendance Request
    @PostMapping("/employeeAttendance")
    EmployeeAttendance newEmployee(@RequestBody EmployeeAttendance newEmployeeAttendance) {
        return employeeAttendanceService.save(newEmployeeAttendance);
    }

    //Endpoint - PUT(Change) By Employee ID Attendance Request
    @PutMapping("/employees/{employeeId}")
    EmployeeAttendance updateEmployeeAttendance(@RequestBody EmployeeAttendance newEmployeeAttendance, @PathVariable Long employeeId) {

        return employeeAttendanceService.findById(employeeId);
    }

    //Endpoint - Delete By Employee ID Attendance Request
    @DeleteMapping("/employeeAttendance/{employeeId}")
    void deleteEmployee(@PathVariable Long employeeId) {
            employeeAttendanceService.deleteById(employeeId);
        }

}