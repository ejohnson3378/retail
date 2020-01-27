package com.company.retail

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {

    @GetMapping('/')
    String index() {
        return 'Hello from the retail API!'
    }
}
