package br.org.graaccto.config;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories({"br.org.graaccto"})
@PropertySource({"classpath:db.properties"})
public class DatabaseConfiguration {

	@Autowired
	private DBConfig dbConfig;

	@Bean(name="dataSource", destroyMethod="close")
	@Primary
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(dbConfig.getDriver());
		dataSource.setUrl(dbConfig.getUrl());
		dataSource.setUsername(dbConfig.getUsername());
		dataSource.setPassword(dbConfig.getPassword());
		dataSource.setConnectionInitSqls(
				Arrays.asList(new String[] {dbConfig.getConnectionInitSql() } ) );

		return dataSource;

	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource());
		entityManager.setPackagesToScan("br.org.graaccto");
		entityManager.setJpaVendorAdapter(jpaVendorAdapter());
		entityManager.setPersistenceUnitName("eProcessPersistenceUnit");
		entityManager.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
		entityManager.setJpaProperties(getHibernateProperties());

		return entityManager;

	}


	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform(dbConfig.getDialect());
		return jpaVendorAdapter;
	}

	private Properties getHibernateProperties() {
		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
		//hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "none");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "false");
		hibernateProperties.setProperty("hibernate.generate_statistics", "true");

		//inicio das configuracoes de cache (ehcache)
		hibernateProperties.setProperty("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.EhCacheProvider");
		hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");
		hibernateProperties.setProperty("hibernate.cache.use_structured_entries", "true");
		hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");
		hibernateProperties.setProperty("hibernate.cache.provider_configuration_file_resource_path", "classpath:ehcache.xml");
		hibernateProperties.setProperty("hibernate.cache.region_prefix", "");

		//inicio das configuracoes de processamento em lote
		hibernateProperties.setProperty("hibernate.default_batch_fetch_size", "8");
		hibernateProperties.setProperty("hibernate.jdbc.batch_size", "30");

		return hibernateProperties;
	}


	@Bean(name="transactionManager")
    @Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}



}
