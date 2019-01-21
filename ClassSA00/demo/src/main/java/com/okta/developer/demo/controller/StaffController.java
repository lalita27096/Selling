package com.okta.developer.demo.controller;
import com.okta.developer.demo.Entity.Product;
import com.okta.developer.demo.Entity.Selling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.developer.demo.Entity.Staff;
import com.okta.developer.demo.repository.StaffRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StaffController {
    private StaffRepository staffRepository;
    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    @RequestMapping("/staff")
    public Collection<Staff> staff() {
        return staffRepository.findAll().stream()
                .filter(this::isStaff)
                .collect(Collectors.toList());
    }
    private boolean isStaff(Staff staff){
        return staff.getPosition().equals("selling");
    }

}
