package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long studentCounter = 0;

    public Student createStudent(Student student) {
        if (findDoubleStudent(student)) {
            return null;
        }
        student.setId(++studentCounter);
        students.put(student.getId(), student);
        return student;
    }

    public Student findStudent(long id) {
        if (!students.containsKey(id)) {
            return null;
        }
        return students.get(id);
    }

    public Student editStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            return null;
        }
        return students.put(student.getId(), student);
    }

    public Student deleteStudent(long id) {
        if (!students.containsKey(id)) {
            return null;
        }
        return students.remove(id);
    }

    public Collection<Student> getStudentsByAge(int neededAge) {
        if (neededAge < 0) {
            return Collections.emptyList();
        }
        return students.values().stream()
                .filter(e -> e.getAge() == neededAge)
                .collect(Collectors.toList());
    }

    private boolean findDoubleStudent(Student student) {
        return students.values().stream()
                .filter(e -> e.getName().equalsIgnoreCase(student.getName()))
                .anyMatch(e -> e.getAge() == student.getAge());
    }
}
