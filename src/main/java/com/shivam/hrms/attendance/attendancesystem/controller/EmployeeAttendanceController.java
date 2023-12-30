package com.shivam.hrms.attendance.attendancesystem.controller;

import com.shivam.hrms.attendance.attendancesystem.service.EmployeeAttendanceService;
import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Controller
@RequestMapping("/employeesAttendance")
public class EmployeeAttendanceController {

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;

    /*@Autowired
    public EmployeeAttendanceController(EmployeeAttendanceService employeeAttendanceService) {
        employeeAttendanceService = employeeAttendanceService;
    }*/

    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "login-attendance-system";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";
    }

    // add mapping for "/list"
    @GetMapping("/listAttendance")
    public String listEmployeesAttendance(Model theModel) {

        // get the employees from db
        List<EmployeeAttendance> theEmployeesAttendance = employeeAttendanceService.findAll();
        System.out.println(theEmployeesAttendance);

        // add to the spring model
        theModel.addAttribute("employeesAttendance", theEmployeesAttendance);

        return "employees-attendance/list-employees-attendance";
    }

    @GetMapping("/showFormCreateAttendance")
    public String showFormCreateAttendance(Model theModel) {

        // create model attribute to bind form data
        EmployeeAttendance EmployeeAttendance = new EmployeeAttendance();

        theModel.addAttribute("employeeAttendance", EmployeeAttendance);

        return "employees-attendance/employee-attendance-form";
    }

    @GetMapping("/showAttendanceFormForUpdate")
    public String showFormForUpdateAttendance(@RequestParam("employeeId") Long employeeId,
                                    Model theModel) {

        // get the employee from the service
        EmployeeAttendance employeeAttendance = employeeAttendanceService.findById(employeeId);

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employeeAttendance", employeeAttendance);

        // send over to our form
        return "employees-attendance/employee-attendance-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employeeAttendance") EmployeeAttendance employeeAttendance) {

        System.out.println(employeeAttendance);

        // save the employee
        employeeAttendanceService.save(employeeAttendance);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employeesAttendance/listAttendance";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") Long employeeId) {

        // delete the employee
        employeeAttendanceService.deleteById(employeeId);

        // redirect to /employees/list
        return "redirect:/employeesAttendance/listAttendance";
    }
}