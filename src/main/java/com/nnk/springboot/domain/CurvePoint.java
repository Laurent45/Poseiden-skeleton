package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {

// TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "curve id is mandatory")
    @Column(name = "curve_id")
    private Integer curveId;

    @NotBlank(message = "as of date is mandatory")
    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @NotBlank(message = "term is mandatory")
    @Digits(message = "term format xx,xx", integer = 3, fraction = 2)
    private Double term;

    @NotBlank(message = "value is mandatory")
    @Digits(message = "value format", integer = 3, fraction = 2)
    private Double value;

    @NotBlank(message = "date of creation is mandatory")
    @Past(message = "Date of creation must be in the past")
    @Column(name = "creation_date")
    private Timestamp creationDate;


    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
