package nju.ycqian.orderservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue
    private long id;

    private int userId;
    private int goodId;
    private double price;
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());

    public Orders(int userId, int goodId, double price) {
        this.userId = userId;
        this.goodId = goodId;
        this.price = price;
    }
}
