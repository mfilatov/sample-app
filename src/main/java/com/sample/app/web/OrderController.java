package com.sample.app.web;

import com.sample.app.model.db.Order;
import com.sample.app.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }

    @RequestMapping(value = "/ordersList", method = RequestMethod.GET)
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("ordersList");
        modelAndView.addObject("orders", orderService.getAll());

        return modelAndView;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public ModelAndView showAddOrderForm() {
        return new ModelAndView("editOrder", "order", new Order());
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.GET)
    public ModelAndView showEditOrderForm(@RequestParam(required = true) String id) {
        return new ModelAndView("editOrder", "order", orderService.get(id));
    }

    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    public String deleteOrder(@RequestParam(required = true) String id) {
        orderService.remove(id);

        return "redirect:/ordersList";
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("order") Order order) {
        if(order.getId() == null) {
            orderService.add(order);
        } else {
            orderService.update(order);
        }

        return "redirect:/ordersList";
    }
}
