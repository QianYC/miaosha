package nju.ycqian.orderservice.dao;

import nju.ycqian.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByUserId(String userId);

    @Nullable
    @Query(value = "SELECT * FROM orders WHERE user_id = ?1 AND good_id = ?2 AND TIMESTAMPDIFF(second, now(), create_time) < 5", nativeQuery = true)
    Orders findRecentOne(String userId, int goodId);
}
