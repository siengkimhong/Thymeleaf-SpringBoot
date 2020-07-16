package com.kimhong.thymeleaf.repository.admin.mybatis.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String selectUserSql(){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("status = true");
            ORDER_BY("id");

        }}.toString();
    }

    public String selectUserByUserSql(String userId){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("user_id = #{userId}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    public String insertUser(){
        return new SQL(){{
            INSERT_INTO("users");
            VALUES("user_id", "#{userId}");
            VALUES("first_name", "#{firstName}");
            VALUES("last_name", "#{lastName}");
            VALUES("email", "#{email}");
            VALUES("password", "#{password}");
        }}.toString();
    }

    public String updateUserSQL(){
        return new SQL(){{
            UPDATE("users");
            SET("first_name = #{firstName}");
            SET("last_name = #{lastName}");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    public String deleteUserSQL(String userId){
        return new SQL(){{
            UPDATE("users");
            SET("status=false");
            WHERE("user_id = #{userId}");
        }}.toString();
    }
}
