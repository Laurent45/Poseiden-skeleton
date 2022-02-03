package com.nnk.springboot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "CurvePoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "curve id is mandatory")
    @PositiveOrZero(message = "curve id must be positive or zero")
    @Column(name = "curve_id")
    private Integer curveId;

    @NotNull(message = "term is mandatory")
    @Digits(message = "term format xxx,xx", integer = 3, fraction = 2)
    private Double term;

    @NotNull(message = "value is mandatory")
    @Digits(message = "value format xxx,xx", integer = 3, fraction = 2)
    private Double value;

    @Column(name = "as_of_date")
    private Timestamp asOfDate;
    @Column(name = "creation_date")
    private Timestamp creationDate;

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
