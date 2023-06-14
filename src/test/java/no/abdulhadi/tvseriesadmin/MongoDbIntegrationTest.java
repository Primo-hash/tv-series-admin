package no.abdulhadi.tvseriesadmin;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoDbIntegrationTest {

    @DisplayName("Check that key/value document is saved, when save request is issued to MongoDB")
    @Test
    public void test(@Autowired MongoTemplate mongoTemplate) {
        DBObject documentToSave = BasicDBObjectBuilder.start()
                .add("key", "testValue")
                .get();

        mongoTemplate.save(documentToSave, "testCollection");
        assertThat(mongoTemplate.findAll(DBObject.class, "testCollection").get(0).get("key"),
                is("testValue"));
    }
}