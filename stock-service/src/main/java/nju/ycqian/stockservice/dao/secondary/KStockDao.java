package nju.ycqian.stockservice.dao.secondary;

import nju.ycqian.stockservice.entity.secondary.KStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KStockDao extends JpaRepository<KStock, Integer> {
}
