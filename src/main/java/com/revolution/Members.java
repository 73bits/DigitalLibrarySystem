package com.revolution;

import utilities.DBConnect;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public final class Members extends javax.swing.JFrame {

	private String memberId, memberName, course, session, contact, SQL;
	private final Connection con = DBConnect.getConnection();
	private Statement smt;
	private ResultSet rst;
	private PreparedStatement pst;
	private DefaultTableModel model;

	public Members() {
		initComponents();
		setMemberDetailsToTable();
	}

	public void setMemberDetailsToTable() {
		SQL = "SELECT * FROM members_details order by member_id";
		try {
			smt = con.createStatement();
			rst = smt.executeQuery(SQL);
			while (rst.next()) {
				memberId = rst.getString("member_id");
				memberName = rst.getString("name");
				course = rst.getString("course");
				session = rst.getString("session");
				contact = rst.getString("contact");

				Object[] obj = {memberId, memberName, course, session, contact};
				model = (DefaultTableModel) tbl_bookDetails.getModel();
				model.addRow(obj);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void clearTable() {
		model = (DefaultTableModel) tbl_bookDetails.getModel();
		model.setRowCount(0);
	}

	public boolean addMembers() {
		boolean isAdded = true;
		memberId = txt_memberId.getText();
		memberName = txt_memberName.getText();
		course = combo_courseName.getSelectedItem().toString();
		session = combo_Session.getSelectedItem().toString();
		contact = txt_contact.getText();
		SQL = "INSERT INTO members_details VALUES('" + memberId + "','" + memberName + "','" + course + "','" + session + "','" + contact + "')";
		try {
			pst = con.prepareStatement(SQL);
			int rowCount = pst.executeUpdate();
			isAdded = rowCount > 0;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		return isAdded;
	}

	public boolean updateMembers() {
		boolean isUpdated = true;
		memberId = txt_memberId.getText();
		memberName = txt_memberName.getText();
		course = combo_courseName.getSelectedItem().toString();
		session = combo_Session.getSelectedItem().toString();
		contact = txt_contact.getText();
		SQL = "UPDATE members_details SET name = '" + memberName + "',"
				+ "course = '" + course + "', session = '" + session + "', contact = '" + contact + "' WHERE member_id = " + memberId + "";
		try {
			pst = con.prepareStatement(SQL);
			int rowCount = pst.executeUpdate();
			isUpdated = rowCount > 0;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return isUpdated;
	}

	public boolean deletedMember() {
		int count = 0;
		boolean isDeleted = true;
		memberId = txt_memberId.getText();
		SQL = "SELECT * FROM issue_book_details WHERE member_id = " + memberId + " AND status = 'pending'";
		try {
			smt = con.createStatement();
			rst = smt.executeQuery(SQL);
			while (rst.next()) {
				count++;
			}
			if (count > 0) {
				JOptionPane.showMessageDialog(this, "User has some books that is not yet returned.", "Error", 2);
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		SQL = "DELETE FROM members_details WHERE member_id = '" + memberId + "'";
		try {
			pst = con.prepareStatement(SQL);
			int rowCount = pst.executeUpdate();
			isDeleted = rowCount > 0;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return isDeleted;
	}

	public boolean validateDetails() {
		memberId = txt_memberId.getText();
		memberName = txt_memberName.getText();
		course = combo_courseName.getSelectedItem().toString();
		session = combo_Session.getSelectedItem().toString();

		if (!memberId.matches("[0-9]+") || memberId.matches("^0+$") || memberId.equals("")) {
			JOptionPane.showMessageDialog(this, "Enter valid member ID");
			txt_memberId.requestFocus();
			return false;
		}

		if (memberName.equals("") || memberName.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(this, "Enter valid member name");
			txt_memberName.requestFocus();
			return false;
		}
		return true;
	}

	public boolean validateMemberId() {
		memberId = txt_memberId.getText();
		SQL = "SELECT * FROM member_details WHERE member_id = " + memberId + "";
		try {
			pst = con.prepareStatement(SQL);
			rst = pst.executeQuery();
			if (rst.next()) {
				JOptionPane.showMessageDialog(this, "Can't have dublicate member Id");
				return true;
			}
		} catch (HeadlessException | SQLException e) {
			System.err.println(e);
			return true;
		}
		return true;
	}

	public void searchById() {
		SQL = "SELECT * FROM members_details WHERE member_id::varchar(255) ~ '" + memberId + "'";
		try {
			smt = con.createStatement();
			memberId = txt_searchBox.getText();
			rst = smt.executeQuery(SQL);
			clearTable();
			while (rst.next()) {
				memberId = rst.getString("member_id");
				memberName = rst.getString("name");
				course = rst.getString("course");
				session = rst.getString("session");
				Object[] obj = {memberId, memberName, course, session};
				model = (DefaultTableModel) tbl_bookDetails.getModel();
				model.addRow(obj);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void searchByName() {
		SQL = "SELECT * FROM members_details WHERE name ~* '" + memberName + "'";
		try {
			smt = con.createStatement();
			memberName = txt_searchBox.getText();
			rst = smt.executeQuery(SQL);
			clearTable();
			while (rst.next()) {
				memberId = rst.getString("member_id");
				memberName = rst.getString("name");
				course = rst.getString("course");
				session = rst.getString("session");
				Object[] obj = {memberId, memberName, course, session};
				model = (DefaultTableModel) tbl_bookDetails.getModel();
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
        txt_memberName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_memberId = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        combo_Session = new javax.swing.JComboBox<>();
        combo_courseName = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new javax.swing.JTable();
        txt_searchBox = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Revolution Library - Members");
        setName("members"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_memberName.setBackground(new java.awt.Color(0, 128, 128));
        txt_memberName.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        txt_memberName.setForeground(new java.awt.Color(255, 255, 255));
        txt_memberName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txt_memberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 360, -1));

        jLabel7.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Member Name");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 360, -1));

        txt_memberId.setBackground(new java.awt.Color(0, 128, 128));
        txt_memberId.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        txt_memberId.setForeground(new java.awt.Color(255, 255, 255));
        txt_memberId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txt_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 360, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/author-60.png"))); // NOI18N
        jLabel15.setText("jLabel13");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 60, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/calender-60.png"))); // NOI18N
        jLabel17.setText("jLabel13");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 60, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/id-60.png"))); // NOI18N
        jLabel14.setText("jLabel13");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 60, -1));

        jLabel9.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Session");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 360, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/course-60.png"))); // NOI18N
        jLabel16.setText("jLabel13");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 60, -1));

        jLabel6.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Member ID");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 360, -1));

        jLabel8.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Select Course");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 360, -1));

        combo_Session.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        combo_Session.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020 - 23", "2021 - 24", "2022 - 25", "2023 - 26", "2024 - 27", "Other" }));
        jPanel1.add(combo_Session, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 360, -1));

        combo_courseName.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        combo_courseName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MCA", "MSc.IT", "MBA", "M.Com", "Other" }));
        jPanel1.add(combo_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 360, -1));

        jButton3.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 660, 160, 40));

        jButton4.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButton4.setText("ADD");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 160, 40));

        jButton5.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButton5.setText("UPDATE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, 160, 40));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/contact_60.png"))); // NOI18N
        jLabel18.setText("jLabel13");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 60, -1));

        jLabel10.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phone Number");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 360, -1));

        txt_contact.setBackground(new java.awt.Color(0, 128, 128));
        txt_contact.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        txt_contact.setForeground(new java.awt.Color(255, 255, 255));
        txt_contact.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 540, 360, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 570, 855));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 51, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/back_16.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 10, 40, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 90, 390, -1));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 30)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_other/author-black-60.png"))); // NOI18N
        jLabel1.setText("Members");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 200, -1));

        tbl_bookDetails.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Member ID", "Name", "Course", "Session", "Contact"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_bookDetails.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbl_bookDetails.setRowHeight(25);
        tbl_bookDetails.setSelectionBackground(new java.awt.Color(105, 117, 166));
        tbl_bookDetails.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_bookDetails.setShowGrid(false);
        tbl_bookDetails.getTableHeader().setReorderingAllowed(false);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bookDetails);
        if (tbl_bookDetails.getColumnModel().getColumnCount() > 0) {
            tbl_bookDetails.getColumnModel().getColumn(0).setResizable(false);
            tbl_bookDetails.getColumnModel().getColumn(0).setPreferredWidth(120);
            tbl_bookDetails.getColumnModel().getColumn(1).setResizable(false);
            tbl_bookDetails.getColumnModel().getColumn(1).setPreferredWidth(400);
            tbl_bookDetails.getColumnModel().getColumn(2).setResizable(false);
            tbl_bookDetails.getColumnModel().getColumn(2).setPreferredWidth(270);
            tbl_bookDetails.getColumnModel().getColumn(3).setResizable(false);
            tbl_bookDetails.getColumnModel().getColumn(3).setPreferredWidth(118);
            tbl_bookDetails.getColumnModel().getColumn(4).setResizable(false);
            tbl_bookDetails.getColumnModel().getColumn(4).setPreferredWidth(165);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 1080, 590));

        txt_searchBox.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        jPanel2.add(txt_searchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 130, 350, 30));

        jButton7.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1570, 130, -1, 30));

        jButton8.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jButton8.setText("Search");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 130, -1, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1700, 876));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		HomePage home = new HomePage();
		home.setVisible(true);
		dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		if (validateDetails() == false) {
			return;
		}
		if (deletedMember() == true) {
			JOptionPane.showMessageDialog(this, "Member Deleted");
			clearTable();
			setMemberDetailsToTable();
		} else {
			JOptionPane.showMessageDialog(this, "Member Not Deleted");
		}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		if (validateDetails() == false) {
			return;
		}
		if (addMembers() == true) {
			clearTable();
			setMemberDetailsToTable();
		} else
			JOptionPane.showInternalMessageDialog(this, "No New Member Added");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		if (validateDetails() == false) {
			return;
		}
		if (updateMembers() == true) {
			JOptionPane.showMessageDialog(this, "Member Details Updated");
			clearTable();
			setMemberDetailsToTable();
		}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
		int rowNo = tbl_bookDetails.getSelectedRow();
		TableModel model = tbl_bookDetails.getModel();
		txt_memberId.setText(model.getValueAt(rowNo, 0).toString());
		txt_memberName.setText(model.getValueAt(rowNo, 1).toString());
		combo_courseName.setSelectedItem(model.getValueAt(rowNo, 2));
		combo_Session.setSelectedItem(model.getValueAt(rowNo, 3));
		txt_contact.setText(model.getValueAt(rowNo, 4).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
		clearTable();
		txt_searchBox.setText("");
		setMemberDetailsToTable();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
		if (txt_searchBox.getText().equals("")) {
			clearTable();
			setMemberDetailsToTable();
		} else if (txt_searchBox.getText().matches("[0-9]+")) {
			searchById();
		} else {
			searchByName();
		}
    }//GEN-LAST:event_jButton8ActionPerformed

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Members.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(() -> {
			new Members().setVisible(true);
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_Session;
    private javax.swing.JComboBox<String> combo_courseName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_bookDetails;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_memberId;
    private javax.swing.JTextField txt_memberName;
    private javax.swing.JTextField txt_searchBox;
    // End of variables declaration//GEN-END:variables
}
