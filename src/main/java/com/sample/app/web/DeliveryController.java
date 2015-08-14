package com.sample.app.web;

import com.sample.app.model.DeliveryShift;
import com.sample.app.model.db.Delivery;
import com.sample.app.service.DeliveryService;
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
public class DeliveryController {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    @Autowired
    DeliveryService deliveryService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/allDeliveries", method = RequestMethod.GET)
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("allDeliveries");
        modelAndView.addObject("deliveries", deliveryService.getAll());

        return modelAndView;
    }

    @RequestMapping(value = "/createDelivery", method = RequestMethod.GET)
    public ModelAndView showCreateDeliveryForm(@RequestParam(required = true) Date date,
                                               @RequestParam(required = true) DeliveryShift deliveryShift,
                                               @RequestParam(required = true) String storageName) {
        return new ModelAndView("editDelivery", "delivery", deliveryService.createDelivery(date, deliveryShift, storageName));
    }

    @RequestMapping(value = "/editDelivery", method = RequestMethod.GET)
    public ModelAndView showEditDeliveryForm(@RequestParam(required = true) String id) {
        return new ModelAndView("editDelivery", "delivery", deliveryService.get(id));
    }

    @RequestMapping(value = "/deleteDelivery", method = RequestMethod.GET)
    public String deleteDelivery(@RequestParam(required = true) String id) {
        deliveryService.remove(id);

        return "redirect:./allDeliveries";
    }

    @RequestMapping(value = "/saveDelivery", method = RequestMethod.POST)
    public String saveDelivery(@ModelAttribute("delivery") Delivery delivery) {
        if(delivery.getId() == null) {
            deliveryService.add(delivery);
        } else {
            deliveryService.update(delivery);
        }

        return "redirect:./allDeliveries";
    }
}
