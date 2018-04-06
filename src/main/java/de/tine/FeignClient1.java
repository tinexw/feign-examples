package de.tine;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "client1", url = "http://localhost:8089", configuration = FeignClient1Configuration.class)
public interface FeignClient1 {

    @GetMapping(value = "/client1")
    String getClient1();

}
