package org.proje.web;

import org.proje.exception.CompanyNotFoundException;
import org.proje.exception.InternalServerException;
import org.proje.exception.ProductNotFoundException;
import org.proje.model.Product;
import org.proje.service.ProductService;
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
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/{id}",produces = "application/json")
    public ResponseEntity<?> getProductAsHateoasResource(@PathVariable("id") Long id){
        try {
            Product product= productService.findProduct(id);
            Link self= ControllerLinkBuilder.linkTo(ProductRestController.class).slash("/product/"+id).withSelfRel();
            Link create=ControllerLinkBuilder.linkTo(ProductRestController.class).slash("/product").withRel("create");
            Link update=ControllerLinkBuilder.linkTo(ProductRestController.class).slash("/product/"+id).withRel("update");
            Link delete=ControllerLinkBuilder.linkTo(ProductRestController.class).slash("/product/"+id).withRel("delete");
            Resource<Product> resource=new Resource<Product>(product,self,create,update,delete);

            return ResponseEntity.ok(product);
        }
        catch (ProductNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/product")
    public ResponseEntity<URI> createProduct(@RequestBody Product product){
        try {
            productService.createProduct(product);
            Long id=product.getId();
            URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (ConstraintViolationException ex){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Product productRequest){
        try {
            Product product=productService.findProduct(id);
            product.setProductCode(productRequest.getProductCode());
            product.setName(productRequest.getName());
            product.setCategories(productRequest.getCategories());
            product.setExplanation(productRequest.getExplanation());
            product.setPrice(productRequest.getPrice());
            product.setCompany(productRequest.getCompany());
            productService.updateProduct(product);
            return ResponseEntity.ok().build();
        }catch (ProductNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Long id){
        try {
            productService.deleteProduct(id);
            productService.findProduct(id);
        }catch (ProductNotFoundException ex){
            throw ex;
        }catch (Exception ex){
            throw new InternalServerException(ex);
        }
    }

    @Cacheable("allProducts")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        System.out.println(">>>inside getProducts...");
        List<Product> products=productService.findProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        try{
            Product product=productService.findProduct(id);
            return ResponseEntity.ok(product);
        }
        catch (CompanyNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("ln") Long productCode){
        List<Product> products=productService.findProducts(productCode);
        return ResponseEntity.ok(products);
    }

}


