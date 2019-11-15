package com.balcia.ws.color;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "color")
public class Color extends PanacheEntityBase {
    @Id
    @SequenceGenerator(
            name = "personSequence",
            sequenceName = "hibernate_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSequence")
    public Long id;

    @Column(length = 40, unique = true)
    public String name;
}
