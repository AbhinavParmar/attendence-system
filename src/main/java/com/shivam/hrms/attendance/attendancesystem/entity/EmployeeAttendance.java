package com.shivam.hrms.attendance.attendancesystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="employee_attendance")
public class EmployeeAttendance {


    @Id
    @Column(name="employee_id")
    private Long employeeId;

    @Column(name="attendance_date")
    private LocalDate attendanceDate;

    @Column(name="employee_name")
    private String employeeName;

    @Column(name="checkin_time")
    private LocalDateTime checkinTime;

    @Column(name="checkout_time")
    private LocalDateTime checkoutTime;

    @Column(name="office_location")
    private String officeLocation;

    @Column(name="shift_detail")
    private String shiftDetail;

    //work from home or weekend or overtime
    @Column(name="mode_of_work")
    private String  modeOfWork;

    @Column(name="note")
    private String note;

}
