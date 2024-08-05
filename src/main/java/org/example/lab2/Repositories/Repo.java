package org.example.lab2.Repositories;

import org.example.lab2.Entities.Customer;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class Repo {

    List<Customer> list1 = new ArrayList<Customer>();

    public ArrayList<Customer> initialdata() {

        Customer cc;

        cc = new Customer();

        cc.setCname("Joe");
        cc.setCemail("xxx@yahoo.com");

        list1.add(cc);

        return (ArrayList<Customer>) list1;

    }

}
