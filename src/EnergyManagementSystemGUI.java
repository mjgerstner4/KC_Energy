import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnergyManagementSystemGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField addressField;
    private JTextField readingsField;
    private JTextArea resultArea;

    public EnergyManagementSystemGUI() {
        setTitle("Energy Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        inputPanel.add(phoneNumberField);
        inputPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Meter Readings (separated by space):"));
        readingsField = new JTextField();
        inputPanel.add(readingsField);

        JButton generateButton = new JButton("Generate Invoice");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateInvoice();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(generateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(resultArea, BorderLayout.EAST);

        add(mainPanel);
    }

    private void generateInvoice() {
        String name = nameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String address = addressField.getText();
        String readingsInput = readingsField.getText();

        // Create instances of EnergyTariff, MeterType, and Customer
        EnergyTariff energyTariff = new EnergyTariff("Standard", 0.10);
        MeterType meterType = new MeterType("Electric", 0.15);
        Customer customer = new Customer(name, phoneNumber, address, energyTariff, meterType);

        // Add meter readings
        String[] readingsArray = readingsInput.split(" ");
        for (String reading : readingsArray) {
            double meterReading = Double.parseDouble(reading);
            customer.addMeterReading(meterReading);
        }

        // Generate monthly invoice
        Invoice invoice = customer.generateMonthlyInvoice();

        // Display customer details and invoice in the result area
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Customer Details:\n");
        resultBuilder.append("Name: ").append(customer.getName()).append("\n");
        resultBuilder.append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
        resultBuilder.append("Address: ").append(customer.getAddress()).append("\n\n");
        resultBuilder.append("Invoice Details:\n");
        resultBuilder.append("Start Date: ").append(invoice.getStartDate()).append("\n");
        resultBuilder.append("End Date: ").append(invoice.getEndDate()).append("\n");
        resultBuilder.append("Energy Usage: ").append(invoice.getEnergyUsage()).append("\n");
        resultBuilder.append("Energy Charge: ").append(invoice.getEnergyCharge()).append("\n");

        resultArea.setText(resultBuilder.toString());
    }
}
