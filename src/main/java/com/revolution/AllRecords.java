package com.revolution;

import utilities.DBConnect;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class AllRecords extends javax.swing.JFrame {

	private String id, bookId, memberId, memberName, bookName, issueDate, dueDate, status, SQL;
	private Date issuedate, duedate, date1, date2;
	private final Connection con = DBConnect.getConnection();
	private Statement smt;
	private ResultSet rst;
	private DefaultTableModel model;

	public AllRecords() {
		initComponents();
		viewAllRecord();
	}

	public void clearTable() {
		model = (DefaultTableModel) jTable1.getModel();
		model.setRowCount(0);

	}

	public boolean validateDates() {
		issuedate = jDateChooser1.getDate();
		duedate = jDateChooser2.getDate();
		if (issuedate == null || duedate == null) {
			JOptionPane.showMessageDialog(this, "Enter dates");
			return false;
		}
		if (issuedate.compareTo(duedate) > 0) {
			JOptionPane.showMessageDialog(this, "Select the valid dates");
			return false;
		}
		return true;
	}

	public void viewAllRecord() {
		clearTable();
		SQL = "SELECT * FROM issue_book_details";
		try {
			smt = con.createStatement();
			rst = smt.executeQuery(SQL);
			while (rst.next()) {
				id = rst.getString("id");
				bookId = rst.getString("book_id");
				memberId = rst.getString("member_id");
				memberName = rst.getString("member_name");
				bookName = rst.getString("book_name");
				issueDate = rst.getString("issue_date");
				dueDate = rst.getString("due_date");
				status = rst.getString("status");

				Object[] obj = {id, memberId, memberName, bookId, bookName, issueDate, dueDate, status};
				model = (DefaultTableModel) jTable1.getModel();
				model.addRow(obj);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void viewSelectedRecord() {
		clearTable();
		date1 = jDateChooser1.getDate();
		issuedate = new java.sql.Date(date1.getTime());
		date2 = jDateChooser2.getDate();
		duedate = new java.sql.Date(date2.getTime());
		SQL = "SELECT * FROM issue_book_details WHERE issue_date BETWEEN '" + issuedate + "' AND '" + duedate + "'";
		try {
			smt = con.createStatement();
			rst = smt.executeQuery(SQL);
			while (rst.next()) {
				id = rst.getString("id");
				bookId = rst.getString("book_id");
				memberId = rst.getString("member_id");
				memberName = rst.getString("member_name");
				bookName = rst.getString("book_name");
				issueDate = rst.getString("issue_date");
				dueDate = rst.getString("due_date");
				status = rst.getString("status");

				Object[] obj = {id, memberId, memberName, bookId, bookName, issueDate, dueDate, status};
				model = (DefaultTableModel) jTable1.getModel();
				model.addRow(obj);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Revolution Library - All Records");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));
        jPanel1.setName("allrecords"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/back_16.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 10, 40, -1));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/records-60.png"))); // NOI18N
        jLabel1.setText("All Records");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 128, 128));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, 360, 20));

        jLabel12.setFont(new java.awt.Font("Cantarell", 1, 22)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("From Issue Date");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        jDateChooser1.setBackground(new java.awt.Color(0, 128, 128));
        jDateChooser1.setDateFormatString("MMM dd, y");
        jDateChooser1.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 220, 30));

        jLabel26.setFont(new java.awt.Font("Cantarell", 1, 22)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("To");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, -1, -1));

        jDateChooser2.setBackground(new java.awt.Color(0, 128, 128));
        jDateChooser2.setDateFormatString("MMM dd, y");
        jDateChooser2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, 220, 30));

        jButton3.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButton3.setText("All");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 150, 130, 30));

        jButton4.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButton4.setText("Search");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 150, 130, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1700, 220));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Member ID", "Member Name", "Book Id", "Book Name", "Issue Date", "Due Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setRowHeight(25);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(338);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(450);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(136);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 1530, 550));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 1700, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		HomePage home = new HomePage();
		home.setVisible(true);
		dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		if (validateDates() == true)
			viewSelectedRecord();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		viewAllRecord();
    }//GEN-LAST:event_jButton3ActionPerformed

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AllRecords.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(() -> {
			new AllRecords().setVisible(true);
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
