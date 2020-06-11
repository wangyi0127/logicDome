package com.dnsc.logic.repository;

import com.dnsc.logic.entity.Shopp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

}
