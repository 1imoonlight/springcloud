package ltd.daydayup.web.config.DataSourceConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author lipengcheng
 * @date 2022-03-04
 */
@Configuration
@MapperScan(basePackages = "ltd.daydayup.web.repository.mapper.springcloud", sqlSessionTemplateRef = "springcloudSqlSessionTemplate")
public class SpringCloudDataSourceConfig {

    @Bean(name = "springcloudDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.springcloud")
    @Primary
    public DataSource springcloudDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "springcloudSqlSessionFactory")
    @Primary
    public SqlSessionFactory springcloudSqlSessionFactory(@Qualifier("springcloudDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/springcloud/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "springcloudTransactionManager")
    @Primary
    public DataSourceTransactionManager springcloudTransactionManager(@Qualifier("springcloudDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "springcloudSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate springcloudSqlSessionTemplate(@Qualifier("springcloudSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
