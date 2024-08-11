package com.mitocode.springreactordemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


//https://www.baeldung.com/spring-boot-console-app
@SpringBootApplication
public class SpringReactorDemoApplication implements CommandLineRunner {
    private static List<String> dishes= new ArrayList<>();
    public void createMono(){
        //just generate Mono
        Mono<String> m1 = Mono.just("Hello Coders");
        m1.subscribe(LOG::info);

    }
    public void createFlux(){
        Flux<String> fx1 = Flux.fromIterable(dishes);
        //fx1.subscribe(LOG::info);
        fx1.collectList().subscribe(list-> LOG.info(list.toString()));
    }
    private static Logger LOG = LoggerFactory.getLogger(SpringReactorDemoApplication.class);
    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringReactorDemoApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING: command line runner");
        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}",i,args[i]);
        }
        dishes.add("Ceviche");
        dishes.add("Bandeja Paisa");
        dishes.add("Tacos");
        createFlux();
    }
}
