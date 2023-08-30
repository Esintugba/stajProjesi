package org.proje.web;

import org.proje.exception.CompanyNotFoundException;
import org.proje.exception.CustomerNotFoundException;
import org.proje.exception.InternalServerException;
import org.proje.model.Customer;
import org.proje.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CustomerRestController {


    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customer/{id}",produces = "application/json")
    public ResponseEntity<?> getCustomerAsHateoasResource(@PathVariable("id") Long id){
        try {
            Customer customer= customerService.findCustomer(id);
            Link self= ControllerLinkBuilder.linkTo(CustomerRestController.class).slash("/customer/"+id).withSelfRel();
            Link create=ControllerLinkBuilder.linkTo(CustomerRestController.class).slash("/customer").withRel("create");
            Link update=ControllerLinkBuilder.linkTo(CustomerRestController.class).slash("/customer/"+id).withRel("update");
            Link delete=ControllerLinkBuilder.linkTo(CustomerRestController.class).slash("/customer/"+id).withRel("delete");
            Resource<Customer> resource=new Resource<Customer>(customer,self,create,update,delete);

            return ResponseEntity.ok(customer);
        }
        catch (CustomerNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customer")
    public ResponseEntity<URI> createCustomer(@RequestBody Customer customer){
        try {
            customerService.createCustomer(customer);
            Long id=customer.getId();
            URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (ConstraintViolationException ex){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customerRequest){
        try {
            Customer customer=customerService.findCustomer(id);
            customer.setTc(customerRequest.getTc());
            customer.setCustomerNumber(customerRequest.getCustomerNumber());
            customer.setFirstName(customerRequest.getFirstName());
            customer.setLastName(customerRequest.getLastName());
            customer.setGender(customerRequest.getGender());
            customer.seteMail(customerRequest.geteMail());
            customerService.updateCustomer(customer);
            return ResponseEntity.ok().build();
        }catch (CompanyNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") Long id){
        try {
            customerService.deleteCustomer(id);
            customerService.deleteCustomer(id);
        }catch (CustomerNotFoundException ex){
            throw ex;
        }catch (Exception ex){
            throw new InternalServerException(ex);
        }
    }

    @Cacheable("allCustomers")
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        System.out.println(">>>inside getCustomers...");
        List<Customer> customers=customerService.findCustomers();
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
        try{
            Customer customer=customerService.findCustomer(id);
            return ResponseEntity.ok(customer);
        }
        catch (CustomerNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam("ln") String lastName){
        List<Customer> customers=customerService.findCustomers(lastName);
        return ResponseEntity.ok(customers);
    }

}
