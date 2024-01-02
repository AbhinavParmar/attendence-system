package com.shivam.hrms.attendance.attendancesystem.controller;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeCheckInCheckOut;
import com.shivam.hrms.attendance.attendancesystem.service.EmployeeAttendanceService;
import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import com.shivam.hrms.attendance.attendancesystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Controller
@RequestMapping("/employeesAttendance")
public class EmployeeAttendanceController {

    @Autowired
    private EmployeeAttendanceService employeeAttendanceService;
    private EmployeeService employeeService;

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

    @PostMapping("/employeeCheckIn")
    public String checkInAttendance(EmployeeCheckInCheckOut employeeCheckInCheckOut) {
        EmployeeAttendance checkInEntry = new EmployeeAttendance();
        System.out.println(employeeCheckInCheckOut);
        if (employeeCheckInCheckOut.getOpCode().equals("checkIn")) {
            checkInEntry.setEmployee(employeeService.findById(employeeCheckInCheckOut.getEmployeeId()));
            checkInEntry.setAttendanceDate(employeeCheckInCheckOut.getCurrentTimeStamp().toLocalDate());
            checkInEntry.setCheckinTime(employeeCheckInCheckOut.getCurrentTimeStamp());
            employeeAttendanceService.save(checkInEntry);
        }
        // use a redirect to prevent duplicate submissions
        return "/home";
    }

    // add mapping for "/list"
    @GetMapping("/listAttendance")
    public String listEmployeesAttendance(Model theModel, @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        // get the employees from db
        List<EmployeeAttendance> theEmployeesAttendance = employeeAttendanceService.findAll();
        System.out.println(theEmployeesAttendance);

        // add to the spring model
        theModel.addAttribute("employeesAttendance", theEmployeesAttendance);
        theModel.addAttribute("keyword", keyword);
        return "employees-attendance/list-employees-attendance";

    }

    // add mapping for "/list"

    @RequestMapping(path = {"/","/search"})
    public String searchByKeyword(EmployeeAttendance employeeAttendance, Model model, String keyword) {
        if(keyword!=null) {
            List<EmployeeAttendance> searchAttendanceList = employeeAttendanceService.getByKeyword(keyword);
            model.addAttribute("employeesAttendance", searchAttendanceList);
            model.addAttribute("keyword", keyword);
        }else {
            List<EmployeeAttendance> searchAttendanceList = employeeAttendanceService.findAll();
            model.addAttribute("employeesAttendance", searchAttendanceList);}
        return "employees-attendance/list-employees-attendance";
    }

    @RequestMapping(path = {"/","/searchByDate"})
    public String searchByDate(EmployeeAttendance employeeAttendance, Model model, LocalDate attendanceDate) {
        if(!attendanceDate.equals(null)) {
            List<EmployeeAttendance> searchAttendanceList = employeeAttendanceService.getByDate(attendanceDate);
            model.addAttribute("employeesAttendance", searchAttendanceList);
            model.addAttribute("attendanceDate", attendanceDate);
        }else {
            List<EmployeeAttendance> searchAttendanceList = employeeAttendanceService.findAll();
            model.addAttribute("employeesAttendance", searchAttendanceList);
        }
        return "employees-attendance/list-employees-attendance";
    }

    @RequestMapping(path = {"/","/searchByDateRange"})
    public String searchByDateRange(EmployeeAttendance employeeAttendance, Model model, LocalDate fromAttendanceDate,LocalDate toAttendanceDate) {
        if(!(fromAttendanceDate.equals(null) || toAttendanceDate.equals(null))) {
            List<EmployeeAttendance> searchAttendanceList = employeeAttendanceService.getByDateRange(fromAttendanceDate,toAttendanceDate);
            model.addAttribute("employeesAttendance", searchAttendanceList);
            model.addAttribute("fromAttendanceDate", fromAttendanceDate);
            model.addAttribute("toAttendanceDate", toAttendanceDate);
        }else {
            List<EmployeeAttendance> searchAttendanceList = employeeAttendanceService.findAll();
            model.addAttribute("employeesAttendance", searchAttendanceList);}
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