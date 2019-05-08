package nju.ycqian.stockservice.dao;

import nju.ycqian.stockservice.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsDao extends JpaRepository<Goods,Long> {
}
