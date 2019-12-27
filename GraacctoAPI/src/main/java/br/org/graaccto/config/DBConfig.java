package br.org.graaccto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConfig {

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;

	@Value("${db.url}")
	private String url;

	@Value("${db.driver}")
	private String driver;

	@Value("${db.dialect}")
	private String dialect;

	@Value("${db.connection.init.sql}")
	private String connectionInitSql;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwor) {
		this.password = passwor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the dialect
	 */
	public String getDialect() {
		return dialect;
	}

	/**
	 * @param dialect the dialect to set
	 */
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	/**
	 * @return the connectionInitSql
	 */
	public String getConnectionInitSql() {
		return connectionInitSql;
	}

	/**
	 * @param connectionInitSql the connectionInitSql to set
	 */
	public void setConnectionInitSql(String connectionInitSql) {
		this.connectionInitSql = connectionInitSql;
	}







}
