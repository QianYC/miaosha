package nju.ycqian.stockservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Goods {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private double price;
    private int amount;

    public Goods(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
