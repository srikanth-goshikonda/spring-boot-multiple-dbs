package com.sdc.configuration;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "classpath:admin.properties")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "studentEntityManger", transactionManagerRef = "studentTxManager", basePackages = "com.sdc.repo.student")
public class StudentDataSourceConfig {

	// data source
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "admin.datasource")
	public DataSource studentDataSource() {
		return DataSourceBuilder.create().build();

	}

	// EntityManagerFactory
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean studentEntityManger(EntityManagerFactoryBuilder e) {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		return e.dataSource(studentDataSource()).packages("com.sdc.entity.student").properties(properties).build();
	}

	// TxManager
	@Bean
	@Primary
	public PlatformTransactionManager studentTxManager(@Qualifier("studentEntityManger") EntityManagerFactory factory) {

		return new JpaTransactionManager(factory);

	}

}
