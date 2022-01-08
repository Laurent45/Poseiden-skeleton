package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "bid_list_id")
    private Integer bidListId;

    @NotBlank(message = "Account is mandatory")
    @Size(max = 50)
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 50)
    private String type;

    @Digits(message = "Bid quantity is mandatory", integer = 3, fraction = 2)
    @Column(name = "bid_quantity")
    private Double bidQuantity;

    @Digits(message = "Ask quantity is mandatory", integer = 3, fraction = 2)
    @Column(name = "ask_quantity")
    private Double askQuantity;

    @Digits(message = "Bid is mandatory", integer = 3, fraction = 2)
    private Double bid;

    @Digits(message = "Ask is mandatory", integer = 3, fraction = 2)
    private Double ask;

    @NotBlank(message = "Benchmark is mandatory")
    @Size(max = 50)
    private String benchmark;

    @Past(message = "Date of bid list must be in the past")
    @Column(name = "bid_list_date")
    private Timestamp bidListDate;

    @NotBlank(message = "Commentary is mandatory")
    @Size(max = 100)
    private String commentary;

    @NotBlank(message = "Security is mandatory")
    @Size(max = 100)
    private String security;

    @NotBlank(message = "Status is mandatory")
    @Size(max = 100)
    private String status;

    @NotBlank(message = "Trader is mandatory")
    @Size(max = 100)
    private String trader;

    @NotBlank(message = "Book is mandatory")
    @Size(max = 100)
    private String book;

    @NotBlank(message = "Creation name is mandatory")
    @Size(max = 100)
    private String creationName;

    @Past(message = "Creation date must be in past")
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @NotBlank(message = "Revision name is mandatory")
    @Size(max = 100)
    @Column(name = "revision_name")
    private String revisionName;

    @Past(message = "Revision date must be in past")
    @Column(name = "revision_date")
    private Timestamp revisionDate;

    @NotBlank(message = "Deal date is mandatory")
    @Size(max = 100)
    private String dealName;

    @NotBlank(message = "Deal type is mandatory")
    @Size(max = 100)
    @Column(name = "deal_type")
    private String dealType;

    @NotBlank(message = "Source list id is mandatory")
    @Size(max = 100)
    @Column(name = "source_list_id")
    private String sourceListId;

    @NotBlank(message = "Side is mandatory")
    @Size(max = 100)
    private String side;
}
