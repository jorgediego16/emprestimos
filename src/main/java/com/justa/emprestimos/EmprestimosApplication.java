package com.justa.emprestimos;

import com.justa.emprestimos.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class EmprestimosApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmprestimosApplication.class, args);
    }

}
