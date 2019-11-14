package io.whileaway.apit.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Respond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    private Long aid;
    private String statusCode;
    @Column(columnDefinition="text")
    private String responseExample;
    @Column(columnDefinition="text")
    private String exampleParams;
}
