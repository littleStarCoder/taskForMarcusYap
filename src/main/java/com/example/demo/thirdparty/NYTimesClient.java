package com.example.demo.thirdparty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "nytimes", url = "https://api.nytimes.com/svc/books/v3/lists.json?api-key=${api.key}")
public interface NYTimesClient {

    @GetMapping
    Object getBestSellers(@RequestParam(name = "list") String list);
}
