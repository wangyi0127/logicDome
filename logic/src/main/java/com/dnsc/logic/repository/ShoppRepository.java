package com.dnsc.logic.repository;

import com.dnsc.logic.entity.PayOrders;
import com.dnsc.logic.entity.Shopp;
import com.dnsc.logic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Repository
public interface ShoppRepository extends JpaRepository<Shopp,Long> {

    /*
     * 查询全部
     * */
    @Query("select s from Shopp s")
    List<Shopp> find();

    @Query("select s from PayOrders s where s.orderid = :orderid")
    PayOrders findByPayOrder(@Param("orderid") String orderid);

    @Query(value = "insert into orders (spid,userid,`count`,sumprice,status,price,oid) value(?,?,?,?,?,?,?)", nativeQuery = true)
    @Transactional
    @Modifying
    public int insertOrder(@Param("spid")Integer spid, @Param("userid") Integer userid, @Param("count") Integer count,@Param("sumprice") double sumprice,@Param("status") Integer status,@Param("price") double price,@Param("oid") String oid);

    @Query(value = "insert into payorders (orderid,status) value(?,?)", nativeQuery = true)
    @Transactional
    @Modifying
    public int insertPayOrder(@Param("orderid")String orderid, @Param("status") Integer status);

    @Modifying
    @Transactional
    @Query("update PayOrders u set u.status = :status where u.orderid=:orderid")
    public int updateByPayOrder(@Param("status") Integer status,@Param("orderid") String orderid);

    @Modifying
    @Transactional
    @Query("update SpOrders u set u.status = :status where u.oid=:oid")
    public int updateByOrder(@Param("status") Integer status,@Param("oid") String oid);
}
