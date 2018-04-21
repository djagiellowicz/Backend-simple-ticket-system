package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Just in case if Components would not be found

@Configuration
@ComponentScan
public class BaseMVCConfiguration extends WebMvcAutoConfiguration {
}
