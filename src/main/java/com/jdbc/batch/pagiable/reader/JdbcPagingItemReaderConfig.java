package com.jdbc.batch.pagiable.reader;

import com.jdbc.batch.pagiable.models.Cliente;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
@Configuration
public class JdbcPagingItemReaderConfig {

    @Bean
    public JdbcPagingItemReader<Cliente> jdbcPaginacaoReader(@Qualifier("appDataSource")DataSource dataSource, PagingQueryProvider queryProvider){
        return new JdbcPagingItemReaderBuilder<Cliente>()
                .name("jdbcPaginacaoReader")
                .dataSource(dataSource)
                .queryProvider(queryProvider)
                .pageSize(2)
                .rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource")DataSource dataSource){
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("SELECT * ");
        queryProvider.setFromClause("from Cliente");
        queryProvider.setSortKey("email");
        return queryProvider;

    }

}
