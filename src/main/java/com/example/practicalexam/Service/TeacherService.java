package com.example.practicalexam.Service;

import com.example.practicalexam.DTO.UpdateStudentDTO;
import com.example.practicalexam.DTO.UpdateTeacherDTO;
import com.example.practicalexam.Model.Student;
import com.example.practicalexam.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class TeacherService {
    private final HashMap<Integer, Teacher> teachers = new HashMap<>();


    public Collection<Teacher> getTeachers() {
        return teachers.values();
    }

    public boolean containsId(Integer id) {
        return teachers.containsKey(id);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getId(), teacher);
    }

    public Teacher getTeacher(Integer id) {
        return teachers.get(id);
    }



    public Teacher updateStudent(Integer id, UpdateTeacherDTO updateTeacherDTO) {
        Teacher saved_teacher = getTeacher(id);

        saved_teacher.setName(updateTeacherDTO.getName());
        saved_teacher.setSalary(updateTeacherDTO.getSalary());

        return saved_teacher;
    }

    public Teacher deleteStudent(Integer id) {
        Teacher saved_teacher = getTeacher(id);

        teachers.remove(id);

        return saved_teacher;
    }



}
