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

@Entity
@Getter
@EqualsAndHashCode
@Setter
@NoArgsConstructor
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50, message = "max size 50 characters")
    @NotBlank(message = "moodys rating is mandatory")
    @Column(name = "moodys_rating")
    private String moodysRating;

    @Size(max = 50, message = "max size 50 characters")
    @NotBlank(message = "sand p rating is mandatory")
    @Column(name = "sand_p_rating")
    private String sandPRating;

    @Size(max = 50, message = "max size 50 characters")
    @NotBlank(message = "fitch rating is mandatory")
    @Column(name = "fitch_rating")
    private String fitchRating;

    @NotNull(message = "order number is mandatory")
    @PositiveOrZero
    @Column(name = "order_number")
    private Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating
            , Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}
