package com.jdbc.batch.pagiable.step;

import com.jdbc.batch.pagiable.models.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class JdbcPaginacaoStepConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step jdbcPaginacaoStep(JdbcPagingItemReader<Cliente> jdbcPaginacaoReader, ItemWriter<Cliente> jdbcPaginacaoWriter ){
        return new StepBuilder("jdbcPaginacaoStep",jobRepository)
                .<Cliente,Cliente>chunk(1,transactionManager)
                .reader(jdbcPaginacaoReader)
                .writer(jdbcPaginacaoWriter)
                .build();
    }
}
