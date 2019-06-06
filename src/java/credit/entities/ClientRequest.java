package credit.entities;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class ClientRequest {
    @Valid
    private Client client;
    
    @Min(value = 5000, message = "Размер кредита должен быть не менее 5000 руб.")
    @Max(value = 300_000, message = "Размер кредита не должен быть более 300 000 руб.")
    @Positive(message = "Размер кредита должен быть положительным")
    private int amount;
    
    @Positive(message = "Срок кредита должен быть положительным")
    private int termInDays;
    
    private boolean approved;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTermInDays() {
        return termInDays;
    }

    public void setTermInDays(int termInDays) {
        this.termInDays = termInDays;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
     
}
