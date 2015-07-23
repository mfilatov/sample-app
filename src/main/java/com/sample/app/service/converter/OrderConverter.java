package com.sample.app.service.converter;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.sample.app.dao.StorageDAO;
import com.sample.app.dao.OrderDAO;
import com.sample.app.model.DeliveryShift;
import com.sample.app.model.OrderStatus;
import com.sample.app.model.db.Contact;
import com.sample.app.model.db.Storage;
import com.sample.app.model.db.Order;
import com.sample.app.model.pojo.OrderCSVBean;
import com.sample.app.service.GoogleGeoService;
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
public class OrderConverter implements Converter<Order, InputStream> {
    private static final Logger logger = LoggerFactory.getLogger(OrderConverter.class);

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
    StorageDAO storageDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    GoogleGeoService googleGeoService;


    public List<Order> convert(InputStream stream) throws IOException {
        ArrayList<Order> result = new ArrayList<Order>();
        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new InputStreamReader(stream), CsvPreference.STANDARD_PREFERENCE);

            beanReader.getHeader(true);

            OrderCSVBean orderCSVBean;
            while ((orderCSVBean = beanReader.read(OrderCSVBean.class, FIELD_MAPPING)) != null) {
                String untokenizedRow = beanReader.getUntokenizedRow();

                Order order = new Order();
                order.setRawData(untokenizedRow);

                try {
                    Long purchaseNumber = getPurchaseNumber(orderCSVBean);

                    Order dbOrder = orderDAO.findByPurchaseNumberId(purchaseNumber);
                    if (dbOrder == null) {
                        order.setPurchaseNumber(purchaseNumber);
                        order.setVolume(getVolume(orderCSVBean));
                        order.setQuantity(getQuantity(orderCSVBean));
                        order.setDeliveryDate(getDeliveryDate(orderCSVBean));
                        order.setDeliveryShift(getDeliveryShift(orderCSVBean));
                        order.setAddress(getFormattedAddress(orderCSVBean));
                        order.setContact(getContact(orderCSVBean));
                        order.setStorage(getStorage(orderCSVBean));
                        order.setOrderStatus(OrderStatus.READY);
                    } else {
                        order.setOrderStatus(OrderStatus.DUPLICATED);
                    }
                } catch (Exception e) {
                    logger.error("exception in CSV parsing process: " + untokenizedRow, e);
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

    private String getFormattedAddress(OrderCSVBean orderCSVBean) throws Exception {
        String street = orderCSVBean.getDestinationRawLine();
        String city = orderCSVBean.getDestinationCity();
        String state = orderCSVBean.getDestinationState();
        String zip = orderCSVBean.getDestinationZip();
        String country = orderCSVBean.getDestinationCountry();

        if (street == null || city == null || state == null || zip == null || country == null) {
            throw new Exception("Geo data should not be empty");
        }

        String address = street + ", " + city + ", " + state + " " + zip + ", " + country;

        return googleGeoService.getFormattedAddress(address);
    }

    private Storage getStorage(OrderCSVBean orderCSVBean) throws Exception {
        String originName = orderCSVBean.getOriginName();
        Storage storage = storageDAO.findByName(originName);
        if (storage == null) {
            throw new Exception("Storage not found, name = " + originName);
        }

        return storage;
    }
}
