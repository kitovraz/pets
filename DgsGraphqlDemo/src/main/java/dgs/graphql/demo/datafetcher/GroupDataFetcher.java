package dgs.graphql.demo.datafetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import dgs.graphql.demo.db.FakeDatabase;
import dgs.graphql.demo.schema.types.Group;

import java.util.List;

@DgsComponent
public class GroupDataFetcher {

    @DgsQuery
    public List<Group> getGroups() {
        return FakeDatabase.GROUPS;
    }
}
