package com.loc_2.daos;

import com.loc_2.config.SpringMongoConfig;
import com.loc_2.entities.User;
import com.loc_2.services.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


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

    public User save(User user) {
        mongoOperation.save(user);
        return findByUsername(user.getUsername());
    }

    public User findByUsername(String username) {
        return mongoOperation.findOne(Query.query(where("username").is(username)), User.class);
    }

    public void setApiKey(String username, String api_key) {
        User toSet = findByUsername(username);
        toSet.setApikey(api_key);
        mongoOperation.save(toSet);
    }
}
