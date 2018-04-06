package de.tine;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class WiremockServer {

    private WireMockServer wireMockServer;

    @PostConstruct
    public void start() {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8089));
        wireMockServer.start();
        wireMockServer.stubFor(any(anyUrl()).willReturn(aResponse().withStatus(200).withBody("success")));
    }

    @PreDestroy
    public void stop() {
        wireMockServer.stop();
    }
}
