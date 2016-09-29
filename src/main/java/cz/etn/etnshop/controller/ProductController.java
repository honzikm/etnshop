package cz.etn.etnshop.controller;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.service.ProductService;

@Controller
@RequestMapping("/product")
@Scope(value = "session")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	private ModelAndView modelAndView = new ModelAndView("product/list");

	@RequestMapping("/list")
	public ModelAndView list() {
		modelAndView.setViewName("/product/list");
//		ModelAndView modelAndView = new ModelAndView("product/list");
//		modelAndView.addObject("products", productService.getProducts());
		System.out.println("list size: " + modelAndView.toString());
		return modelAndView;
	}
	
	@RequestMapping("/listAll")
	public ModelAndView listAll(){
		modelAndView = new ModelAndView("redirect:/product/list");
		modelAndView.addObject("products", productService.getProducts());
		return modelAndView;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView searchProduct (
			@RequestParam(value="key", required=true) String key)
	{
		modelAndView = new ModelAndView("redirect:/product/list");
		key = key.trim();
		if(key == ""){
			modelAndView.addObject("products", productService.getProducts());
		}
		else {
			modelAndView.addObject("products", productService.getProducts(key));
		}
		return modelAndView;
	}
	
	
	@RequestMapping("/newProduct")
	public String newProduct() {
		return "product/newproduct";
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct( 
			@RequestParam(value="serialNumber", required=true) String serialNumber,
			@RequestParam(value="name", required=true) String name){

		serialNumber = serialNumber.trim();
		name = name.trim();
		
		if (name == "" || serialNumber == ""){
			return "/product/listAll";
		}
		
		Product p = new Product();
		p.setName(name);
		p.setSerialNumber(serialNumber);
		
		productService.saveOrUpdateProduct(p);
		
		return "redirect:/product/listAll";
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(
			@RequestParam(value="id", required=true) Integer id) {
		productService.removeProduct(id);
		return "redirect:/product/listAll";
	}
	
	@RequestMapping("/editProduct")
	public ModelAndView editProduct(
			@RequestParam(value="id", required=true) Integer id) {
		ModelAndView modelAndView = new ModelAndView("product/editProduct");
		Product p = productService.getProduct(id);
		if(p != null) {
			modelAndView.addObject("product", productService.getProduct(id));
		}
		else {
			modelAndView = new ModelAndView("product/listAll");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveProduct",  method = RequestMethod.POST)
	public String saveProduct (
			@RequestParam(value="id", required=true) Integer id,
			@RequestParam(value="serialNumber", required=true) String serialNumber,
			@RequestParam(value="name", required=true) String name) {
		
		Product p = productService.getProduct(id);
		p.setName(name);
		p.setSerialNumber(serialNumber);
		productService.saveOrUpdateProduct(p);
		return "redirect:/product/listAll";
	}
}
