package de.tine;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "client1", url = "http://localhost:8091", primary = false)
public interface FeignClient1IT extends FeignClient1 {

}
