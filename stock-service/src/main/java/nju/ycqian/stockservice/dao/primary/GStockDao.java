package nju.ycqian.stockservice.dao.primary;

import nju.ycqian.stockservice.entity.primary.GStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GStockDao extends JpaRepository<GStock,Integer> {
}
