package com.jkkim.todo.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private Boolean completed = false;

    @Column(name = "item_name")
    private String name;




}
