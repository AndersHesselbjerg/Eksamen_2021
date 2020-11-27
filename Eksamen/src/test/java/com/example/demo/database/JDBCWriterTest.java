package com.example.demo.database;

import com.example.demo.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JDBCWriterTest {

    @Test
    void createUser() {
        JDBCWriter jdbcWriter = new JDBCWriter();
        User user = new User("a", "abc");
        jdbcWriter.createUser(user);
    }
}