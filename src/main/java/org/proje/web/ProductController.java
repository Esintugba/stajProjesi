package org.proje.web;


import org.proje.model.Product;
import org.proje.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products")
    public ModelAndView getProducts(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("products",productService.findProducts());
        mav.setViewName("products");
        return mav;
    }

    @GetMapping(value = "/products/new")
    public String newProduct(){
        return "newProduct";
    }

    @ModelAttribute
    public Product initModel(){
        return new Product();
    }

    @PostMapping(value = "/products/new")
    public String handleFormSubmitCreate(@ModelAttribute @Valid Product product, BindingResult bindingResult , RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "newProducts";
        }
        productService.createProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product created with id :" + product.getId());
        return "redirect:/products";
    }

    @GetMapping(value = "/products/update/{id}")
    public String loadUpdateProduct(@PathVariable Long id, ModelMap modelMap){
        Product product=productService.findProduct(id);
        modelMap.put("product",product);
        return "editProduct";
    }

    @PostMapping(value = "/products/update/{id}")
    public String handleFormSubmitUpdate(@ModelAttribute @Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "editProduct";
        }
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product created with id :" + product.getId());
        return "redirect:/products";
    }

    @GetMapping(value ="/products/delete/{id}")
    public String loadDeleteProduct(@PathVariable Long id, ModelMap modelMap){
        Product product=productService.findProduct(id);
        modelMap.put("product",product);
        return "deleteProduct";
    }

    @PostMapping(value ="/products/delete/{id}")
    public String handleFormSubmitDelete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("message", "Product created with id :" + id);
        return "redirect:/products";
    }

}
