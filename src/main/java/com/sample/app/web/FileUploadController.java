package com.sample.app.web;

import com.sample.app.model.OrderStatus;
import com.sample.app.model.db.Order;
import com.sample.app.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                List<Order> orders = orderService.loadOrders(file.getInputStream());
                StringBuilder errorMessage = new StringBuilder();
                StringBuilder duplicatedMessage = new StringBuilder();
                for (Order order : orders) {
                    if (order.getOrderStatus() == OrderStatus.PARSING_ERROR) {
                        errorMessage.append("</br>").append(order.getRawData());
                    } else if (order.getOrderStatus() == OrderStatus.DUPLICATED) {
                        duplicatedMessage.append("</br>").append(order.getRawData());
                    }
                }

                StringBuilder resultMessage = new StringBuilder();

                String error = errorMessage.toString();
                if (StringUtils.isNotEmpty(error)) {
                    resultMessage.append("</br>").append("Errors:").append(error);
                }

                String duplicated = duplicatedMessage.toString();
                if (StringUtils.isNotEmpty(duplicated)) {
                    resultMessage.append("</br>").append("Duplicated:").append(duplicated);
                }

                String result = resultMessage.toString();

                return StringUtils.isEmpty(result) ? "File successfully uploaded" : result;
            } catch (Exception e) {
                return "File upload failed" + " => " + e.getMessage();
            }
        } else {
            return "File upload failed because it was empty";
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadForm() {
        return "upload";
    }
}
