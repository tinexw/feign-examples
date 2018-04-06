package de.tine;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = {FeignClientsConfiguration.class,  HttpMessageConvertersAutoConfiguration.class})
public class FeignClient1BuilderTest {

    private static final Logger LOG = LoggerFactory.getLogger(FeignClient1BuilderTest.class);

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8090);

    @Autowired
    private Contract contract;

    @Test
    public void test() throws Exception {
        wireMockRule
            .stubFor(any(anyUrl()).willReturn(aResponse().withStatus(200).withBody("success")));
        final FeignClient1 feignClient1 = Feign.builder().client(new Client.Default(null, null))
            .contract(contract)
            .requestInterceptor(new RequestInterceptor() {
                @Override
                public void apply(RequestTemplate requestTemplate) {
                    LOG.info("TEST");
                }
            })
            .target(FeignClient1.class, "http://localhost:8090");
        feignClient1.getClient1();
    }


}
