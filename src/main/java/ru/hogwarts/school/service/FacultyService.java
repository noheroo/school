package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long facultyCounter = 0;

    public Faculty createFaculty(Faculty faculty) {
        if (findDoubleFaculty(faculty)) {
            return null;
        }
        faculty.setId(++facultyCounter);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        if (!faculties.containsKey(id)) {
            return null;
        }
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            return null;
        }
        return faculties.put(faculty.getId(), faculty);
    }

    public Faculty deleteFaculty(long id) {
        if (!faculties.containsKey(id)) {
            return null;
        }
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultiesByColor(String neededColor) {
        if (neededColor.isBlank()) {
            return Collections.emptyList();
        }
        return faculties.values().stream()
                .filter(e -> e.getColor().equalsIgnoreCase(neededColor))
                .collect(Collectors.toList());
    }

    private boolean findDoubleFaculty(Faculty faculty) {
        return faculties.values().stream()
                .filter(e -> e.getName().equalsIgnoreCase(faculty.getName()))
                .anyMatch(e -> e.getColor().equalsIgnoreCase(faculty.getColor()));
    }
}
