package com.kimhong.thymeleaf.repository.admin.mybatis.provider;

import com.kimhong.thymeleaf.utils.Paging;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String selectUserSql() {
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("status = true");
            ORDER_BY("id desc");
            OFFSET("#{offset}");
            LIMIT("#{limit}");
        }}.toString();
    }

    public String selectUserByUserSql(String userId) {
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("user_id = #{userId}");
            AND();
            WHERE("status = true");
        }}.toString();
    }

    public String insertUser() {
        return new SQL() {{
            INSERT_INTO("users");
            VALUES("user_id", "#{userId}");
            VALUES("first_name", "#{firstName}");
            VALUES("last_name", "#{lastName}");
            VALUES("email", "#{email}");
            VALUES("password", "#{password}");
        }}.toString();
    }

    public String updateUserSQL() {
        return new SQL() {{
            UPDATE("users");
            SET("first_name = #{firstName}");
            SET("last_name = #{lastName}");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    public String deleteUserSQL(String userId) {
        return new SQL() {{
            UPDATE("users");
            SET("status=false");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    public String searchUserByKeywordSql() {
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("first_name ilike '%' || #{keyword} || '%'");
            OR();
            WHERE("last_name ilike '%' || #{keyword} || '%'");
            OR();
            WHERE("email ilike '%' || #{keyword} || '%'");
            AND();
            WHERE("status=true");
            ORDER_BY("id DESC");
            OFFSET("#{paging.offset}");
            LIMIT("#{paging.limit}");
        }}.toString();
    }

    public String countSearchResultSql() {
        return new SQL() {{
            SELECT("COUNT(*)");
            FROM("users");
            WHERE("first_name ilike '%' || #{keyword} || '%'");
            OR();
            WHERE("last_name ilike '%' || #{keyword} || '%'");
            OR();
            WHERE("email ilike '%' || #{keyword} || '%'");
            AND();
            WHERE("status=true");
//            OFFSET("#{offset}");
//            LIMIT("#{paging.limit}");
        }}.toString();
    }
}
