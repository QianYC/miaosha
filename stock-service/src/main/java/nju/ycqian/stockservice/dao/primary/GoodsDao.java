package nju.ycqian.stockservice.dao.primary;

import nju.ycqian.stockservice.entity.primary.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao extends JpaRepository<Goods,Integer> {
}
