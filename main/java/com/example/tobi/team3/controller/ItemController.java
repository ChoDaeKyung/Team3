package com.example.tobi.team3.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

    @GetMapping("/main/category")
    public String itemList(HttpSession session, Model model){
        setSession(session, model);
        return "category";
    }

    @GetMapping("/main/mypage/deleteItem")
    public String deleteItem(HttpSession session, Model model){
        setSession(session, model);
        return "deleteItem";
    }

    @GetMapping("/main/category/detail")
    public String detail(
            @RequestParam("id") Long id,
            HttpSession session,
            Model model){
        setSession(session, model);
        model.addAttribute("id", id);
        return "itemDetails";
    }

    private void setSession(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userId",userId);
        model.addAttribute("userName",userName);
    }

    @GetMapping("/main/mypage/addItem")
    public String addItem(HttpSession session, Model model){
        setSession(session, model);
        return "addItem";
    }
    @GetMapping("/main/search")
    public String searchItem(@RequestParam("search") String search, HttpSession session, Model model){
        setSession(session, model);
        model.addAttribute("search", search);
        return "searchItem";
    }
}