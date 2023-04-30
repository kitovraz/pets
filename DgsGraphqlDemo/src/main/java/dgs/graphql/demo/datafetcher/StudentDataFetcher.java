package dgs.graphql.demo.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import dgs.graphql.demo.db.FakeDatabase;
import dgs.graphql.demo.schema.types.Group;
import dgs.graphql.demo.schema.types.Student;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DgsComponent
public class StudentDataFetcher {

    @DgsQuery
    public List<Student> getStudents(Long groupId) {
        System.out.println("getStudents");
        return FakeDatabase.STUDENTS.stream()
                .filter(student -> Objects.nonNull(student.getGroup()))
                .filter(student -> student.getGroup().getId() == groupId)
                .collect(Collectors.toList());
    }
}
