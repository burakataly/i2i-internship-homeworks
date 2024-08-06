package org.example;

public class Message {
    private String operation;
    private Integer operand;

    public Message(Integer operand, String operation) {
        this.operand = operand;
        this.operation = operation;
    }

    public Message() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getOperand() {
        return operand;
    }

    public void setOperand(Integer operand) {
        this.operand = operand;
    }
}

