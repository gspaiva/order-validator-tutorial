package br.com.gabriel.paiva.ordervalidator.ordervalidator.service;

import org.springframework.stereotype.Service;
import br.com.gabriel.paiva.ordervalidator.ordervalidator.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

@Service
public class OrderValidatorService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(OrderValidatorService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "order-validator")
    public void orderValidator(String orderContent) throws Exception{
        try {
            Order order = objectMapper.readValue(orderContent, Order.class);
            order.validate();
            logger.info("Order received =" + order.getId());

            if(order.validationMessages.isEmpty())
            {
                sendMessageToOrderFinisher("{\"status\": \"ORDER_VALID\", id:" + order.getId() + "}" );
            }
            else{
                logger.info("Order is not valid");
                sendMessageToOrderFinisher("{\"status\": \"ORDER_NOT_VALID\", id:" + order.getId() + ", "
                        + order.getValidationMessagesAsJson() + "}"  );
            }
        }catch (Exception e){
            throw e;
        }
    }

    @JmsListener(destination = "order-finisher")
    public void orderFinisher(String orderFinisher) throws Exception{
        try {
            logger.info("Finishing order:" + orderFinisher);
        }catch (Exception e){
            throw e;
        }
    }

    private void sendMessageToOrderFinisher (String message){
        try{
            jmsTemplate.convertAndSend("order-finisher", message);
        }catch (Exception e){
            throw e;
        }
    }
}
