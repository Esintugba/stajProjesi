package org.proje.web;

import org.proje.model.Company;
import org.proje.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/companies")
    public ModelAndView getCompanies(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("companies",companyService.findCompanies());
        mav.setViewName("companies");
        return mav;
    }

    @GetMapping("/companies/new")
    public String newCompany(){
        return "newCompany";
    }

    @ModelAttribute
    public Company initModel(){
        return new Company();
    }

    @PostMapping("/companies/new")
    public String handleFormSubmitCreate(@ModelAttribute @Valid Company company, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "newCompany";
        }
        companyService.createCompany(company);
        redirectAttributes.addFlashAttribute("message", "Company created with id :" + company.getId());
        return "redirect:/companies";
    }


    @GetMapping(value = "/companies/update/{id}")
    public String loadUpdateCompany(@PathVariable Long id, ModelMap modelMap){
        Company company=companyService.findCompany(id);
        modelMap.put("company",company);
        return "editCompany";
    }

    @PostMapping(value = "/companies/update/{id}")
    public String handleFormSubmitUpdate(@ModelAttribute  @Valid Company company, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "editCompany";
        }
        companyService.updateCompany(company);
        redirectAttributes.addFlashAttribute("message","Company updated with id :" + company.getId());
        return "redirect:/companies";
    }
    @GetMapping(value ="/companies/delete/{id}")
    public String loadDeleteProduct(@PathVariable Long id, ModelMap modelMap){
        Company company=companyService.findCompany(id);
        modelMap.put("company",company);
        return "deleteCompany";
    }

    @PostMapping(value ="/companies/delete/{id}")
    public String handleFormSubmitDelete(@PathVariable Long id, RedirectAttributes redirectAttributes){
       companyService.deleteCompany(id);
        redirectAttributes.addFlashAttribute("message", "Company deleted with id:" + id);
        return "redirect:/companies";
    }
}
