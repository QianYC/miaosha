package nju.ycqian.orderservice.dao;

import nju.ycqian.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface OrdersRespository extends JpaRepository<Orders, Integer> {

    List<Orders> findAllByUserId(int userId);

    @Nullable
    @Query(value = "SELECT * FROM orders WHERE user_id = ?1 AND good_id = ?2 AND TIMESTAMPDIFF(second, now(), create_time) < 5", nativeQuery = true)
    Orders findRecentOne(int userId, int goodId);
}
