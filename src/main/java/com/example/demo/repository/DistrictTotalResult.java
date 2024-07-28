package com.example.demo.repository;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Setter
@Getter
public class DistrictTotalResult {

    @BsonProperty
    private String id;
    @BsonProperty
    private long total;
}
