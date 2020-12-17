package com.emily.simplewebapp.controllers;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
    private static ArrayList<String> friends = new ArrayList<String>();

    @GetMapping("welcome")
    public String showWelcome(){
        return "welcome";
    }
    @PostMapping("welcome")
    public String processName(@RequestParam String name){
        return "redirect:/hello?name="+name;
    }

    @GetMapping("hello")
    public String showHello(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("friends", friends);
        return "hello";
    }

    @PostMapping("hello")
    public String getFriend(@RequestParam String friend, String name, Model model){
        friends.add(friend);
        model.addAttribute("name", name);
        model.addAttribute("friends", friends);
        return "hello";
    }

    @GetMapping("delete")
    public String showDelete(Model model){
        model.addAttribute("friends", friends);
        return "delete";
    }

    @PostMapping("delete")
    public String processDelete(@RequestParam String friend, String name, Model model){
        String[] words = friend.split(",");
        ArrayList<Integer> nums = new ArrayList<Integer>();

        for(String w : words){
            nums.add(Integer.parseInt(w));
        }

        Collections.sort(nums);
        Collections.reverse(nums);

        for(int n : nums){
            friends.remove(n);
        }
        
        model.addAttribute("name", name);
        model.addAttribute("friends", friend);
        return "redirect:/hello?name="+name;
    }

}