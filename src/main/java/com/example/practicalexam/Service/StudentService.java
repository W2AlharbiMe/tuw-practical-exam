package com.example.practicalexam.Service;

import com.example.practicalexam.DTO.UpdateStudentDTO;
import com.example.practicalexam.Model.Student;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Service
public class StudentService {

    private final HashMap<Integer, Student> students = new HashMap<>();


        public Collection<Student> getStudents() {
            return students.values();
        }

    public boolean containsId(Integer id) {
        return students.containsKey(id);
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student updateStudent(Integer id, UpdateStudentDTO updateStudentDTO) {
        Student saved_student = getStudent(id);

        saved_student.setAge(updateStudentDTO.getAge());
        saved_student.setName(updateStudentDTO.getName());
        saved_student.setMajor(updateStudentDTO.getMajor());

        return saved_student;
    }

    public Student getStudentByName(String name) throws Exception {
        for (Student s : getStudents()) {
            if(s.getName().equalsIgnoreCase(name)) return s;
        }

        throw new Exception("student not found.");
    }

    public Student deleteStudent(Integer id) {
        Student saved_student = getStudent(id);

        students.remove(id);

        return saved_student;
    }

    private Student getStudent(Integer id) {
        return students.get(id);
    }

}
