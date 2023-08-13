package com.example.practicalexam.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotNull(message = "the id field is required.")
    @Positive(message = "the id must be positive.")
    private Integer id;

    @NotEmpty(message = "the name field is required.")
    private String name;

    @NotNull(message = "the salary field is required.")
    @Positive(message = "the salary must be positive.")
    private Double salary;
}
