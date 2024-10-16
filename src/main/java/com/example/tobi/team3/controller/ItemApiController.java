package com.example.tobi.team3.controller;

import com.example.tobi.team3.model.Items;
import com.example.tobi.team3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/{item}")
    public String itemList(@PathVariable String item, HttpSession session, Model model) {
        setSession(session, model);

        List<Items> itemList = itemService.getItemList(item);
        model.addAttribute("items", itemList);

        return "category";
    }

    private void setSession(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        model.addAttribute("userId",userId);
        model.addAttribute("userName",userName);
    }
}
