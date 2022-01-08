package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "trade")
public class Trade {

// TODO: Map columns in data table TRADE with corresponding java fields
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "account is mandatory")
    private String account;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "type is mandatory")
    private String type;

    @Column(name = "buy_quantity")
    @PositiveOrZero(message = "zero or positive number")
    private Double buyQuantity;

    @Column(name = "sell_quantity")
    @PositiveOrZero(message = "zero or positive number")
    private Double sellQuantity;

    @Column(name = "buy_price")
    @PositiveOrZero(message = "zero or positive number")
    private Double buyPrice;

    @Column(name = "sell_price")
    @PositiveOrZero(message = "zero or positive number")
    private Double sellPrice;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "benchmark is mandatory")
    private String benchmark;

    @Column(name = "trade_date")
    private Timestamp tradeDate;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "security is mandatory")
    private String security;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "status is mandatory")
    private String status;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "trader is mandatory")
    private String trader;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "book is mandatory")
    private String book;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "creation name is mandatory")
    @Column(name = "creation_name")
    private String creationName;

    @Column(name = "creattion_date")
    private Timestamp creationDate;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "revision name is mandatory")
    @Column(name = "revision_name")
    private String revisionName;

    @Column(name = "revision_date")
    private Timestamp revisionDate;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "deal name is mandatory")
    @Column(name = "deal_name")
    private String dealName;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "deal type is mandatory")
    @Column(name = "deal_type")
    private String dealType;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "source list id is mandatory")
    @Column(name = "source_list_id")
    private String sourceListId;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "side is mandatory")
    private String side;
}
