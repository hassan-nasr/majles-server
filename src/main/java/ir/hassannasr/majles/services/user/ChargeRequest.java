package ir.hassannasr.majles.services.user;

/**
 * Created by hassan on 10/01/2016.
 */
public class ChargeRequest {
    String phone;
    Long amount;
    String type;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
