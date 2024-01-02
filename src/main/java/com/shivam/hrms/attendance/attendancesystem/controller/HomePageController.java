package com.shivam.hrms.attendance.attendancesystem.controller;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeCheckInCheckOut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomePageController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        EmployeeCheckInCheckOut employeeCheckInCheckOut = new EmployeeCheckInCheckOut();
        model.addAttribute("employeeCheckInCheckOut", employeeCheckInCheckOut);
        return "home";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
