package com.prototype.protoapp.stock.repository;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class LoginUserRepository {

    private static final String SQL_FIND_BY_EMAIL =
            "SELECT u.email, u.name AS user_name, u.password, r.name AS role_name"
            + " FROM login_user AS u INNER JOIN user_role AS ur ON u.id = ur.user_id"
            + " INNER JOIN roles AS r ON ur.role_id = r.id"
            + " WHERE u.name=:name";

    private static final ResultSetExtractor<LoginUser> LOGIN_USER_EXTRACTOR = (rs) -> {
        String email = null;
        String userName = null;
        String password = null;
        List<String> roleList = new ArrayList<>();
        while (rs.next()) {
            if (userName == null) {
                email = rs.getString("email");
                userName = rs.getString("user_name");
                password = rs.getString("password");
            }
            roleList.add(rs.getString("role_name"));
        }
        if (userName == null) {
            return null;
        }
        return new LoginUser(email, userName, password, roleList);
    };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<LoginUser> findByEmail(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource("name", name);
        LoginUser loginUser = jdbcTemplate.query(SQL_FIND_BY_EMAIL, params, LOGIN_USER_EXTRACTOR);
        return Optional.ofNullable(loginUser);
    }


}
