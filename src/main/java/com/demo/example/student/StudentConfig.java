package com.demo.example.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
          Student oudom =     new Student(
                 "Tea Oudom",
                 "oudom@gmail.com",
                       LocalDate.of(1990,01,01)
               );
            Student pheara =     new Student(
                    "Hong Pheara",
                    "pheara@gmail.com",
                    LocalDate.of(1995,01,01)
            );

            Student sopha =     new Student(
                    "Ning Sopha",
                    "sopha@gmail.com",
                    LocalDate.of(2000,01,01)
            );

            repository.saveAll(List.of(oudom,pheara,sopha));

        };
    }
}
