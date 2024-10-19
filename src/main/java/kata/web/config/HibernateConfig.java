package kata.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import kata.web.model.User;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
//@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("kata")
public class HibernateConfig {

    //@Value("${application.url}")
    private static String DB_URL = "jdbc:postgresql://localhost:5433/Kata_2.3.1";

    //@Value("${application.username}")
    private static String DB_USER = "postgres";

    //@Value("${application.password}")
    private static String DB_PASS = "delta1501d";


    @Bean
    public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASS);

        return dataSource;

    }
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());

        Properties props = new Properties();
        props.put("hibernate.show_sql", ("true"));
        props.put("hibernate.dialect", ("org.hibernate.dialect.PostgreSQLDialect"));
        props.put("hibernate.hbm2ddl.auto", ("update"));
        //props.put("hibernate.format_sql", (""));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
