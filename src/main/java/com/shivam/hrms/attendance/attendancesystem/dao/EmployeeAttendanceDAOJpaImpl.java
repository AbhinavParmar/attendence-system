package com.shivam.hrms.attendance.attendancesystem.dao;

import com.shivam.hrms.attendance.attendancesystem.entity.EmployeeAttendance;
import com.shivam.hrms.attendance.attendancesystem.service.EmployeeAttendanceService;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAttendanceDAOJpaImpl implements EmployeeAttendanceDAO {

    private EmployeeAttendanceService employeeAttendanceService;

    /*private EntityManager entityManager;*/
    @Override
    public List<EmployeeAttendance> findAll() {

        /*// create a query
        TypedQuery<EmployeeAttendance> findAllAttendanceQuery =
                entityManager.createQuery("from employee_attendance", EmployeeAttendance.class);
        // execute query and get result list


        List<EmployeeAttendance> employeeAttendanceList = findAllAttendanceQuery.getResultList();
        */

        // return the results
        List<EmployeeAttendance> employeeAttendanceList = employeeAttendanceService.findAll();

        return employeeAttendanceList;
    }

   /* @Override
    public EmployeeAttendance findByID(int employeeID) {

        EmployeeAttendance employeeAttendance = employeeAttendanceService.findById(employeeID);
        return employeeAttendance;

    }*/
}
