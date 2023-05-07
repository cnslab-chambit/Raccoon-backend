package kwu.raccoondomain.unit.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static kwu.raccoondomain.unit.configs.DataSourceSelector.READ;
import static kwu.raccoondomain.unit.configs.DataSourceSelector.WRITE;

@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean
    public DataSource testDataSource(
            @Qualifier("testRoutingDataSource") DataSource testRoutingDataSource) {
        return new LazyConnectionDataSourceProxy(testRoutingDataSource);
    }

    @Bean
    public DataSource testRoutingDataSource(
            @Qualifier("readDataSource") DataSource readDataSource,
            @Qualifier("writeDataSource") DataSource writeDataSource,
            DataSourceSelector dataSourceSelector
    ) {
        ReplicationRoutingDataSource testRoutingDataSource
                = new ReplicationRoutingDataSource(dataSourceSelector);

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(WRITE, writeDataSource);
        dataSourceMap.put(READ, readDataSource);

        testRoutingDataSource.setTargetDataSources(dataSourceMap);
        testRoutingDataSource.setDefaultTargetDataSource(writeDataSource);

        return testRoutingDataSource;
    }


}
