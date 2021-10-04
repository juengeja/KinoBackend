package com.example.kinoticketreservierungssystem.config;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.cosmos.GatewayConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.core.ResponseDiagnostics;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;


@Configuration
@EnableCosmosRepositories
public class ConfigDB extends AbstractCosmosConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigDB.class);

    @Value("https://dhbw-kino-sql.documents.azure.com:443/")
    private String uri;

    @Value("jmR9w6HJRnAbmAvV5wGDmwXZ86ARpnJcEh14uxBOxlQuTQPWJ6du8YkixeR96nXUjILbUOTymtHmJ0bw2Czaaw==")
    private String key;

    @Value("shiLkgAZJ6B7EEEMbZNkB2fxvHZM0YTSSZDWSz5fehes5XhndWoKsFqoOZiatsz5lHs3skXzjMBkwWcmMsOK9g==")
    private String secondaryKey;

    @Value("dhbw-kino-sql")
    private String dbName;

    @Value("true")
    private boolean queryMetricsEnabled;

    private AzureKeyCredential azureKeyCredential;

    @Bean
    public CosmosClientBuilder getCosmosClientBuilder() {
        this.azureKeyCredential = new AzureKeyCredential(key);
        DirectConnectionConfig directConnectionConfig = new DirectConnectionConfig();
        GatewayConnectionConfig gatewayConnectionConfig = new GatewayConnectionConfig();
        return new CosmosClientBuilder()
                .endpoint(uri)
                .credential(azureKeyCredential)
                .directMode(directConnectionConfig, gatewayConnectionConfig);
    }

    @Override
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
                .enableQueryMetrics(queryMetricsEnabled)
                .responseDiagnosticsProcessor(new ResponseDiagnosticsProcessorImplementation())
                .build();
    }

    public void switchToSecondaryKey() {
        this.azureKeyCredential.update(secondaryKey);
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    private static class ResponseDiagnosticsProcessorImplementation implements ResponseDiagnosticsProcessor {

        @Override
        public void processResponseDiagnostics(@Nullable ResponseDiagnostics responseDiagnostics) {
            LOGGER.error("Response Diagnostics {}", responseDiagnostics);
        }
    }












}
