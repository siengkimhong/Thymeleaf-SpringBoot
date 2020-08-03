package com.kimhong.thymeleaf.repository.admin.mybatis;

import com.kimhong.thymeleaf.model.Role;
import com.kimhong.thymeleaf.model.User;
import com.kimhong.thymeleaf.repository.admin.mybatis.provider.UserProvider;
import com.kimhong.thymeleaf.repository.admin.mybatis.provider.UserRoleProvider;
import com.kimhong.thymeleaf.utils.Paging;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    //    @Insert("INSERT INTO users(user_id,first_name,last_name,email,password) values(" +
//            "#{userId},#{firstName},#{lastName},#{email},#{password})")
    @InsertProvider(type = UserProvider.class, method = "insertUser")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean saveUser(User user);

    //    @Select("select * from users where status=true")
    @SelectProvider(type = UserProvider.class, method = "selectUserSql")
    @Results(id = "userMap"
            , value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "roles", column = "id",
                many = @Many(select = "selectRoleByUserId"))
    })
    List<User> findAll(Paging paging);

    //@Select("SELECT * FROM users WHERE user_id=#{userId} AND status=true")
    @SelectProvider(type = UserProvider.class, method = "selectUserByUserSql")
    @ResultMap("userMap")
    User findOne(String userId);


    //    @Update("UPDATE users SET " +
//            "first_name = #{firstName}," +
//            "last_name = #{lastName} " +
//            "WHERE user_id = #{userId}")
    @UpdateProvider(type = UserProvider.class, method = "updateUserSQL")
    boolean updateByUserId(User newUser);

    //    @Update("UPDATE users SET " +
//            "status = false " +
//            "WHERE user_id = #{userId}")
    @UpdateProvider(type = UserProvider.class, method = "deleteUserSQL")
    boolean deleteByUserId(String userId);

    @Select("SELECT COUNT(*) FROM users WHERE status=true")
    int countUser();

    @SelectProvider(type = UserProvider.class, method = "searchUserByKeywordSql")
    @ResultMap("userMap")
    List<User> searchUserByKeyword(@Param("keyword") String keyword, Paging paging);

    @SelectProvider(type = UserProvider.class, method = "countSearchResultSql")
    int countSearchResult(String keyword);

    @SelectProvider(type = UserProvider.class, method = "selectUserByEmailSql")
    @ResultMap("userMap")
    User selectUserByEmail(String email);

    @SelectProvider(type = UserProvider.class, method = "selectRoleByUserIdSql")
    List<Role> selectRoleByUserId(int id);

    @InsertProvider(type = UserRoleProvider.class, method = "createUserRoleSql")
    boolean createUserRole(User user);

    @UpdateProvider(type = UserRoleProvider.class, method = "updateUserRoleSql")
    boolean updateUserRole(User user);
}
