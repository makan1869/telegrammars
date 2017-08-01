package ir.serenade.telegrammars.config;

import liquibase.integration.spring.SpringLiquibase;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by serenade on 8/1/17.
 */

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {


}