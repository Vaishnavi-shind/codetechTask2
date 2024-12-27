import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeTracker extends JFrame {
    private JTextField rollNoField, nameField, subject1Field, subject2Field, subject3Field;
    private JButton addButton, clearButton, deleteButton;
    private JTable studentTable;
    private DefaultTableModel model;

    public StudentGradeTracker() {
        setTitle("Student Grade Tracker");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top Panel for input fields
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2, 10, 10));

        topPanel.add(new JLabel("Roll No:"));
        rollNoField = new JTextField();
        topPanel.add(rollNoField);

        topPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        topPanel.add(nameField);

        topPanel.add(new JLabel("Subject 1:"));
        subject1Field = new JTextField();
        topPanel.add(subject1Field);

        topPanel.add(new JLabel("Subject 2:"));
        subject2Field = new JTextField();
        topPanel.add(subject2Field);

        topPanel.add(new JLabel("Subject 3:"));
        subject3Field = new JTextField();
        topPanel.add(subject3Field);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel for JTable
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Roll No", "Name", "Subject 1", "Subject 2", "Subject 3", "Total", "Average", "Grade"});
        studentTable = new JTable(model);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        // Bottom Panel for buttons
        JPanel bottomPanel = new JPanel();
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
        deleteButton = new JButton("Delete");
        
        bottomPanel.add(addButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(deleteButton);
        
        add(bottomPanel, BorderLayout.SOUTH);

        // Add Button ActionListener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        // Clear Button ActionListener
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Delete Button ActionListener
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });
    }

    // Method to add student data to the table
    private void addStudent() {
        try {
            String rollNo = rollNoField.getText();
            String name = nameField.getText();
            int sub1 = Integer.parseInt(subject1Field.getText());
            int sub2 = Integer.parseInt(subject2Field.getText());
            int sub3 = Integer.parseInt(subject3Field.getText());

            // Calculate total, average, and grade
            int total = sub1 + sub2 + sub3;
            double average = total / 3.0;
            String grade;
            if (average >= 90) {
                grade = "A";
            } else if (average >= 80) {
                grade = "B";
            } else if (average >= 70) {
                grade = "C";
            } else {
                grade = "D";
            }

            // Add data to the table
            model.addRow(new Object[]{rollNo, name, sub1, sub2, sub3, total, average, grade});
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for subjects.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear input fields
    private void clearFields() {
        rollNoField.setText("");
        nameField.setText("");
        subject1Field.setText("");
        subject2Field.setText("");
        subject3Field.setText("");
    }

    // Method to delete selected row
    private void deleteSelectedRow() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Set the Nimbus Look and Feel for better UI
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run the application
        SwingUtilities.invokeLater(() -> {
            StudentGradeTracker tracker = new StudentGradeTracker();
            tracker.setVisible(true);
});
}
}