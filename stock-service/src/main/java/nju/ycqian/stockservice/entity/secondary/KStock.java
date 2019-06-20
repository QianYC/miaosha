package nju.ycqian.stockservice.entity.secondary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "kill_goods_stock")
@Setter
@Getter
@NoArgsConstructor
public class KStock {
    @Id
    private int goodId;

    private int amount;

    public KStock(int goodId, int amount) {
        this.goodId = goodId;
        this.amount = amount;
    }
}
