package com.example.practicalexam.Controller;

import com.example.practicalexam.ApiResponse.ApiResponse;
import com.example.practicalexam.ApiResponse.ApiResponseWithData;
import com.example.practicalexam.DTO.UpdateStudentDTO;
import com.example.practicalexam.DTO.UpdateTeacherDTO;
import com.example.practicalexam.Model.Student;
import com.example.practicalexam.Model.Teacher;
import com.example.practicalexam.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeachersController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity<Collection<Teacher>> getTeachers() {
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
        if(!teacherService.containsId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse("teacher not found.")));
        }

        Teacher teacher = teacherService.getTeacher(id);

        return ResponseEntity.ok(teacher);
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }

        if(teacherService.containsId(teacher.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse("the id must be unique.")));
        }

        teacherService.addTeacher(teacher);

        return ResponseEntity.status(HttpStatus.CREATED).body((new ApiResponseWithData<>("the teacher have been created.", teacher)));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody @Valid UpdateTeacherDTO updateTeacherDTO, Errors errors) {
        if(!teacherService.containsId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse("teacher not found.")));
        }

        if(errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldErrors());
        }

        Teacher teacher = teacherService.updateStudent(id, updateTeacherDTO);

        return ResponseEntity.ok((new ApiResponseWithData<>("the teacher have been updated.", teacher)));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
        if(!teacherService.containsId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ApiResponse("teacher not found.")));
        }

        Teacher teacher = teacherService.deleteStudent(id);

        return ResponseEntity.ok((new ApiResponseWithData<>("the teacher have been deleted.", teacher)));
    }

}
