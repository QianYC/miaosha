package nju.ycqian.stockservice.dao.secondary;

import nju.ycqian.stockservice.entity.secondary.KillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KillGoodsDao extends JpaRepository<KillGoods,Integer> {
}
