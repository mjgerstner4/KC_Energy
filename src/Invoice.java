import java.time.LocalDateTime;

public class Invoice {
    private Customer customer;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double energyUsage;
    private double energyCharge;
    private double totalAmountDue;

    public Invoice(Customer customer, LocalDateTime startDate, LocalDateTime endDate, double energyUsage, double energyCharge) {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.energyUsage = energyUsage;
        this.energyCharge = energyCharge;
        this.totalAmountDue = energyCharge;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getEnergyUsage() {
        return energyUsage;
    }

    public double getEnergyCharge() {
        return energyCharge;
    }

    public double getTotalAmountDue() {
        return totalAmountDue;
    }

    public void markAsPaid() {
        totalAmountDue = 0;
    }
}