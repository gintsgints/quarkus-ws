package com.balcia.ws.fruit;

import com.balcia.ws.color.Color;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "known_fruits")
@Cacheable
public class Fruit extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "personSequence",
            sequenceName = "hibernate_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSequence")
    public Long id;

    @Column(length = 40, unique = true)
    public String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="color_id")
    public Color color;
}
