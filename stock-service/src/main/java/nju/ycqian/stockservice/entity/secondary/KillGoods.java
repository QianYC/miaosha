package nju.ycqian.stockservice.entity.secondary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "kill_goods")
@Getter
@Setter
@NoArgsConstructor
public class KillGoods {
    @Id
    private int goodId;

    private float price;

    private Date startTime, endTime;

    public KillGoods(int goodId, float price, Date startTime, Date endTime) {
        this.goodId = goodId;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
