package com.nnk.springboot.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "bidlist")
public class BidList {

    public BidList(Integer bidListId, String account, String type, Double bidQuantity) {
        this.bidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    @NotEmpty(message = "Account is mandatory")
    @Size(max = 50)
    private String account;

    @NotEmpty(message = "Type is mandatory")
    @Size(max = 50)
    private String type;

    @NotNull(message = "Bid quantity is mandatory")
    @Digits(message = "Bid quantity should have 2 digits before comma and 2 " +
            "digits after",
            integer = 3, fraction = 2)
    @Column(name = "bid_quantity")
    private Double bidQuantity;

    @Column(name = "ask_quantity")
    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Timestamp bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "revision_name")
    private String revisionName;
    @Column(name = "revision_date")
    private Timestamp revisionDate;
    private String dealName;
    @Column(name = "deal_type")
    private String dealType;
    @Column(name = "source_list_id")
    private String sourceListId;
    private String side;
}
