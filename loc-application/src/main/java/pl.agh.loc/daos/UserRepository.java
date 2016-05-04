package pl.agh.loc.daos;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.agh.loc.User;
import pl.agh.loc.config.SpringMongoConfig;


import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by Rafal on 2016-03-20.
 */

@Repository
public class UserRepository {

    ApplicationContext ctx =
            new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    public UserRepository() {
    }

    public User findById(String id) {
        return mongoOperation.findById(id, User.class);
    }

    public void save(User user) {
        mongoOperation.save(user);
    }

    public User findByUsername(String username) {
        return mongoOperation.findOne(Query.query(where("username").is(username)), User.class);
    }
}
