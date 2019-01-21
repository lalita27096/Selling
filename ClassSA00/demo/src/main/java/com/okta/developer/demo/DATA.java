package com.okta.developer.demo;
import com.okta.developer.demo.Entity.*;
import com.okta.developer.demo.repository.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.print.Book;
import java.util.Date;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
public class DATA  {
    Date date = new Date();
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DATA.class, args);
    }
    public DATA(ProductRepository productRepository,
                CustomerRepository customerRepository,
                SellingRepository sellingRepository,
                StaffRepository staffRepository){
    }
    @Bean
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
    @Bean
    ApplicationRunner init(CustomerRepository customerRepository,
                           SellingRepository sellingRepository,
                           ProductRepository productRepository,
                           StaffRepository staffRepository) {
        return args ->{
            Stream.of("Sunvo", "Ploy", "Wahn").forEach(cusName -> {

                Customer customerName = new Customer();
                customerName.setCustomerName(cusName);
                customerRepository.save(customerName);

                if (cusName == "Sunvo") {
                    Customer cusid = customerRepository.findByCusId(1L);
                    customerName.setCustomerID("C"+cusid.getCusId());
                    customerRepository.save(customerName);

                }
                else if(cusName == "Ploy"){
                    Customer cusid = customerRepository.findByCusId(2L);
                    customerName.setCustomerID("C"+cusid.getCusId());
                    customerRepository.save(customerName);
                }
                else if(cusName == "Wahn"){
                    Customer cusid = customerRepository.findByCusId(3L);
                    customerName.setCustomerID("C"+cusid.getCusId());
                    customerRepository.save(customerName);
                }
            });

            Customer c1 = customerRepository.findByCusId(1L);

            Stream.of("Opal", "SunvoDz", "Ao").forEach(stName -> {
                Staff staffdb = new Staff();
                staffdb.setStaffName(stName);
                staffRepository.save(staffdb);

                if (stName == "Opal") {
                    Staff staffid = staffRepository.findByStaffId(1L);
                    staffdb.setStaffIDs("St"+staffid.getStaffId());
                    staffdb.setPosition("HairStylist");
                    staffRepository.save(staffdb);
                }
                else if(stName == "SunvoDz"){
                    Staff staffid = staffRepository.findByStaffId(2L);
                    staffdb.setStaffIDs("St"+staffid.getStaffId());
                    staffdb.setPosition("Renting");
                    staffRepository.save(staffdb);
                }
                else if(stName == "Ao"){
                    Staff staffid = staffRepository.findByStaffId(3L);
                    staffdb.setStaffIDs("St"+staffid.getStaffId());
                    staffdb.setPosition("selling");
                    staffRepository.save(staffdb);

                }
            });
            Staff st1 = staffRepository.findByStaffId(1L);


            Stream.of("Dress","Married","Shoes").forEach(proName ->{
                Product productName = new Product();
                productName.setProductName(proName);
                productRepository.save(productName);

                if(proName == "Dress") {
                    Product prodid = productRepository.findByProdId(1L);
                    productName.setProductID("P" + prodid.getProdId());
                    productName.setProductPrice(1000);
                    productName.setProductQuantity(18);
                    productName.setStatusProduct("renting");
                    productRepository.save(productName);
                }
                else if(proName == "Married") {
                    Product prodid = productRepository.findByProdId(2L);
                    productName.setProductID("P" + prodid.getProdId());
                    productName.setProductPrice(1000);
                    productName.setProductQuantity(19);
                    productName.setStatusProduct("renting");
                    productRepository.save(productName);
                }
                else if(proName == "Shoes") {
                    Product prodid = productRepository.findByProdId(3L);
                    productName.setProductID("P" + prodid.getProdId());
                    productName.setProductPrice(1000);
                    productName.setProductQuantity(20);
                    productName.setStatusProduct("selling");
                    productRepository.save(productName);
                }

            });
            Product pt1 = productRepository.findByProdId(1L);


                Selling sellingdb = new Selling();
                sellingRepository.save(sellingdb);
                String sDate1 = "20:04:1998";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
                LocalDate date = LocalDate.parse(sDate1, formatter);


                    sellingdb.setSellingDate(date);
                    sellingdb.setCustomer(c1);
                    sellingdb.setStaff(st1);
                    sellingdb.setProduct(pt1);
                    sellingRepository.save(sellingdb);


            System.out.println("\n Spring-Boot Complete");
        };

    }
}
