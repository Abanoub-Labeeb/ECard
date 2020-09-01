package org.ecards.configuration.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"org.ecards.repository"})
public class RepositoryConfig {}