package com.kimhong.thymeleaf.repository.admin.jdbc.imp;

import com.kimhong.thymeleaf.model.Category;
import com.kimhong.thymeleaf.repository.admin.jdbc.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryRepositoryImp implements CategoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Category category) {
        int i = 0;
        String insertSQL = "INSERT INTO categories(NAME) VALUES (?)";
        System.out.println(category.getName());
        i = jdbcTemplate.update(insertSQL, category.getName());
        return i;
    }

    @Override
    public List<Category> finAll() {
        final String SELECT_SQL = "SELECT * FROM categories where status=true ORDER BY id DESC";
        return jdbcTemplate.query(SELECT_SQL, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Category(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("status"));
            }
        });
    }

    @Override
    public int edit(Category category) {
        String updateSql = "UPDATE categories SET name=? where id = ?";
        return jdbcTemplate.update(updateSql, category.getName(), category.getId());
    }

    @Override
    public Category find(int id) {
        String SELECT_BY_ID_SQL = "SELECT * FROM categories where id=? and status=true";
        return jdbcTemplate.query(SELECT_BY_ID_SQL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        }, new ResultSetExtractor<Category>() {
            @Override
            public Category extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    return new Category(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getBoolean("status"));
                } else {
                    return null;
                }
            }
        });
    }

    @Override
    public int delete(int id) {
        final String SQL_DELETE = "UPDATE categories set Status= false where id=?";
        return jdbcTemplate.update(SQL_DELETE, id);
    }
}
