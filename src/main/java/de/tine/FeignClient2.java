package de.tine;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "client2", url = "http://localhost:8089", configuration = FeignClient2Configuration.class)
public interface FeignClient2 {

    @RequestMapping(method = RequestMethod.GET, value = "/client2")
    String getClient2();

}
