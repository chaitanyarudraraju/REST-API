package com.spring.Company;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
//Please specify "/employees" at the end of URL to access the REST API
@RequestMapping("/employees")
public class Controller {

    Map<String, Rest> employeeInformation = new HashMap<>();

    // To collect the details by collecting the data from client sent using POST method
    @PostMapping
    public String send(@RequestBody Rest employerDetails) {
        Rest addDetails = new Rest();
        addDetails.setUser_id(employerDetails.getUser_id());
        addDetails.setName(employerDetails.getName());
        addDetails.setMail(employerDetails.getMail());
        employeeInformation.put(employerDetails.getUser_id(), addDetails);
        System.out.println(employerDetails.toString());
        return "Employee details added";
    }

    // To show the details of all the employees to client while requesting through GET method
    @GetMapping
    public Collection<Rest> recieve() {
        return employeeInformation.values();
    }

    @PutMapping(path = "/{User_id}")
    public String alter(@PathVariable String User_id, @RequestBody Rest EmployeeDetails) {
        Rest AlterDetails = new Rest();
        if (employeeInformation.containsKey(User_id)) {
            employeeInformation.remove(User_id);

            employeeInformation.put(User_id, EmployeeDetails);
            return "Employee details have been edited";
        } else
            return "Employee ID was not found";
    }

    //Please specify the User_id at the end of the URL to delete the record of the employee using DELETE method
    @DeleteMapping(path = "/{User_id}")
    public String delete(@PathVariable String User_id) {
        if (employeeInformation.containsKey(User_id)) {
            employeeInformation.remove(User_id);
            return "Employee details deleted";
        } else
            return "Employee ID not found";
    }

    //Please specify the User_id at the end of the URL to update the record of the employee using PATCH method
    @PatchMapping(path = "/{User_id}")
    public String update(@PathVariable String User_id, @RequestBody Rest EmployeeDetails) {
        if (employeeInformation.containsKey(User_id)) {
            Rest ModifyDetails = new Rest();
            ModifyDetails.setUser_id(EmployeeDetails.getUser_id());
            ModifyDetails.setName(EmployeeDetails.getName());
            ModifyDetails.setMail(EmployeeDetails.getMail());
            employeeInformation.put(User_id, ModifyDetails);
            return "Edit is done";
        } else
            return "Employee ID not found";
    }

}


