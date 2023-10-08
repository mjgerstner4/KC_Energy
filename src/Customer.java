import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import javax.swing.SwingUtilities;

public class Customer {
    private String name;
    private String phoneNumber;
    private String address;
    private EnergyTariff energyTariff;
    private MeterType meterType;
    private List<MeterReading> meterReadings;

    // Constructor
    public Customer(String name, String phoneNumber, String address, EnergyTariff energyTariff, MeterType meterType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.energyTariff = energyTariff;
        this.meterType = meterType;
        this.meterReadings = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EnergyTariff getEnergyTariff() {
        return energyTariff;
    }

    public void setEnergyTariff(EnergyTariff energyTariff) {
        this.energyTariff = energyTariff;
    }

    public MeterType getMeterType() {
        return meterType;
    }

    public void setMeterType(MeterType meterType) {
        this.meterType = meterType;
    }

    public List<MeterReading> getMeterReadings() {
        return meterReadings;
    }

    // Add a meter reading
    public void addMeterReading(double reading) {
        MeterReading meterReading = new MeterReading(reading, LocalDateTime.now());
        meterReadings.add(meterReading);
    }

    // Get the energy usage for a billing period
    public double getEnergyUsage(LocalDateTime startDate, LocalDateTime endDate) {
        double startReading = 0;
        double endReading = 0;

        for (MeterReading meterReading : meterReadings) {
            if (meterReading.getDateTime().isBefore(startDate)) {
                startReading = meterReading.getReading();
            }

            if (meterReading.getDateTime().isBefore(endDate)) {
                endReading = meterReading.getReading();
            }
        }

        return endReading - startReading;
    }

    // Generate a monthly invoice for the customer
    public Invoice generateMonthlyInvoice() {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMonths(1);

        double energyUsage = getEnergyUsage(startDate, endDate);
        double energyCharge = energyUsage * energyTariff.getRate();

        Invoice invoice = new Invoice(this, startDate, endDate, energyUsage, energyCharge);
        return invoice;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EnergyManagementSystemGUI gui = new EnergyManagementSystemGUI();
                gui.setVisible(true);
            }
        });
    }

}