package com.dnsc.logic.repository;

import com.dnsc.logic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRep extends JpaRepository<User,Long> {

    /*
     * 查询全部
     * */
    @Query("select t from User t")
    List<User> find();

    /*
     * 根据用户名查询
     * */
    @Query("select t from User t where t.loginName = :name")
    User findByUser(@Param("name") String name);

    /*
     * 根据用户名修改密码
     * */
    @Modifying
    @Transactional
    @Query("update User u set u.passWord = :password where u.loginName=:name")
    public int updateByUser(@Param("name") String name,@Param("password") String password);

    /*
     * 根据用户名id修改信息
     * */
    @Modifying
    @Transactional
    @Query("update User u set u.userName = :name, u.gid = :gid, u.cid = :cid where u.id=:id")
    public int updateByUserInfo(@Param("id") Integer id,@Param("name") String name,@Param("gid") Integer gid,@Param("cid") String cid);

}
