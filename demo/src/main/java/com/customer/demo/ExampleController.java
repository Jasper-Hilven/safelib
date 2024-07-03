package com.customer.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @PostMapping(path="/user/{id}")
    public void MakeNameGary(@PathVariable String id){
        var notSoSafeSql = "UPDATE Customers SET ContactName = 'Gary' where CustomerID = '"+id+"'";
        System.out.println("This is not so safe Sql");
        System.out.println(notSoSafeSql);
    }
}
