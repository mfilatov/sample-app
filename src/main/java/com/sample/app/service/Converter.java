package com.sample.app.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.sample.app.dao.OfficeDAO;
import com.sample.app.model.DeliveryShift;
import com.sample.app.model.OrderStatus;
import com.sample.app.model.db.Address;
import com.sample.app.model.db.Contact;
import com.sample.app.model.db.Office;
import com.sample.app.model.db.Order;
import com.sample.app.model.pojo.OrderCSVBean;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Converter {
    private static final Logger logger = LoggerFactory.getLogger(Converter.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    private static final String[] FIELD_MAPPING = new String[] {
            "deliveryDate",
            "deliveryShift",
            "originName",
            "originRawLine",
            "originCity",
            "originState",
            "originZip",
            "originCountry",
            "clientName",
            "destinationRawLine",
            "destinationCity",
            "destinationState",
            "destinationZip",
            "destinationCountry",
            "phoneNumber",
            null,
            "purchaseOrderNumber",
            "volume",
            "handlingUnitQuantity",
            null
    };

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Autowired
    OfficeDAO officeDAO;

    public List<Order> convert(InputStream stream) throws IOException {
        ArrayList<Order> result = new ArrayList<Order>();
        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new InputStreamReader(stream), CsvPreference.STANDARD_PREFERENCE);

            beanReader.getHeader(true);

            OrderCSVBean orderCSVBean;
            while ((orderCSVBean = beanReader.read(OrderCSVBean.class, FIELD_MAPPING)) != null) {
                Order order = new Order();
                order.setRawData(beanReader.getUntokenizedRow());
                try {
                    order.setPurchaseNumber(getPurchaseNumber(orderCSVBean));
                    order.setVolume(getVolume(orderCSVBean));
                    order.setQuantity(getQuantity(orderCSVBean));
                    order.setDeliveryDate(getDeliveryDate(orderCSVBean));
                    order.setDeliveryShift(getDeliveryShift(orderCSVBean));
                    order.setAddress(getAddress(orderCSVBean));
                    order.setContact(getContact(orderCSVBean));
                    order.setOffice(getOffice(orderCSVBean));
                    order.setOrderStatus(OrderStatus.PARSED);
                } catch (Exception e) {
                    logger.error("exception in CSV parsing process", e);
                    order.setOrderStatus(OrderStatus.PARSING_ERROR);
                }

                result.add(order);
            }
        } finally {
            IOUtils.closeQuietly(beanReader);
        }

        return result;
    }

    private Long getPurchaseNumber(OrderCSVBean orderCSVBean) {
        return Long.parseLong(orderCSVBean.getPurchaseOrderNumber());
    }

    private Double getVolume(OrderCSVBean orderCSVBean) {
        return Double.parseDouble(orderCSVBean.getVolume());
    }

    private Integer getQuantity(OrderCSVBean orderCSVBean) {
        return Integer.parseInt(orderCSVBean.getHandlingUnitQuantity());
    }

    private Date getDeliveryDate(OrderCSVBean orderCSVBean) throws Exception {
        String deliveryDateStr = orderCSVBean.getDeliveryDate();

        if (deliveryDateStr == null) {
            logger.error("deliveryDate is empty");
            throw new Exception("deliveryDate is empty");
        }

        try {
            return sdf.parse(deliveryDateStr);
        } catch (Exception e) {
            logger.error("deliveryDate parse exception", e);
            throw e;
        }
    }

    private DeliveryShift getDeliveryShift(OrderCSVBean orderCSVBean) {
        return DeliveryShift.parseByName(orderCSVBean.getDeliveryShift());
    }

    private Contact getContact(OrderCSVBean orderCSVBean) throws NumberParseException {
        Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(orderCSVBean.getPhoneNumber(), "US");
        return new Contact(orderCSVBean.getClientName(), phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
    }

    private Address getAddress(OrderCSVBean orderCSVBean) {
        return new Address(
                orderCSVBean.getDestinationRawLine(),
                orderCSVBean.getDestinationCity(),
                orderCSVBean.getDestinationState(),
                orderCSVBean.getDestinationZip(),
                orderCSVBean.getDestinationCountry()
        );
    }

    private Office getOffice(OrderCSVBean orderCSVBean) throws Exception {
        String originName = orderCSVBean.getOriginName();
        Office office = officeDAO.findByName(originName);
        if (office == null) {
            throw new Exception("Office not found, name = " + originName);
        }

        return office;
    }
}
