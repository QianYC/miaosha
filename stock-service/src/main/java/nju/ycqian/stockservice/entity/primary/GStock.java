package nju.ycqian.stockservice.entity.primary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "goods_stock")
@Getter
@Setter
@NoArgsConstructor
public class GStock {
    @Id
    private int goodId;

    private int amount;

    public GStock(int goodId, int amount) {
        this.goodId = goodId;
        this.amount = amount;
    }
}
