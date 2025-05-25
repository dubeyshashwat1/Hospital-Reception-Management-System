import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class HospitalReception extends JFrame implements ActionListener {
    private JTextField idField, nameField, ageField, phoneField, addressField, diseaseField, appointmentField, doctorField;
    private JComboBox<String> genderBox;
    private JTextArea outputArea;
    private JButton addButton, updateButton, deleteButton, searchButton, generateButton, clearButton;
    private Connection conn;

    public HospitalReception() {
        createLoginWindow();
    }

    private void createLoginWindow() {
        JFrame loginFrame = new JFrame("Hospital Login");
        loginFrame.setSize(350, 200);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        loginPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        loginPanel.add(usernameField);
        
        loginPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            if (usernameField.getText().equals("admin") && new String(passwordField.getPassword()).equals("admin123")) {
                loginFrame.dispose();
                createMainWindow();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid username or password!");
            }
        });
        
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);
        
        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }

    private void createMainWindow() {
        setTitle("Hospital Reception System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_db", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
        }

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 248, 255)); // AliceBlue

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
        formPanel.setBackground(Color.WHITE);

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        diseaseField = new JTextField();
        appointmentField = new JTextField();
        doctorField = new JTextField();
        genderBox = new JComboBox<>(new String[]{"M", "F", "O"});

        generateButton = new JButton("Generate ID");
        generateButton.addActionListener(e -> idField.setText(String.valueOf(new Random().nextInt(90000) + 10000)));

        addFormField(formPanel, "Patient ID:", idField, generateButton);
        addFormField(formPanel, "Full Name:", nameField, null);
        addFormField(formPanel, "Age:", ageField, null);
        addFormField(formPanel, "Gender:", genderBox, null);
        addFormField(formPanel, "Phone:", phoneField, null);
        addFormField(formPanel, "Address:", addressField, null);
        addFormField(formPanel, "Disease:", diseaseField, null);
        addFormField(formPanel, "Appointment Date:", appointmentField, null);
        addFormField(formPanel, "Doctor:", doctorField, null);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));

        addButton = createColorButton("Add", new Color(50, 205, 50)); // LimeGreen
        updateButton = createColorButton("Update", new Color(30, 144, 255)); // DodgerBlue
        deleteButton = createColorButton("Delete", new Color(220, 20, 60)); // Crimson
        searchButton = createColorButton("Search", new Color(255, 165, 0)); // Orange
        clearButton = createColorButton("Clear", new Color(169, 169, 169)); // DarkGray

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);
        clearButton.addActionListener(e -> clearFields());

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);

        // Output Area
        outputArea = new JTextArea(8, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("System Messages"));

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void addFormField(JPanel panel, String label, JComponent field, JComponent extra) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel.add(lbl);
        
        if (extra != null) {
            JPanel p = new JPanel(new BorderLayout());
            p.add(field, BorderLayout.CENTER);
            p.add(extra, BorderLayout.EAST);
            panel.add(p);
        } else {
            panel.add(field);
        }
    }

    private JButton createColorButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(100, 30));
        return btn;
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        phoneField.setText("");
        addressField.setText("");
        diseaseField.setText("");
        appointmentField.setText("");
        doctorField.setText("");
        genderBox.setSelectedIndex(0);
        outputArea.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        String id = idField.getText();
        String name = nameField.getText();
        String age = ageField.getText();
        String gender = (String) genderBox.getSelectedItem();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String disease = diseaseField.getText();
        String appointment = appointmentField.getText();
        String doctor = doctorField.getText();

        try {
            if (e.getSource() == addButton) {
                if (name.isEmpty() || age.isEmpty() || phone.isEmpty()) {
                    outputArea.setText("Please fill all required fields!");
                    return;
                }
                
                PreparedStatement pst = conn.prepareStatement("INSERT INTO patients VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pst.setInt(1, Integer.parseInt(id));
                pst.setString(2, name);
                pst.setInt(3, Integer.parseInt(age));
                pst.setString(4, gender);
                pst.setString(5, phone);
                pst.setString(6, address);
                pst.setString(7, disease);
                pst.setString(8, appointment);
                pst.setString(9, doctor);
                pst.executeUpdate();
                outputArea.setText("Patient added successfully!\nID: " + id + "\nName: " + name);
                
            } else if (e.getSource() == searchButton) {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM patients WHERE id=? OR name=? OR phone=?");
                pst.setString(1, id);
                pst.setString(2, name);
                pst.setString(3, phone);
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    idField.setText(rs.getString("id"));
                    nameField.setText(rs.getString("name"));
                    ageField.setText(rs.getString("age"));
                    genderBox.setSelectedItem(rs.getString("gender"));
                    phoneField.setText(rs.getString("phone"));
                    addressField.setText(rs.getString("address"));
                    diseaseField.setText(rs.getString("disease"));
                    appointmentField.setText(rs.getString("appointment_date"));
                    doctorField.setText(rs.getString("doctor"));
                    outputArea.setText("Record found for ID: " + id);
                } else {
                    outputArea.setText("No matching records found!");
                }
                
            } else if (e.getSource() == updateButton) {
                PreparedStatement pst = conn.prepareStatement(
                    "UPDATE patients SET name=?, age=?, gender=?, phone=?, address=?, disease=?, appointment_date=?, doctor=? WHERE id=?");
                pst.setString(1, name);
                pst.setInt(2, Integer.parseInt(age));
                pst.setString(3, gender);
                pst.setString(4, phone);
                pst.setString(5, address);
                pst.setString(6, disease);
                pst.setString(7, appointment);
                pst.setString(8, doctor);
                pst.setInt(9, Integer.parseInt(id));
                int rows = pst.executeUpdate();
                outputArea.setText(rows + " record(s) updated for ID: " + id);
                
            } else if (e.getSource() == deleteButton) {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM patients WHERE id=?");
                pst.setInt(1, Integer.parseInt(id));
                int rows = pst.executeUpdate();
                outputArea.setText(rows + " record(s) deleted for ID: " + id);
                clearFields();
            }
        } catch (Exception ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new HospitalReception();
    }
}