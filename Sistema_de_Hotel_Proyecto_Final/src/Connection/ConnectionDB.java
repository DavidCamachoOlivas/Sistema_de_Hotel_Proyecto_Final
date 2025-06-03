package Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionDB {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://b0ufffjehz0mbockqctk-mysql.services.clever-cloud.com:3306/b0ufffjehz0mbockqctk");
        config.setUsername("uywiohjkpxink6lw");
        config.setPassword("22LfDvbA07QUq1XOKk4d");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}