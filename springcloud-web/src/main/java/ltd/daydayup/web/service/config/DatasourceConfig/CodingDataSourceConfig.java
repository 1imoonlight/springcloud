package ltd.daydayup.web.service.config.DatasourceConfig;

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

@Configuration
@MapperScan(basePackages = "ltd.daydayup.codingweb.repository.dao.mapper.coding", sqlSessionTemplateRef = "codingSqlSessionTemplate")
public class CodingDataSourceConfig {

    @Bean(name = "codingDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.coding")
    @Primary
    public DataSource codingDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "codingSqlSessionFactory")
    @Primary
    public SqlSessionFactory codingSqlSessionFactory(@Qualifier("codingDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/coding/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "codingTransactionManager")
    @Primary
    public DataSourceTransactionManager codingTransactionManager(@Qualifier("codingDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "codingSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate codingSqlSessionTemplate(@Qualifier("codingSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
