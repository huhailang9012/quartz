package cn.comtom.app.standard.component.quartz.config;

import cn.comtom.app.standard.component.quartz.mapper.common.JobMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
/**
 * 定时任务数据源配置
 * @author huhailang
 */
@Configuration
@MapperScan(value = "tk.mybatis.mapper.annotation",
	basePackages = "cn.comtom.app.standard.component.quartz.mapper",
	markerInterface = JobMapper.class,
	mapperHelperRef = QuartzDataSourceConfig.QUARTZ_MAPPER_HELPER,
	sqlSessionTemplateRef = QuartzDataSourceConfig.QUARTZ_SQL_SESSION_TEMPLATE
)
public class QuartzDataSourceConfig {

    public static final String QUARTZ_DATA_SOURCE = "quartzDataSource";

    public static final String QUARTZ_TRANSACTION_MANAGER = "quartzTransactionManager";

    public static final String QUART_SQL_SESSION_FACTORY = "quartSqlSessionFactory";

    public static final String QUARTZ_MAPPER_HELPER = "quartzMapperHelper";

    public static final String QUARTZ_SQL_SESSION_TEMPLATE = "quartzSqlSessionTemplate";

	@Bean(name = QUARTZ_DATA_SOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.quartz")
    public DataSource setDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = QUARTZ_TRANSACTION_MANAGER)
    public DataSourceTransactionManager setTransactionManager(@Qualifier(QUARTZ_DATA_SOURCE) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = QUART_SQL_SESSION_FACTORY)
    public SqlSessionFactory setSqlSessionFactory(@Qualifier(QUARTZ_DATA_SOURCE) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("cn.comtom.app.standard.component.quartz.model.dbo");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
        conf.setMapUnderscoreToCamelCase(true);
        conf.setLogImpl(Slf4jImpl.class);
        bean.setConfiguration(conf);
        return bean.getObject();
    }

    @Bean(name = QUARTZ_SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier(QUART_SQL_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(QUARTZ_MAPPER_HELPER)
    public MapperHelper quartzMapperHelper() {
        Config config = new Config();
        List<Class> mappers = new ArrayList<Class>();
        mappers.add(JobMapper.class);
        config.setMappers(mappers);
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(config);
        return mapperHelper;
    }

}