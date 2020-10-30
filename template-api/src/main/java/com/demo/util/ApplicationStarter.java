package com.demo.util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.io.File;

/**
 * @author dp
 * @date 2020/7/9 9:49 上午
 */
@SpringBootApplication
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ApplicationStarter.class);
        File pidFile = new File("app.pid");
        pidFile.setWritable(true, true);
        pidFile.setExecutable(false);
        pidFile.setReadable(true);
        application.addListeners(new ApplicationPidFileWriter(pidFile));
        application.run(args);
    }
}
