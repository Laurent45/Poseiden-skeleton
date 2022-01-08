package com.nnk.springboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rulename")
public class RuleName {

// TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "name is mandatory")
    @Size(max = 100, message = "max 100 characters")
    private String name;

    @NotBlank(message = "description is mandatory")
    @Size(max = 100, message = "max 100 characters")
    private String description;

    @NotBlank(message = "json is mandatory")
    @Size(max = 100, message = "max 100 characters")
    private String json;

    @NotBlank(message = "template is mandatory")
    @Size(max = 100, message = "max 100 characters")
    private String template;

    @NotBlank(message = "sql_str is mandatory")
    @Size(max = 100, message = "max 100 characters")
    @Column(name = "sql_str")
    private String sqlStr;

    @NotBlank(message = "sql_part is mandatory")
    @Size(max = 100, message = "max 100 characters")
    @Column(name = "sql_part")
    private String sqlPart;

    public RuleName(String name, String description, String json
            , String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }
}
