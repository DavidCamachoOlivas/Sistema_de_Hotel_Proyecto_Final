package Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionDB {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        String user = "avnadmin";
        String pass = "AVNS_09hEmMqg7x5NVvl-bOH";
        
        config.setJdbcUrl("jdbc:mysql://avnadmin:AVNS_09hEmMqg7x5NVvl-bOH@mysql-16ce286a-database-hotel.g.aivencloud.com:24121/defaultdb?ssl-mode=REQUIRED");
        config.setUsername(user);
        config.setPassword(pass);

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