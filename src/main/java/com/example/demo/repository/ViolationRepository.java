package com.example.demo.repository;


import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;

@Slf4j
@Repository
public class ViolationRepository extends AbstractMongoRepository {


    public void test(List<String> districtIds, Instant startTime, Instant endTime, int page, int pageSize) {

        List<Bson> bsons = new ArrayList<>();
        bsons.add(match(and(gte("detectedAt", startTime), lte("detectedAt", endTime))));
        bsons.add(match(in("districtID", districtIds)));
        bsons.add(group("$districtID", sum("total", 1)));

        //bsons.add(count("total"));
        //Document count = getCollection("violation").aggregate(bsons).iterator().next();
        //System.out.println(count.get("total"));

        bsons.add(skip(page * pageSize));
        bsons.add(limit(pageSize));
        List<Document> documents = getCollection("violation").aggregate(bsons, Document.class)
                .into(new ArrayList<>());
        for (Document document : documents) {
            System.out.println(document.toString());
        }
    }

    public void violationByCamGroup(String districtId, Instant startTime, Instant endTime, int page, int pageSize) {

        List<Bson> aggregates = new ArrayList<>();
        aggregates.add(match(and(gte("detectedAt", startTime), lte("detectedAt", endTime))));

        aggregates.add(match(eq("districtID", districtId)));
        aggregates.add(group(new Document(Map.of("districtID", "$districtID", "cameraGroup", "$cameraGroup._id")),
                sum("total", 1)));

        aggregates.add(skip(page * pageSize));
        aggregates.add(limit(pageSize));
        List<Document> documents = getCollection("violation").aggregate(aggregates, Document.class)
                .into(new ArrayList<>());
        for (Document document : documents) {
            System.out.println(document.toString());
        }

    }

}
