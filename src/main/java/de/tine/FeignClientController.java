package de.tine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignClientController {

    @Autowired
    private FeignClient1 client1;

    @Autowired
    private FeignClient2 client2;

    @GetMapping("client1")
    public String invokeClient1() {
        return client1.getClient1();
    }

    @GetMapping("client2")
    public String invokeClient2() {
        return client2.getClient2();
    }

}
