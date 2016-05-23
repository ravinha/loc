package com.loc_2.daos;

import com.loc_2.config.SpringMongoConfig;
import com.loc_2.entities.Summoner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by Rafal on 2016-05-23.
 */
@Repository
public class SummonerRepository {

    ApplicationContext ctx =
            new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    public Summoner findByUsername(String summonerName) {
        return mongoOperation.findOne(Query.query(where("name").is(summonerName)), Summoner.class);
    }

    public Summoner save(Summoner summoner) {
        mongoOperation.save(summoner);
        return findByUsername(summoner.getName());
    }
}
