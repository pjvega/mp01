package com.distribuida.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;

@ApplicationScoped
public class DbConfig {

/*    //opcion 2
    @Inject
    @ConfigProperty(name="db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;

    @PostConstruct*/
   /* public void init(){
        System.out.println("************post construct");

        //opcion 1
        Config config = ConfigProvider.getConfig();

        String user = config.getValue("db.user", String.class);
        String passwd = config.getValue("db.password", String.class);
        String url = config.getValue("db.url", String.class);

        System.out.println("op1: user" + user);
        System.out.println("op1: pwd" + passwd);
        System.out.println("op1: url" + url);

        //opcion 2
        System.out.println("op2: user"+ dbUser);
        System.out.println("op2: pwd"+ dbPassword);
        System.out.println("op2: url"+ dbUrl);
    }*/
    public Jdbi init(){
        Config config = ConfigProvider.getConfig();
        String url = config.getValue("db.url", String.class);
        String username = config.getValue("db.user", String.class);
        String password = config.getValue("db.password", String.class);
        String driver = config.getValue("db.driver", String.class);

        HikariConfig hc = new HikariConfig();
        hc.setMaximumPoolSize(6);
        hc.setJdbcUrl(url);
        hc.setUsername(username);
        hc.setPassword(password);
        hc.setDriverClassName(driver);

        return Jdbi.create(new HikariDataSource(hc));
    }
    public void test(){

    }

}
