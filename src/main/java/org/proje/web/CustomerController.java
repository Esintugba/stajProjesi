package org.proje.web;


import org.proje.model.Customer;
import org.proje.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customers")
    public ModelAndView getCustomers(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("customers",customerService.findCustomers());
        mav.setViewName("customers");
        return mav;
    }

    @GetMapping("/customers/new")
    public String newCustomer(){
        return "newCustomer";
    }

    @ModelAttribute
    public Customer initModel(){
        return new Customer();
    }

    @PostMapping("/customers/new")
    public String handleFormSubmit(@ModelAttribute @Valid Customer customer , BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "newCustomer";
        }
        customerService.createCustomer(customer);
        redirectAttributes.addFlashAttribute("message", "Customer created with id :" + customer.getId());
        return "redirect:/customers";
    }

    @GetMapping(value = "/customers/update/{id}")
    public String loadUpdateCustomer(@PathVariable Long id, ModelMap modelMap){
        Customer customer=customerService.findCustomer(id);
        modelMap.put("customer",customer);
        return "editCustomer";
    }

    @PostMapping(value = "/customers/update/{id}")
    public String handleFormSubmitUpdate(@ModelAttribute @Valid Customer customer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            return "editCustomer";
        }
        customerService.updateCustomer(customer);
        redirectAttributes.addFlashAttribute("message", "Customer created with id :" + customer.getId());
        return "redirect:/customers";
    }

    @GetMapping(value ="/customers/delete/{id}")
    public String loadDeleteCustomers(@PathVariable Long id, ModelMap modelMap){
       Customer customer=customerService.findCustomer(id);
        modelMap.put("customer",customer);
        return "deleteCustomer";
    }

    @PostMapping(value ="/customers/delete/{id}")
    public String handleFormSubmitDelete(@PathVariable Long id ,RedirectAttributes redirectAttributes){
        customerService.deleteCustomer(id);
        redirectAttributes.addFlashAttribute("message", "Customer deleted with id:" + id);
        return "redirect:/customers";
    }
}
