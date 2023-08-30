package org.proje.web;


import org.proje.exception.CompanyNotFoundException;
import org.proje.exception.InternalServerException;
import org.proje.model.Company;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.proje.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CompanyRestController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company/{id}", produces = "application/json")
    public ResponseEntity<?> getCompanyAsHateoasResource(@PathVariable("id") Long id) {
        try {
            Company company = companyService.findCompany(id);
            Link self = ControllerLinkBuilder.linkTo(CompanyRestController.class).slash("/company/" + id).withSelfRel();
            Link create = ControllerLinkBuilder.linkTo(CompanyRestController.class).slash("/company").withRel("create");
            Link update = ControllerLinkBuilder.linkTo(CompanyRestController.class).slash("/company/" + id).withRel("update");
            Link delete = ControllerLinkBuilder.linkTo(CompanyRestController.class).slash("/company/" + id).withRel("delete");
            Resource<Company> resource = new Resource<Company>(company, self, create, update, delete);

            return ResponseEntity.ok(company);
        } catch (CompanyNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/company")
    public ResponseEntity<URI> createCompany(@RequestBody Company company) {
        try {
            companyService.createCompany(company);
            Long id = company.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable("id") Long id, @RequestBody Company companyRequest) {
        try {
            Company company = companyService.findCompany(id);
            company.setCompanyCode(companyRequest.getCompanyCode());
            company.setCompanyName(companyRequest.getCompanyName());
            company.setAddress(companyRequest.getAddress());
            company.seteMail(companyRequest.geteMail());
            companyService.updateCompany(company);
            return ResponseEntity.ok().build();
        } catch (CompanyNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/company/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@PathVariable("id") Long id) {
        try {
            companyService.findCompany(id);
            companyService.deleteCompany(id);
        } catch (CompanyNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }


    @Cacheable("allCompanies")
    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getCompanies(){
        System.out.println(">>>inside getCompanies...");
        List<Company> companies=companyService.findCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable("id") Long id){
        try{
            Company company=companyService.findCompany(id);
            return ResponseEntity.ok(company);
        }
        catch (CompanyNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/company")
    public ResponseEntity<List<Company>> getCompanies(@RequestParam("ln") String companyName){
        List<Company> companies=companyService.findCompanies(companyName);
        return ResponseEntity.ok(companies);
    }

}
