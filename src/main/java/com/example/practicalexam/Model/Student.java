package com.example.practicalexam.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotNull(message = "the id field is required.")
    @Positive(message = "the id field must be positive.")
    private Integer id;

    @NotEmpty(message = "the name field is required.")
    private String name;

    @NotNull(message = "the age field is required.")
    @Positive(message = "the age field must be positive.")
    private Integer age;

    @NotEmpty(message = "the major field is required.")
    private String major;
}
