package com.example.billingProject.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.sql.Timestamp;

@Configuration
@EnableJpaAuditing
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mobileNo;
    private String emailId;
    @CreatedDate
    @NotNull
    private Timestamp createdAt;
    @LastModifiedDate
    @NotNull
    private Timestamp updatedAt;
    private String employeeType;
    @JsonIgnore
    private String password;



}
