package me.xkuyax.bouldertracker;

import me.xkuyax.utils.config.Config;
import me.xkuyax.utils.mysql.MysqlConnection;
import me.xkuyax.utils.mysql.MysqlConnectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;

@SpringBootApplication
public class BoulderTrackerApplication {

    public static void main(String[] args) {
        SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
        SpringApplication.run(BoulderTrackerApplication.class, args);
    }

    @Bean
    public static Config config() {
        return new Config("config.yml");
    }

    @Bean
    public static MysqlConnection mysqlConnection(Config config) {
        return MysqlConnectionUtils.hikari(config, "Mysql");
    }
}
