import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverterGUI extends JFrame {
    private JComboBox<String> fromCurrencyCombo;
    private JComboBox<String> toCurrencyCombo;
    private JTextField amountField;
    private JLabel resultLabel;

    // Exchange rates relative to USD
    private final HashMap<String, Double> exchangeRates = new HashMap<>();

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        initializeExchangeRates();
        initializeUI();
    }

    private void initializeExchangeRates() {
        // Rates relative to USD
        exchangeRates.put("USD", 1.00);
        exchangeRates.put("EUR", 0.93);
        exchangeRates.put("INR", 83.21);
        exchangeRates.put("GBP", 0.80);
        exchangeRates.put("JPY", 155.62);
        exchangeRates.put("CAD", 1.37);
        exchangeRates.put("AUD", 1.51);
        exchangeRates.put("CNY", 7.24);
        exchangeRates.put("CHF", 0.91);
    }

    private void initializeUI() {
        // Create panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Currency Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Amount input
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Amount:"), gbc);

        amountField = new JTextField(10);
        gbc.gridx = 1;
        panel.add(amountField, gbc);

        // From currency
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("From Currency:"), gbc);

        fromCurrencyCombo = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        gbc.gridx = 1;
        panel.add(fromCurrencyCombo, gbc);

        // To currency
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("To Currency:"), gbc);

        toCurrencyCombo = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        gbc.gridx = 1;
        panel.add(toCurrencyCombo, gbc);

        // Convert button
        JButton convertButton = new JButton("Convert");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        convertButton.setBackground(new Color(30, 144, 255));
        convertButton.setForeground(Color.WHITE);
        panel.add(convertButton, gbc);

        // Result label
        resultLabel = new JLabel("Result will appear here");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(new Color(34, 139, 34));
        gbc.gridy = 5;
        panel.add(resultLabel, gbc);

        // Action Listener
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        add(panel);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = (String) fromCurrencyCombo.getSelectedItem();
            String toCurrency = (String) toCurrencyCombo.getSelectedItem();

            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);

            double usdAmount = amount / fromRate;
            double convertedAmount = usdAmount * toRate;

            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid amount.");
        } catch (Exception e) {
            resultLabel.setText("Conversion error.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterGUI converter = new CurrencyConverterGUI();
            converter.setVisible(true);
        });
    }
}