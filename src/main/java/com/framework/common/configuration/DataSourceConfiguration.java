package com.framework.common.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
}
