package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull(message = "curve id is mandatory")
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
