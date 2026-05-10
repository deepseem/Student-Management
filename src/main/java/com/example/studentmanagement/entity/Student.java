package com.example.studentmanagement.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Student Entity")
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Student ID", example = "1")
    private Long id;

    @Schema(description = "Student Name", example = "Deepak")
    private String name;

    @Schema(description = "Student Email", example = "deepak@example.com")
    private String email;

    @Schema(description = "Student Course", example = "Computer Science")
    private String course;

    @Schema(description = "Student Age", example = "20")
    private Integer age;
}
