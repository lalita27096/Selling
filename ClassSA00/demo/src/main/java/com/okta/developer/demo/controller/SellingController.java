package com.okta.developer.demo.controller;
import com.okta.developer.demo.Entity.Customer;
import com.okta.developer.demo.Entity.Product;
import com.okta.developer.demo.Entity.Selling;
import com.okta.developer.demo.Entity.Staff;
import com.okta.developer.demo.repository.CustomerRepository;
import com.okta.developer.demo.repository.SellingRepository;
import com.okta.developer.demo.repository.StaffRepository;
import com.okta.developer.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class SellingController {
    @Autowired
    private SellingRepository sellingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ProductRepository productRepository;

    public SellingController(SellingRepository sellingRepository,CustomerRepository customerRepository,StaffRepository staffRepository, ProductRepository productRepository) {
        this.sellingRepository = sellingRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.productRepository = productRepository;
    }
    @GetMapping("/selling")
    public Collection<Selling> selling() {
        return sellingRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @PostMapping("/sell/{productID}/{productName}/{productPrice}/{customerID}/{staffIDs}/{sellingDate}")
        public Selling newSSel(@PathVariable String productID,@PathVariable String productName,@PathVariable Integer productPrice,@PathVariable String customerID,@PathVariable String staffIDs,@PathVariable String sellingDate){
            Selling newSelling = new Selling();
            Product product = productRepository.findByProductID(productID);
            Customer customer = customerRepository.findByCustomerID(customerID);
            Staff staff = staffRepository.findByStaffIDs(staffIDs);

        String sDate1 = sellingDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate date = LocalDate.parse(sDate1,formatter);

        newSelling.setProduct(product);
        newSelling.setCustomer(customer);
        newSelling.setStaff(staff);
        newSelling.setSellingDate(date);
        System.out.println(date);
        return sellingRepository.save(newSelling);
        }


}
