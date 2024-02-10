package com.solution.grapeApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DynamicService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> executeDynamicSql(String dynamicSql) {
        return jdbcTemplate.queryForList(dynamicSql);
    }
}
