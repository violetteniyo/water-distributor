package com.example.inyange.controller;

import com.example.inyange.model.Product;
import com.example.inyange.serviceImplementation.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductServiceImplementation productservice;

    @GetMapping("/products")
    public String displayProducts(Model model){
        List<Product> listProduct = productservice.displayProducts();
        model.addAttribute("listProduct", listProduct);

        return "productsPage";
    }
    @GetMapping("/product/new")
    public String addNewProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("pageTitle","Add New Product");
        return "ProductPage";
    }

    @PostMapping("/product/save/new")
    public String saveProduct(Product product, RedirectAttributes ra){
        try{
            Product savedProduct = productservice.saveProduct(product);
            if (savedProduct!=null){
                ra.addFlashAttribute("message","product saved successfully");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        product.setProductName("");
        product.setProductType("");
        product.setQuantity(0);
        product.setPrice_per_unit(0);
        product.setTotalPrice(0);
        return "redirect:/products";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model, RedirectAttributes ra){
        try{
            Product product = productservice.findProductById(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "edit product (ID: "+id+")");

            return "productPage";

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/products";
    }

    @GetMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id, Model model, RedirectAttributes ra){
        try {
            productservice.deleteProduct(id);
            ra.addFlashAttribute("message", "Product successfully deleted");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/products";
    }
}
