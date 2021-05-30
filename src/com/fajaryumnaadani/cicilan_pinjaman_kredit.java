package com.fajaryumnaadani;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class cicilan_pinjaman_kredit {
    private JPanel panel_utama;
    private JTextField textBunga;
    private JButton submitButton;
    private JButton clearButton;
    private JComboBox comboBoxLamaAngsuran;
    private JComboBox comboBoxJenisBunga;
    private JTextArea textAngsuran;
    private JTextArea textBesarBunga;
    private JTextArea textSisaPinjaman;
    private JTextArea textPokok;
    private JTextArea textBulan;
    private JLabel labelTotAngPokok;
    private JLabel labelTotAngBunga;
    private JLabel labelTotAng;
    private JTextField textPinjaman;
    private JLabel labelPinjaman;
    private double  bulan, bunga, angsuran_pokok, angsuran, besar_bunga,
            total_bunga, total_angsuran, total_pokok, Pinjaman;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Perhitungan Cicilan Pinjaman Kredit");
        frame.setContentPane(new cicilan_pinjaman_kredit().panel_utama);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void hapus_input(){
        textPinjaman.setText("");
        labelPinjaman.setText("");
        textBunga.setText("");
        comboBoxLamaAngsuran.setSelectedIndex(0);
        comboBoxJenisBunga.setSelectedIndex(0);
    }

    public void hapus_output(){
        textBulan.setText("");
        textPokok.setText("");
        textBesarBunga.setText("");
        textAngsuran.setText("");
        textSisaPinjaman.setText("");
        labelTotAng.setText("");
        labelTotAngBunga.setText("");
        labelTotAngPokok.setText("");
    }

    public void tampilkan_hasil(){
        textPokok.append("    Rp " + String.format("%,.0f",angsuran_pokok) + "\n");
        textBesarBunga.append("    Rp " + String.format("%,.0f",besar_bunga) + "\n");
        textAngsuran.append("    Rp " + String.format("%,.0f",angsuran) + "\n");
        textSisaPinjaman.append("    Rp " +String.format("%,.0f",Pinjaman) +"\n");
    }

    public void  tampilkan_total_hasil(){
        String ttl_bunga = String.valueOf(String.format("%,.0f",total_bunga));
        String ttl_ang = String.valueOf(String.format("%,.0f",total_angsuran));
        String ttl_Pokok = String.valueOf(String.format("%,.0f",total_pokok));
        labelTotAngPokok.setText("    Rp " + ttl_Pokok);
        labelTotAngBunga.setText("    Rp " + ttl_bunga);
        labelTotAng.setText("    Rp " + ttl_ang);
    }

    public cicilan_pinjaman_kredit() {
        textPinjaman.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String fpinjaman = textPinjaman.getText().replaceAll("\\,", "");
                Pinjaman = Double.parseDouble(fpinjaman);
                DecimalFormat df = new DecimalFormat("#,###,###,##0");
                labelPinjaman.setText(" Rp " +df.format(Pinjaman));
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    if(textPinjaman.getText().length() == 1){
                        textPinjaman.setText("");
                        labelPinjaman.setText(" Rp 0");
                    }
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPinjaman.getText().isEmpty() || textBunga.getText().isEmpty()
                        || comboBoxLamaAngsuran.getSelectedItem().equals("Pilihan")
                        || comboBoxJenisBunga.getSelectedItem().equals("Pilihan")) {
                        JOptionPane.showMessageDialog(null, "Lengkapi Input");
                } else {
                    if (comboBoxLamaAngsuran.getSelectedItem().equals("3 Bulan")) {
                        bulan = 3;
                    } else if (comboBoxLamaAngsuran.getSelectedItem().equals("6 Bulan")) {
                        bulan = 6;
                    } else if (comboBoxLamaAngsuran.getSelectedItem().equals("9 Bulan")) {
                        bulan = 9;
                    } else if (comboBoxLamaAngsuran.getSelectedItem().equals("12 Bulan")) {
                        bulan = 12;
                    }

                    Pinjaman = Double.parseDouble(textPinjaman.getText());
                    bunga = Double.parseDouble(textBunga.getText());
                    angsuran_pokok = Pinjaman/bulan;
                    total_bunga = 0;
                    total_angsuran = 0;
                    total_pokok = 0;
                    if (comboBoxJenisBunga.getSelectedItem().equals("Efektif")) {
                        hapus_output();
                        for (int i=1; i <= bulan; i++){
                            besar_bunga = Pinjaman * (bunga/100)/bulan;
                            angsuran = angsuran_pokok + besar_bunga;
                            Pinjaman = Pinjaman - angsuran_pokok;
                            textBulan.append("      " +i +"\n");
                            tampilkan_hasil();
                            total_bunga = total_bunga + besar_bunga;
                            total_angsuran = total_angsuran + angsuran;
                            total_pokok = total_pokok + angsuran_pokok;
                        }
                        tampilkan_total_hasil();
                    } else if (comboBoxJenisBunga.getSelectedItem().equals("Flat")) {
                        hapus_output();
                        besar_bunga = (Pinjaman * bunga/100)/bulan;
                        angsuran = angsuran_pokok + besar_bunga;
                        for (int i=1; i <= bulan; i++) {
                            Pinjaman = Pinjaman - angsuran_pokok;
                            textBulan.append("      " +i +"\n");
                            tampilkan_hasil();
                        }
                        total_pokok = angsuran_pokok * bulan;
                        total_bunga = besar_bunga * bulan;
                        total_angsuran = angsuran * bulan;
                        tampilkan_total_hasil();
                    }
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapus_input();
                hapus_output();
            }
        });
    }
}

//String value = (String)comboBoxLamaAngsuran.getSelectedItem();
//bulan = Integer.parseInt(value);

