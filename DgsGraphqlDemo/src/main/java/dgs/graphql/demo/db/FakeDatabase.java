package dgs.graphql.demo.db;

import dgs.graphql.demo.schema.types.Group;
import dgs.graphql.demo.schema.types.Student;
import org.springframework.stereotype.Component;

import java.util.List;

public class FakeDatabase {

    public static final List<Student> STUDENTS = List.of(
            Student.newBuilder().id("1").name("Max").build(),
            Student.newBuilder().id("2").name("Nick").build()
    );

    public static final List<Group> GROUPS = List.of(
            Group.newBuilder().id("1").number(456).build()
    );

    static {
        STUDENTS.get(0).setGroup(GROUPS.get(0));
        STUDENTS.get(1).setGroup(GROUPS.get(0));

        GROUPS.get(0).setStudents(List.of(
                STUDENTS.get(0),
                STUDENTS.get(1)));
    }
}
