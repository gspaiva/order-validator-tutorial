package br.com.gabriel.paiva.ordervalidator.ordervalidator.domain;

import br.com.gabriel.paiva.ordervalidator.ordervalidator.Validator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable , Validator {


    private Long id;

    private String clientName;
    private Integer clientScore;

    private String cardOwner;
    private String cardNumber;

    private BigDecimal price;
    private Integer amount;

    public List<String> validationMessages = new ArrayList<>();


    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getClientScore() {
        return clientScore;
    }

    public void setClientScore(Integer clientScore) {
        this.clientScore = clientScore;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getValidationMessagesAsJson(){
        StringBuilder json = new StringBuilder("messages:[");
        validationMessages.forEach(message -> {
            json.append("'" + message + "'" + ",");
        });
        json.append("]");
        return json.toString();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", clientScore=" + clientScore +
                ", cardOwner='" + cardOwner + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    @Override
    public void validate() {
        if(!this.clientName.equals(cardOwner)){
            validationMessages.add("Client name is not equal card owner");
        }

        if(this.amount > 500){
            validationMessages.add("Amount too big for configured threshold");
        }

        if(this.clientScore < 50){
            validationMessages.add("Score too low for configured threshold");
        }
    }

}
