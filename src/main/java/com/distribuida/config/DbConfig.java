package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.apache.commons.dbcp2.BasicDataSource;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@ApplicationScoped
public class DbConfig {
    Jdbi jdbi = null;

    //Opcion 2 ENV
    @Inject
    @ConfigProperty(name = "db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name = "db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name = "db.url")
    String dbUrl;

    @PostConstruct
    public void init(){
        System.out.println("*******************post construct");
//
//        //Opcion 1 API
//        Config config = ConfigProvider.getConfig();
//
//        String user = config.getValue("db.user", String.class);
//        String passwd = config.getValue("db.password", String.class);
//        String url = config.getValue("db.url", String.class);
//
//        System.out.println("op1: user: " + user);
//        System.out.println("op1: pwd: " + passwd);
//        System.out.println("op1: url: " + url);
//
        //Opcion 2 ENV
        System.out.println("op2: user: " + dbUser);
        System.out.println("op2: pwd: " + dbPassword);
        System.out.println("op2: url: " + dbUrl);
    }

    public void test(){

    }



    @ApplicationScoped
    private static BasicDataSource ds=null;
    public DataSource getDataSource(){
        ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername(dbUser);
        ds.setPassword(dbPassword);
        ds.setUrl(dbUrl);
        //definimos el tamaño del pool
        ds.setInitialSize(50);
        ds.setMaxIdle(10);
        ds.setMaxTotal(20);
        ds.setMaxWaitMillis(5000);

        return ds;
    }

    @Produces
    public Jdbi pool() {
        DataSource con = getDataSource();
        return Jdbi.create(con).installPlugin(new SqlObjectPlugin());
    }

}
