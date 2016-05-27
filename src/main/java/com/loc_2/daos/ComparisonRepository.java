package com.loc_2.daos;

import com.loc_2.config.SpringMongoConfig;
import com.loc_2.entities.Comparison;
import com.loc_2.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by Rafal on 2016-05-27.
 */
@Repository
public class ComparisonRepository {

    ApplicationContext ctx =
            new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    public void save(Comparison comparison) {
        mongoOperation.save(comparison);
    }

    public void view(User user){
        Query query = new Query();
        query.addCriteria(Criteria
                .where("comparee.name").is(user.getSummoner().getName())
                .and("isViewed").is(false));
        Update update = new Update();
        update.set("isViewed", true);
        mongoOperation.updateMulti(query, update, Comparison.class);
    }

    public List<Comparison> findComparedToMe(User user) {
        return mongoOperation.find(Query.query(where("comparee.name").is(user.getSummoner().getName()).and("isViewed").is(false)), Comparison.class);
    }
}
