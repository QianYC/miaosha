package nju.ycqian.stockservice.entity.primary;

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
    private int id;

    private String name;

    private float price;

    public Goods(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
