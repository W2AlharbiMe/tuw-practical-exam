package com.example.practicalexam.Controller;

import com.example.practicalexam.ApiResponse.ApiResponse;
import com.example.practicalexam.ApiResponse.ApiResponseWithData;
import com.example.practicalexam.DTO.UpdateStudentDTO;
import com.example.practicalexam.Model.Student;
import com.example.practicalexam.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<Collection<Student>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    // use this:
    // http://localhost:8080/api/v1/students/get/name?name=STUDENT_NAME
    @GetMapping("/get/name")
    public ResponseEntity<?> getStudentByName(@RequestParam("name") String name) {
        try {
            Student student = studentService.getStudentByName(name);

            return ResponseEntity.ok(student);
        } catch (Exception e1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse(e1.getMessage())));
        }
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody @Valid Student student, Errors errors) {

        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }

        if(studentService.containsId(student.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse("the id must be unique.")));
        }

        studentService.addStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponseWithData<>("the student have been created.", student)));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody @Valid UpdateStudentDTO updateStudentDTO, Errors errors) {
        if(!studentService.containsId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse("student not found.")));
        }

        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }

        Student student = studentService.updateStudent(id, updateStudentDTO);

        return ResponseEntity.ok((new ApiResponseWithData<>("the student have been updated.", student)));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        if(!studentService.containsId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse("student not found.")));
        }

        Student student = studentService.deleteStudent(id);

        return ResponseEntity.ok((new ApiResponseWithData<>("the student have been deleted.", student)));
    }

}
