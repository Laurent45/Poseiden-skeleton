package com.nnk.springboot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "trade")
public class Trade {

    public Trade(Integer id, String account, String type, Double buyQuantity) {
        this.id = id;
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "account is mandatory")
    private String account;

    @Size(max = 50, message = "max 50 characters")
    @NotBlank(message = "type is mandatory")
    private String type;

    @Column(name = "buy_quantity")
    @NotNull(message = "buy quantity is mandatory")
    @PositiveOrZero(message = "zero or positive number")
    private Double buyQuantity;

    @Column(name = "sell_quantity")
    private Double sellQuantity;
    @Column(name = "buy_price")
    private Double buyPrice;
    @Column(name = "sell_price")
    private Double sellPrice;
    private String benchmark;
    @Column(name = "trade_date")
    private Timestamp tradeDate;
    private String security;
    private String status;
    private String trader;
    private String book;
    @Column(name = "creation_name")
    private String creationName;
    @Column(name = "creattion_date")
    private Timestamp creationDate;
    @Column(name = "revision_name")
    private String revisionName;
    @Column(name = "revision_date")
    private Timestamp revisionDate;
    @Column(name = "deal_name")
    private String dealName;
    @Column(name = "deal_type")
    private String dealType;
    @Column(name = "source_list_id")
    private String sourceListId;
    private String side;
}
