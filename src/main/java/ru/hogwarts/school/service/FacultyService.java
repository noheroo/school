package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.ObjectAlreadyAddedException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();

    private long facultyCounter = 0;

    public Faculty createFaculty(Faculty faculty) {
        validateFaculty(faculty);

        faculty.setId(++facultyCounter);
        return faculties.put(faculty.getId(), faculty);
    }

    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty Faculty) {
        return faculties.put(Faculty.getId(), Faculty);
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultiesByColor(String neededColor) {
        return faculties.values().stream()
                .filter(e -> e.getColor().equalsIgnoreCase(neededColor))
                .collect(Collectors.toList());
    }

    private void validateFaculty(Faculty faculty) {
        if (faculties.values().stream()
                .filter(e -> e.getName().equalsIgnoreCase(faculty.getName()))
                .anyMatch(e -> e.getColor().equalsIgnoreCase(faculty.getColor()))) {
            throw new ObjectAlreadyAddedException("Faculty " + faculty.getName() + " with color " + faculty.getColor() + " already added");
        }
    }


}
