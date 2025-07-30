package com.example.billingProject.Requests;


import com.example.billingProject.CustomAnnotation.ValidationEmployeeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@Data
public class UserInfo {

    @NotBlank(message = "Name should not be empty")
    private String name;
    @NotBlank(message = "Mobile number should not be empty")
    private String mobileNo;
    @NotBlank(message = "Email id should not be empty")
    @Email(message = "Enter valid emailId")
    private String emailId;
    @NotBlank(message = "Employee type has to be entered ")
    @ValidationEmployeeType(message="ENTER VALID EMPLOYEE TYPE: It should be either customer or staff or admin")
    private String employeeType;
    @NotBlank(message ="Password has to be entered" )
    private String password;

}
