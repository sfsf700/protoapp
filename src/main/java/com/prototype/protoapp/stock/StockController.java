package com.prototype.protoapp.stock;

import com.prototype.protoapp.stock.entity.Stock;
import com.prototype.protoapp.stock.form.GetForm;
import com.prototype.protoapp.stock.form.PostForm;
import com.prototype.protoapp.stock.form.PutForm;
import com.prototype.protoapp.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class StockController {

    private final StockService stockService;
    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @GetMapping
    public String stockList(@ModelAttribute GetForm form,
            Model model) {
        List<Stock> list = stockService.findList(form);
        model.addAttribute("list", list);
        model.addAttribute("getForm", form);
        return "list";
    }

    @PostMapping("/form")
    public String formPage(@ModelAttribute PutForm form, Model model) {
        model.addAttribute("putForm", form);
        if (form.getUpdateFlag()) {
            model.addAttribute("update", true);
        } else {
            model.addAttribute("update", false);
        }
        return "form";
    }
    @PostMapping(path = {"/insert", "/form", "/update"}, params = "back")
    public String backPage(Model model) {
        return "redirect:/stock";
    }


    @PostMapping(path = "/insert", params = "insert")
    public String insert(@Valid @ModelAttribute PostForm form, BindingResult result,  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "パラメータエラーが発生しました。");
            return "form";
        }
        int count = stockService.insert(form);
        model.addAttribute("postForm", form);
        return "redirect:/stock";
    }

    @GetMapping("/{id}")
    public String showUpdate(@PathVariable int id, Model model) {
        Optional<Stock> stockOptional = Optional.ofNullable(stockService.findById(id));

        if(stockOptional.isPresent()) {
            model.addAttribute("stock", stockOptional.get());
        } else {
            model.addAttribute("error", "対象データが存在しません");
        }
        return "detail";
    }


    @PostMapping(path="/update", params = "update")
    public String update(@Valid @ModelAttribute PutForm form, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("error", "パラメータエラーが発生しました。");
            return "form";
        }
        int count = stockService.update(form);
        return "redirect:/stock";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        int count = stockService.delete(id);
        return "redirect:/stock";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



}