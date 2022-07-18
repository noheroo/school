package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.ObjectAlreadyAddedException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();

    private long studentCounter = 0;

    public Student createStudent(Student student) {
        validateStudent(student);

        student.setId(++studentCounter);
        return students.put(student.getId(), student);
    }

    public Student findStudent(long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        return students.put(student.getId(), student);
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> getStudentsByAge(int neededAge) {
        return students.values().stream()
                .filter(e -> e.getAge() == neededAge)
                .collect(Collectors.toList());
    }

    private void validateStudent(Student student) {
        if (students.values().stream()
                .filter(e -> e.getName().equalsIgnoreCase(student.getName()))
                .anyMatch(e -> e.getAge() == student.getAge())) {
            throw new ObjectAlreadyAddedException("Student " + student.getName() + " with age " + student.getAge() + " already added");
        }
    }
}
