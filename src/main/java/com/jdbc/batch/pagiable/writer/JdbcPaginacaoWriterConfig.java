package com.jdbc.batch.pagiable.writer;

import com.jdbc.batch.pagiable.models.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcPaginacaoWriterConfig {
    @Bean
    public ItemWriter<Cliente> jdbcPaginacaoWriter() {
        return clientes -> clientes.forEach(System.out::println);
    }
}
