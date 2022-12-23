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
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "classpath:teacher.properties")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "teacherEntityManger", transactionManagerRef = "teacherTxManager", basePackages = "com.sdc.repo.teacher")
public class TeacherDataSourceConfig {

	// data source
	@Bean
	@ConfigurationProperties(prefix = "teacher.datasource")
	public DataSource teacherDataSource() {
		return DataSourceBuilder.create().build();

	}

	// EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean teacherEntityManger(EntityManagerFactoryBuilder e) {

		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "true");

		return e.dataSource(teacherDataSource()).packages("com.sdc.entity.teacher").properties(properties).build();
	}

	// TxManager
	@Bean
	public PlatformTransactionManager teacherTxManager(@Qualifier("teacherEntityManger") EntityManagerFactory factory) {

		return new JpaTransactionManager(factory);

	}
}
