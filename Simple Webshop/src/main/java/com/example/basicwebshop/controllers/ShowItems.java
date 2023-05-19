package com.example.basicwebshop.controllers;

import com.example.basicwebshop.models.ShopItem;
import com.example.basicwebshop.models.WebShop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class ShowItems {
    private final WebShop webshop = new WebShop();
    private String currency = " Kč";

    public ShowItems() {
        ShopItem shoes = new ShopItem("Running shoes", "Clothes and Shoes", "Nike running shoes for everyday sport", 1000, 5);
        ShopItem printer = new ShopItem("Printer", "Electronics", "Some HP printer that will print pages", 3000, 2);
        ShopItem cocaCola = new ShopItem("Coca Cola", "Beverages and Snacks", "0.5l standard coke", 25, 0);
        ShopItem cocaColaLight = new ShopItem("Coca Cola Light", "Beverages and Snacks","0.5l Light coke", 25, 3);
        ShopItem wokin = new ShopItem("Wokin", "Beverages and Snacks","Chicken with fried rice and WOKIN sauce", 119, 100);
        ShopItem tShirt = new ShopItem("T-Shirt", "Clothes and Shoes","Blue with Corgi on a bike", 300, 1);

        webshop.add(shoes);
        webshop.add(printer);
        webshop.add(cocaCola);
        webshop.add(cocaColaLight);
        webshop.add(wokin);
        webshop.add(tShirt);
    }

    // Show all items
    @GetMapping(value = "/showitems")
    public String showItems(Model model) {
        model.addAttribute("items", webshop.getShopItems());
        model.addAttribute("pageType", "showitems");
        return "index";
    }

    // Show only available items
    @GetMapping(value = "/only-available")
    public String showOnlyAvailableItems(Model model) {
        List<ShopItem> availableItems = webshop.getShopItems().stream()
                .filter(item -> item.getQuantityOfStock() > 0)
                .collect(Collectors.toList());

        model.addAttribute("items", availableItems);
        model.addAttribute("pageType", "only-available");
        return "index";
    }

    // Sort the list of items in ascending order
    @GetMapping(value = "/cheapest-first")
    public String cheapestFirst(Model model) {
        List<ShopItem> availableItems = webshop.getShopItems().stream()
                .sorted(Comparator.comparingInt(ShopItem::getPrice))
                .collect(Collectors.toList());

        model.addAttribute("items", availableItems);
        model.addAttribute("pageType", "cheapest-first");
        return "index";
    }

    // Get item(s) that contain "NIKE" in name or description
    @GetMapping(value = "/contains-nike")
    public String containsNike(Model model) {
        List<ShopItem> availableItems = webshop.getShopItems().stream()
                .filter(item -> item.getDescription().toLowerCase().contains("nike") || item.getName().toLowerCase().contains("nike"))
                .collect(Collectors.toList());

        model.addAttribute("items", availableItems);
        model.addAttribute("pageType", "contains-nike");
        return "index";
    }

    // Get average stock value
    @GetMapping(value = "/average-stock")
    public String averageStock(Model model) {
        double averageStock = webshop.getShopItems().stream()
                .mapToInt(ShopItem::getQuantityOfStock)
                .average()
                .orElse(0.0);

        model.addAttribute("averageStock", averageStock);
        model.addAttribute("pageType", "average-stock");
        return "average";
    }

    // Filter most expensive item
    @GetMapping(value = "/most-expensive")
    public String mostExpensive(Model model) {
        String mostExpensiveItem = webshop.getShopItems().stream()
                .max(Comparator.comparingInt(ShopItem::getPrice))
                .map(ShopItem::getName)
                .orElse("");

        model.addAttribute("mostExpensiveItem", mostExpensiveItem);
        model.addAttribute("pageType", "most-expensive");
        return "average";
    }

    // Search bar
    @PostMapping(value = "/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<ShopItem> searchResults = webshop.getShopItems().stream()
                .filter(item -> item.getName().toLowerCase().contains(keyword) || item.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        model.addAttribute("items", searchResults);
        model.addAttribute("pageType", "search");
        return "index";
    }

    // =========
    // More >> (Optional Extension)
    // =========

    // Go to another page with additional filters
    @RequestMapping(value = "/more-filters")
    public String showMore(Model model) {
        model.addAttribute("items", webshop.getShopItems());
        model.addAttribute("pageType", "showitems");
        return "extension";
    }

    // Filter items by type
    @RequestMapping(value = "/filter-by-type/{type}")
    public String filterByType(Model model, @PathVariable("type") String type) {
        List<ShopItem> filterType = webshop.getShopItems().stream()
                .filter(item -> item.getType().replaceAll(" ", "-").equalsIgnoreCase(type))
                .collect(Collectors.toList());

        model.addAttribute("items", filterType);
        model.addAttribute("pageType", "/filter-by-type/" + type);
        return "extension";
    }

    // Display price in EUR
    @GetMapping(value = "/price-in-eur")
    public String priceInEuro(Model model) {
        currency = " EUR";
        List<ShopItem> items = webshop.getShopItems().stream()
                .peek(item -> item.setPrice((item.getPrice() / 23)))
                .collect(Collectors.toList());

        model.addAttribute("items", items);
        model.addAttribute("currency", currency);
        model.addAttribute("pageType", "price-in-eur");
        return "extension";
    }

    // Display price in EUR
    @GetMapping(value = "/price-in-original")
    public String priceInCZK(Model model) {
        currency = " Kč";
        List<ShopItem> items = webshop.getShopItems().stream()
                .peek(item -> item.setPrice((item.getPrice() * 23)))
                .collect(Collectors.toList());

        model.addAttribute("items", items);
        model.addAttribute("currency", currency);
        model.addAttribute("pageType", "price-in-original");
        return "extension";
    }

    // Show currency on webpage
    @ModelAttribute
    public void setCurrency(Model model) {
        model.addAttribute("currency", currency);
    }
}
