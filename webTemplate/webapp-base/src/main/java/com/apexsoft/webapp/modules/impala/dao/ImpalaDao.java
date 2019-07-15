package com.apexsoft.webapp.modules.impala.dao;


import com.apexsoft.webapp.modules.impala.model.ImpalaDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Dinglei
 * @Description:
 * @Date: Created in 上午11:27 2018/2/6
 * @MODIFIED BY:
 */
@Repository
public class ImpalaDao {

    @Autowired
    @Qualifier("impalaJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    //Druid,mybatis暂都不支持impala数据源，因此这里直接使用dbcp连接池，以及jdbcTemplate操作
    public List<ImpalaDemo> test() throws Exception{
        List<ImpalaDemo> list = jdbcTemplate.query("SELECT A.ZQDM, SUM(ZXSZ) AS ZQSZ" +
                " FROM CUST.T_ZQYE_HIS A" +
                " WHERE A.RQ = 20151124" +
                " AND A.ZQLB IN ('A0', 'B0', 'C0')" +
                " GROUP BY A.ZQDM",
                new Object[]{},
                new ImpalaDemoRowMapper());
        return list;
    }

    private class ImpalaDemoRowMapper implements RowMapper<ImpalaDemo> {
        @Override
        public ImpalaDemo mapRow(ResultSet rs, int rowNum) throws SQLException {
            ImpalaDemo obj = new ImpalaDemo();
            obj.setZqdm(rs.getString("zqdm"));
            obj.setZqsz(rs.getFloat("zqsz"));
            return obj;
        }
    }
}
