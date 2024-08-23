package com.revolution;

import utilities.DBConnect;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public final class Books extends javax.swing.JFrame {

	private String bookName, author, bookId, quantity, SQL;
	private final Connection con = DBConnect.getConnection();
	private Statement smt;
	private ResultSet rst;
	private PreparedStatement pst;
	private DefaultTableModel model;

	public Books() {
		initComponents();
		setBookDetailsToTable();
	}

	public void setBookDetailsToTable() {
		SQL = "SELECT * FROM book_details ORDER BY book_id";
		try {
			smt = con.createStatement();
			rst = smt.executeQuery(SQL);
			while (rst.next()) {
				bookId = rst.getString("book_id");
				bookName = rst.getString("book_name");
				author = rst.getString("author");
				quantity = rst.getString("quantity");

				Object[] obj = {bookId, bookName, author, quantity};
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

	public boolean addBook() {
		boolean isAdded = false;
		bookId = txt_bookId.getText();
		bookName = txt_bookName.getText();
		author = txt_authorName.getText();
		quantity = txt_quantity.getText();
		SQL = "INSERT INTO book_details VALUES('" + bookId + "','" + bookName + "','" + author + "'," + quantity + ")";
		try {
			pst = con.prepareStatement(SQL);
			int rowCount = pst.executeUpdate();
			if (rowCount > 0) {
				isAdded = true;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		return isAdded;
	}

	public boolean updateBook() {
		boolean isUpdated = false;
		bookId = txt_bookId.getText();
		bookName = txt_bookName.getText();
		author = txt_authorName.getText();
		quantity = txt_quantity.getText();
		SQL = "UPDATE book_details SET book_name = '" + bookName + "', author = '" + author + "',"
				+ "quantity = '" + quantity + "' where book_id = '" + bookId + "'";
		try {
			pst = con.prepareStatement(SQL);
			int rowCount = pst.executeUpdate();
			isUpdated = rowCount > 0;
		} catch (SQLException e) {
			System.err.println(e);
		}
		return isUpdated;
	}

	public boolean deleteBook() {
		boolean isDeleted = false;
		bookId = txt_bookId.getText();
		SQL = "DELETE FROM book_details WHERE book_id = '" + bookId + "'";
		try {
			pst = con.prepareStatement(SQL);
			int rowCount = pst.executeUpdate();
			if (rowCount > 0) {
				isDeleted = true;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		return isDeleted;
	}

	public boolean validateDetails() {
		bookId = txt_bookId.getText();
		bookName = txt_bookName.getText();
		author = txt_authorName.getText();
		quantity = txt_quantity.getText();

		if (!bookId.matches("[0-9]+") || bookId.matches("^0+$")) {
			JOptionPane.showMessageDialog(this, "Enter valid book ID");
			txt_bookId.requestFocus();
			return false;
		}

		if (bookName.equals("") || bookName.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(this, "Enter valid book name");
			txt_bookName.requestFocus();
			return false;
		}

		if (author.equals("") || author.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(this, "Enter valid author name");
			txt_authorName.requestFocus();
			return false;
		}

		if (!quantity.matches("[0-9]+") || quantity.matches("^0+$")) {
			JOptionPane.showMessageDialog(this, "Enter valid book quantity");
			txt_quantity.requestFocus();
			return false;
		}

		return true;
	}

	public boolean bookAlreadyExist() {
		bookName = txt_bookName.getText();
		SQL = "SELECT * FROM book_details WHERE book_name = '" + bookName + "'";
		try {
			pst = con.prepareStatement(SQL);
			rst = pst.executeQuery();
			if (rst.next()) {
				JOptionPane.showMessageDialog(this, "Book already exist");
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return true;
		}
		return false;
	}

	public boolean bookIdAlreadyExist() {
		bookId = txt_bookId.getText();
		SQL = "SELECT * FROM book_details WHERE book_id = '" + bookId + "'";
		try {
			pst = con.prepareStatement(SQL);
			rst = pst.executeQuery();
			if (rst.next()) {
				JOptionPane.showMessageDialog(this, "Can't have dublicate book Id");
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return true;
		}
		return false;
	}

	public void searchByID() {
		SQL = "SELECT * FROM book_details WHERE book_id::varchar(255) ~ '" + bookId + "'";
		try {
			smt = con.createStatement();
			bookId = txt_searchBox.getText();
			rst = smt.executeQuery(SQL);
			clearTable();
			while (rst.next()) {
				bookId = rst.getString("book_id");
				bookName = rst.getString("book_name");
				author = rst.getString("author");
				quantity = rst.getString("quantity");
				Object[] obj = {bookId, bookName, author, quantity};
				model = (DefaultTableModel) tbl_bookDetails.getModel();
				model.addRow(obj);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public void searchByName() {
		SQL = "SELECT * FROM book_details WHERE book_name ~* '" + bookName + "'";
		try {
			smt = con.createStatement();
			bookName = txt_searchBox.getText();
			rst = smt.executeQuery(SQL);
			clearTable();
			while (rst.next()) {
				bookId = rst.getString("book_id");
				bookName = rst.getString("book_name");
				author = rst.getString("author");
				quantity = rst.getString("quantity");
				Object[] obj = {bookId, bookName, author, quantity};
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

        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txt_bookId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_bookName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_authorName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_quantity = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txt_searchBox = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Revolution Library - Books");
        setName("books"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(255, 51, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/back_16.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1640, 10, 40, -1));

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 30)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_other/book-black-60.png"))); // NOI18N
        jLabel1.setText("Books");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 20, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 90, 390, -1));

        tbl_bookDetails.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            tbl_bookDetails.getColumnModel().getColumn(2).setPreferredWidth(320);
            tbl_bookDetails.getColumnModel().getColumn(3).setResizable(false);
            tbl_bookDetails.getColumnModel().getColumn(3).setPreferredWidth(134);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, 980, 590));

        jPanel1.setBackground(new java.awt.Color(0, 128, 128));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_bookId.setBackground(new java.awt.Color(0, 128, 128));
        txt_bookId.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        txt_bookId.setForeground(new java.awt.Color(255, 255, 255));
        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 360, -1));

        jLabel6.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Book ID");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 360, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/book-id-60.png"))); // NOI18N
        jLabel14.setText("jLabel13");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 60, -1));

        txt_bookName.setBackground(new java.awt.Color(0, 128, 128));
        txt_bookName.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        txt_bookName.setForeground(new java.awt.Color(255, 255, 255));
        txt_bookName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txt_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, 360, -1));

        jLabel7.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Book Name");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 360, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/book-60.png"))); // NOI18N
        jLabel15.setText("jLabel13");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 60, -1));

        txt_authorName.setBackground(new java.awt.Color(0, 128, 128));
        txt_authorName.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        txt_authorName.setForeground(new java.awt.Color(255, 255, 255));
        txt_authorName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txt_authorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 360, -1));

        jLabel8.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Author Name");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 360, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/author-60.png"))); // NOI18N
        jLabel16.setText("jLabel13");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 60, -1));

        jLabel9.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Quantity");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 360, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/quantity-60.png"))); // NOI18N
        jLabel17.setText("jLabel13");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 500, 60, -1));

        txt_quantity.setBackground(new java.awt.Color(0, 128, 128));
        txt_quantity.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        txt_quantity.setForeground(new java.awt.Color(255, 255, 255));
        txt_quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        jPanel1.add(txt_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 360, -1));

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

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 570, 855));

        txt_searchBox.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
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

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
		int rowNo = tbl_bookDetails.getSelectedRow();
		TableModel tablemodel = tbl_bookDetails.getModel();
		txt_bookId.setText(tablemodel.getValueAt(rowNo, 0).toString());
		txt_bookName.setText(tablemodel.getValueAt(rowNo, 1).toString());
		txt_authorName.setText(tablemodel.getValueAt(rowNo, 2).toString());
		txt_quantity.setText(tablemodel.getValueAt(rowNo, 3).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		HomePage home = new HomePage();
		home.setVisible(true);
		dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		if (validateDetails() == false) {
			return;
		}
		if (bookIdAlreadyExist() == true || bookAlreadyExist() == true) {
			return;
		}
		if (addBook() == true) {
			JOptionPane.showMessageDialog(this, "Book Added");
			clearTable();
			setBookDetailsToTable();
		} else {
			JOptionPane.showMessageDialog(this, "Book Not Added");
		}

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		if (validateDetails() == true) {
			if (updateBook() == true) {
				JOptionPane.showMessageDialog(this, "Book Updated");
				clearTable();
				setBookDetailsToTable();
			} else {
				JOptionPane.showMessageDialog(this, "Book Not Updated");
			}
		}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		if (validateDetails() == true) {
			if (deleteBook() == true) {
				JOptionPane.showMessageDialog(this, "Book Deleted");
				clearTable();
				setBookDetailsToTable();
			} else {
				JOptionPane.showMessageDialog(this, "Book Not Deleted");
			}
		}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
		clearTable();
		txt_searchBox.setText("");
		setBookDetailsToTable();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
		if (txt_searchBox.getText().equals("")) {
			clearTable();
			setBookDetailsToTable();
		} else if (txt_searchBox.getText().matches("[0-9]+")) {
			searchByID();
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
			java.util.logging.Logger.getLogger(Books.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(() -> {
			new Books().setVisible(true);
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_bookDetails;
    private javax.swing.JTextField txt_authorName;
    private javax.swing.JTextField txt_bookId;
    private javax.swing.JTextField txt_bookName;
    private javax.swing.JTextField txt_quantity;
    private javax.swing.JTextField txt_searchBox;
    // End of variables declaration//GEN-END:variables
}
