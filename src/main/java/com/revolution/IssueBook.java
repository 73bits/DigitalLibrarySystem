package com.revolution;

import utilities.DBConnect;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class IssueBook extends javax.swing.JFrame {

	private final String dash = "...........................................................";
	private String bookName, memberName, bookId, memberId, SQL;
	private final Connection con = DBConnect.getConnection();
	private PreparedStatement pst;
	private ResultSet rst;
	private Date issuedate, duedate;

	public IssueBook() {
		initComponents();
	}

	public boolean getBookDetails() {
		bookId = txt_bookId.getText();
		SQL = "SELECT * FROM book_details WHERE book_id = " + bookId + "";
		try {
			pst = con.prepareStatement(SQL);
			rst = pst.executeQuery();
			if (rst.next()) {
				lbl_bookId.setText(rst.getString("book_id"));
				lbl_bookName.setText(rst.getString("book_name"));
				lbl_author.setText(rst.getString("author"));
				lbl_quantity.setText(rst.getString("quantity"));
				lbl_error1.setText("Book Found .");
				jPanel7.setBackground(new Color(0, 51, 153));
				return true;
			} else {
				lbl_bookId.setText(dash);
				lbl_bookName.setText(dash);
				lbl_author.setText(dash);
				lbl_quantity.setText(dash);
				lbl_error1.setText("Book Not Found .");
				jPanel7.setBackground(Color.RED);
				txt_bookId.requestFocus();
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public boolean getMemberDetails() {
		memberId = txt_memberId.getText();
		SQL = "SELECT * FROM members_details WHERE member_id = " + memberId + "";
		try {
			pst = con.prepareStatement(SQL);
			rst = pst.executeQuery();
			if (rst.next()) {
				lbl_memberId.setText(rst.getString("member_id"));
				lbl_memberName.setText(rst.getString("name"));
				lbl_course.setText(rst.getString("course"));
				lbl_session.setText(rst.getString("session"));
				lbl_error2.setText("Member Found .");
				jPanel11.setBackground(new Color(0, 51, 153));
				return true;
			} else {
				lbl_memberId.setText(dash);
				lbl_memberName.setText(dash);
				lbl_course.setText(dash);
				lbl_session.setText(dash);
				lbl_error2.setText("Member Not Found .");
				txt_memberId.requestFocus();
				jPanel11.setBackground(Color.RED);
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return true;
	}

	public boolean issueBook() {
		bookId = txt_bookId.getText();
		memberId = txt_memberId.getText();
		bookName = lbl_bookName.getText();
		memberName = lbl_memberName.getText();
		issuedate = jDateChooser1.getDate();
		java.sql.Date IssueDate = new java.sql.Date(issuedate.getTime());
		duedate = jDateChooser2.getDate();
		java.sql.Date DueDate = new java.sql.Date(duedate.getTime());
		SQL = "INSERT INTO issue_book_details (book_id, book_name, member_id, member_name, issue_date, due_date, status)"
				+ "VALUES(" + bookId + ",'" + bookName + "'," + memberId + ",'" + memberName + "','" + IssueDate + "','" + DueDate + "','pending')";
		try {
			pst = con.prepareStatement(SQL);
			if (pst.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(this, "Book Issued");
				return true;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
		return false;
	}

	public boolean validateBookId() {
		if (!txt_bookId.getText().matches("[0-9]+")) {
			lbl_error1.setText("Enter Valid Book ID .");
			jPanel7.setBackground(Color.RED);
			txt_bookId.requestFocus();
			return false;
		}
		return true;
	}

	public boolean validateMemberId() {
		if (!txt_memberId.getText().matches("[0-9]+")) {
			lbl_error2.setText("Enter Valid Member ID .");
			jPanel11.setBackground(Color.RED);
			txt_memberId.requestFocus();
			return false;
		}
		return true;
	}

	public boolean validateDates() {
		issuedate = jDateChooser1.getDate();
		duedate = jDateChooser2.getDate();
		if (issuedate == null || duedate == null) {
			JOptionPane.showMessageDialog(this, "Enter valid date");
			return false;
		}
		if (issuedate.compareTo(duedate) > 0) {
			JOptionPane.showMessageDialog(this, "Enter valid due date");
			return false;
		}
		return true;
	}

	public boolean isAlreadyIssued() {
		bookId = txt_bookId.getText();
		memberId = txt_memberId.getText();
		SQL = "SELECT * FROM issue_book_details WHERE book_id = '" + bookId + "' AND member_id = '" + memberId + "' AND status = 'pending'";
		try {
			pst = con.prepareStatement(SQL);
			rst = pst.executeQuery();
			if (rst.next()) {
				JOptionPane.showMessageDialog(this, "Book already issued");
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public void updateBookCount() {
		bookId = txt_bookId.getText();
		SQL = "UPDATE book_details SET quantity = quantity - 1 WHERE book_id = " + bookId + "";
		try {
			pst = con.prepareStatement(SQL);
			int rowCount = pst.executeUpdate();
			if (rowCount > 0) {
				JOptionPane.showMessageDialog(this, "Book Count Updated");
				int initialCount = Integer.parseInt(lbl_quantity.getText());
				lbl_quantity.setText(Integer.toString(initialCount - 1));
			}
		} catch (HeadlessException | NumberFormatException | SQLException e) {
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lbl_error1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_session = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbl_memberId = new javax.swing.JLabel();
        lbl_memberName = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbl_error2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txt_bookId = new javax.swing.JFormattedTextField();
        txt_memberId = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Revolution Library - Issue Book");
        setName("issuebook"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Cantarell", 0, 14)); // NOI18N
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

        jPanel2.setBackground(new java.awt.Color(0, 128, 128));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/book-60.png"))); // NOI18N
        jLabel1.setText("  Book Details");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 128, 128));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 460, 20));

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quantity");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 120, -1));

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText(":");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, 20, -1));

        jLabel4.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Name ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 120, -1));

        jLabel5.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Author");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 120, -1));

        lbl_quantity.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        lbl_quantity.setText("...........................................................");
        jPanel2.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 270, 20));

        jLabel7.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText(":");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 20, -1));

        jLabel8.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText(":");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 20, -1));

        jLabel9.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(":");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 20, -1));

        jLabel10.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Book ID ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 120, -1));

        lbl_bookId.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        lbl_bookId.setText("...........................................................");
        jPanel2.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 270, 20));

        lbl_bookName.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        lbl_bookName.setText("...........................................................");
        jPanel2.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 270, 20));

        lbl_author.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        lbl_author.setText("...........................................................");
        jPanel2.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 270, 20));

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(0, 204, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 800, 290, 40));

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 204, 204));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 800, 290, 40));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 480, 40));

        lbl_error1.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lbl_error1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_error1.setText("Enter Book Details .");
        jPanel7.add(lbl_error1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 440, 40));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 740, 480, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 855));

        jLabel14.setFont(new java.awt.Font("Cantarell", 1, 30)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_other/issue-book-black-60.png"))); // NOI18N
        jLabel14.setText("Issue Book");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 130, -1, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 190, -1, -1));

        jLabel6.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel6.setText("Book ID");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 330, 110, -1));

        jLabel11.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel11.setText("Member ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 390, 110, -1));

        jLabel24.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel24.setText(":");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 330, 20, -1));

        jLabel25.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel25.setText(":");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 390, 20, -1));

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Cantarell", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Issue Book");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 650, 340, 50));

        jLabel12.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel12.setText("Issue Date      :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 450, -1, -1));

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setDateFormatString("M/dd/yyyy");
        jDateChooser1.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 450, 450, -1));

        jLabel26.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel26.setText("Due Date        :");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 510, -1, -1));

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setDateFormatString("M/dd/yyyy");
        jDateChooser2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 510, 450, -1));

        jPanel4.setBackground(new java.awt.Color(0, 128, 128));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 128, 128));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 460, -1));

        jLabel15.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Session");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 110, -1));

        jLabel16.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText(":");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, 20, -1));

        jLabel17.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Name ");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel18.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Course");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 110, -1));

        lbl_session.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_session.setForeground(new java.awt.Color(255, 255, 255));
        lbl_session.setText("...........................................................");
        jPanel4.add(lbl_session, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 270, 20));

        jLabel20.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText(":");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 20, -1));

        jLabel21.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText(":");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 20, -1));

        jLabel22.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText(":");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 20, -1));

        jLabel23.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Member ID ");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 110, -1));

        lbl_memberId.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_memberId.setForeground(new java.awt.Color(255, 255, 255));
        lbl_memberId.setText("...........................................................");
        jPanel4.add(lbl_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, 270, 20));

        lbl_memberName.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_memberName.setForeground(new java.awt.Color(255, 255, 255));
        lbl_memberName.setText("...........................................................");
        jPanel4.add(lbl_memberName, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 270, 20));

        lbl_course.setFont(new java.awt.Font("Cantarell", 1, 16)); // NOI18N
        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        lbl_course.setText("...........................................................");
        jPanel4.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 270, 20));

        jLabel19.setFont(new java.awt.Font("Cantarell", 1, 22)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/author-60.png"))); // NOI18N
        jLabel19.setText("  Member Details");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jPanel11.setBackground(new java.awt.Color(0, 204, 204));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_error2.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lbl_error2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_error2.setText("Enter Member Details .");
        jPanel11.add(lbl_error2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 440, 40));

        jPanel4.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 740, 480, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 500, 855));

        jButton3.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton3.setText("<-");
        jButton3.setToolTipText("Get Member Details");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 390, 50, 27));

        jButton5.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton5.setText("<-");
        jButton5.setToolTipText("Get Book Details");
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1580, 330, 50, 27));
        jButton5.getAccessibleContext().setAccessibleDescription("");

        txt_bookId.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jPanel1.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 330, 400, -1));

        txt_memberId.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jPanel1.add(txt_memberId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 390, 400, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1696, 876));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		HomePage home = new HomePage();
		home.setVisible(true);
		dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		if (validateBookId() == false || validateMemberId() == false) {
			return;
		}
		if (getBookDetails() == false || getMemberDetails() == false) {
			return;
		}
		if (isAlreadyIssued() == true) {
			return;
		}
		if (Integer.parseInt(lbl_quantity.getText()) <= 0) {
			JOptionPane.showMessageDialog(this, "Book Unavailable");
			return;
		}
		if (validateDates() == false) {
			return;
		}
		if (issueBook() == false) {
			return;
		}
		updateBookCount();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		if (validateBookId()) {
			getBookDetails();
		}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		if (validateMemberId() == true) {
			getMemberDetails();
		}
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
			java.util.logging.Logger.getLogger(IssueBook.class
					.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		}
		java.awt.EventQueue.invokeLater(() -> {
			new IssueBook().setVisible(true);
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_error1;
    private javax.swing.JLabel lbl_error2;
    private javax.swing.JLabel lbl_memberId;
    private javax.swing.JLabel lbl_memberName;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_session;
    private javax.swing.JFormattedTextField txt_bookId;
    private javax.swing.JFormattedTextField txt_memberId;
    // End of variables declaration//GEN-END:variables
}
