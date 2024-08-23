package com.revolution;

import utilities.DBConnect;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class HomePage extends javax.swing.JFrame {

        private String bookName, bookId, author, memberName, memberID, course, session;
        private final Connection con = DBConnect.getConnection();
        private Statement smt;
        private ResultSet rst;
        private DefaultTableModel model;
        private final Color mouseEnterColor = new Color(0, 0, 0);
        private final Color mouseExitColor = new Color(51, 51, 51);
        private Date todaysdate;

        public HomePage() {
                initComponents();
                setBookDetailsToTable("book_id");
                setMemberDetailsToTable("member_id");
                setDataToCard();
        }

        public void clearBookTable() {
                model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
        }

        public void clearMemberTable() {
                model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);
        }

        public void setBookDetailsToTable(String sortBy) {
                clearBookTable();
                try {
                        smt = con.createStatement();
                        rst = smt.executeQuery("SELECT * FROM book_details ORDER BY " + sortBy + "");
                        while (rst.next()) {
                                bookName = rst.getString("book_name");
                                bookId = rst.getString("book_id");
                                author = rst.getString("author");
                                int Quantity = rst.getInt("quantity");

                                Object[] obj = {bookName, bookId, author, Quantity};
                                model = (DefaultTableModel) jTable1.getModel();
                                model.addRow(obj);
                        }
                } catch (SQLException e) {
                        System.err.println(e);
                }
        }

        public void setMemberDetailsToTable(String sortBy) {
                clearMemberTable();
                try {
                        smt = con.createStatement();
                        rst = smt.executeQuery("SELECT * FROM members_details ORDER BY " + sortBy + "");
                        while (rst.next()) {
                                memberName = rst.getString("name");
                                memberID = rst.getString("member_id");
                                course = rst.getString("course");
                                session = rst.getString("session");

                                Object[] obj = {memberName, memberID, course, session};
                                model = (DefaultTableModel) jTable2.getModel();
                                model.addRow(obj);
                        }
                } catch (SQLException e) {
                        System.err.println(e);
                }
        }

        public void setDataToCard() {
                long lDate = System.currentTimeMillis();
                todaysdate = new Date(lDate);

                try {
                        smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        rst = smt.executeQuery("SELECT * FROM book_details");
                        rst.last();
                        lbl_bookCount.setText(Integer.toString(rst.getRow()));
                } catch (SQLException e) {
                        System.err.println(e);
                }

                try {
                        smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        rst = smt.executeQuery("SELECT * FROM members_details");
                        rst.last();
                        lbl_memberCount.setText(Integer.toString(rst.getRow()));
                } catch (SQLException e) {
                        System.err.println(e);
                }

                try {
                        smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        rst = smt.executeQuery("SELECT * FROM issue_book_details WHERE status = 'pending'");
                        rst.last();
                        lbl_issedBookCount.setText(Integer.toString(rst.getRow()));
                } catch (SQLException e) {
                        System.err.println(e);
                }

                try {
                        smt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        rst = smt.executeQuery("SELECT * FROM issue_book_details WHERE due_date < '" + todaysdate + "' AND status = 'pending'");
                        rst.last();
                        lbl_defaulterCount.setText(Integer.toString(rst.getRow()));
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

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lbl_bookCount = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lbl_memberCount = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lbl_issedBookCount = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lbl_defaulterCount = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Revolution Library - Dashboard");
        setName("home"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 128, 128));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 5, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/hamburger-menu-60.png"))); // NOI18N
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 60));

        jLabel3.setFont(new java.awt.Font("Cantarell", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Digital Library System");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 260, 40));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1700, 80));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(105, 117, 166));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/home-30.png"))); // NOI18N
        jLabel36.setText("  Dashboard");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 270, 40));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 340, 60));

        jLabel4.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Features");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 130, 40));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/book-30.png"))); // NOI18N
        jLabel8.setText("  Manage Books");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 220, 40));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 340, 60));

        jLabel9.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/book-30.png"))); // NOI18N
        jLabel9.setText("  Books");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 300, 40));

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 340, 60));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/member-30.png"))); // NOI18N
        jLabel10.setText("  Members");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 300, 40));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 340, 60));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/book-share-30.png"))); // NOI18N
        jLabel11.setText("  Issue Book");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 300, 40));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 340, 60));

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/return-30.png"))); // NOI18N
        jLabel13.setText("  Return Book");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanel10.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 300, 40));

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 340, 60));

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/records-30.png"))); // NOI18N
        jLabel12.setText("  All Records");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        jPanel11.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 300, 40));

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 340, 60));

        jPanel12.setBackground(new java.awt.Color(51, 51, 51));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/issued-book-30.png"))); // NOI18N
        jLabel14.setText("  Find Defaulter");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 300, 40));

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 340, 60));

        jPanel13.setBackground(new java.awt.Color(51, 51, 51));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/defaulter-list-30.png"))); // NOI18N
        jLabel15.setText("  All Defaulters");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        jPanel13.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 300, 40));

        jPanel5.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 340, 60));

        jPanel14.setBackground(new java.awt.Color(51, 51, 51));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_light/logout-30.png"))); // NOI18N
        jLabel5.setText("  Logout");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });
        jPanel14.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 270, 40));

        jPanel5.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 340, 60));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 340, 800));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(105, 117, 166)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_bookCount.setFont(new java.awt.Font("DialogInput", 1, 50)); // NOI18N
        lbl_bookCount.setForeground(new java.awt.Color(102, 102, 102));
        lbl_bookCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_dark/book-shelf-48.png"))); // NOI18N
        lbl_bookCount.setText("10");
        jPanel15.add(lbl_bookCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, 70));

        jPanel6.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 260, 140));

        jLabel16.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Books");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 180, -1));

        jLabel18.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Number of members");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 190, -1));

        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 128, 128)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_memberCount.setFont(new java.awt.Font("DialogInput", 1, 50)); // NOI18N
        lbl_memberCount.setForeground(new java.awt.Color(102, 102, 102));
        lbl_memberCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_dark/users-48.png"))); // NOI18N
        lbl_memberCount.setText("10");
        jPanel16.add(lbl_memberCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, 70));

        jPanel6.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 260, 140));

        jLabel19.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Issued books");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 180, -1));

        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(105, 117, 166)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_issedBookCount.setFont(new java.awt.Font("DialogInput", 1, 50)); // NOI18N
        lbl_issedBookCount.setForeground(new java.awt.Color(102, 102, 102));
        lbl_issedBookCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_dark/issued-books-48.png"))); // NOI18N
        lbl_issedBookCount.setText("10");
        jPanel17.add(lbl_issedBookCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, 70));

        jPanel6.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 260, 140));

        jLabel21.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Defaulter list");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 40, 180, -1));

        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(0, 128, 128)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_defaulterCount.setFont(new java.awt.Font("DialogInput", 1, 50)); // NOI18N
        lbl_defaulterCount.setForeground(new java.awt.Color(102, 102, 102));
        lbl_defaulterCount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_dark/defaulter-list-48.png"))); // NOI18N
        lbl_defaulterCount.setText("10");
        jPanel18.add(lbl_defaulterCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, 70));

        jPanel6.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, 260, 140));

        jTable1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Book ID", "Author", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(105, 117, 166));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(524);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(405);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
        }

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 1180, 210));

        jTable2.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Member ID", "Course", "Branch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(105, 117, 166));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable2.setShowGrid(true);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(524);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(405);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(120);
        }

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, 1180, 210));

        jLabel23.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Number of books");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 180, -1));

        jLabel24.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Members");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 180, -1));

        jButton1.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton1.setText("ID");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 260, 80, -1));

        jButton2.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton2.setText("Name");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 260, 80, -1));

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jLabel2.setText("Order By   :");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 260, 70, 20));

        jButton3.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton3.setText("ID");
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 540, 80, -1));

        jButton4.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton4.setText("Name");
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 540, 80, -1));

        jLabel6.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        jLabel6.setText("Order By   :");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 540, 70, 20));

        jButton6.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton6.setText("Quantity");
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 260, 80, -1));

        jButton7.setFont(new java.awt.Font("Cantarell", 1, 10)); // NOI18N
        jButton7.setText("Course");
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 540, 80, -1));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 1360, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
                new Books().setVisible(true);
                dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
                jPanel3.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
                jPanel3.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
                new Members().setVisible(true);
                dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
                jPanel8.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
                jPanel8.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
                new IssueBook().setVisible(true);
                dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
                jPanel9.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
                jPanel9.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
                new ReturnBook().setVisible(true);
                dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
                jPanel10.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
                jPanel10.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
                new AllRecords().setVisible(true);
                dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
                jPanel11.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
                jPanel11.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
                new SearchDefaulter().setVisible(true);
                dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
                jPanel12.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
                jPanel12.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
                new DefaulterList().setVisible(true);
                dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
                jPanel13.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
                jPanel13.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
                int logOut = JOptionPane.showConfirmDialog(this, "Do you want to log out ?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (logOut == JOptionPane.YES_OPTION) {
                        new Login().setVisible(true);
                        dispose();
                }
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
                jPanel14.setBackground(new Color(255, 51, 51));
                jLabel5.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
                jPanel14.setBackground(mouseExitColor);
                jLabel5.setForeground(new Color(204, 204, 204));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                setBookDetailsToTable("book_id");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                setBookDetailsToTable("book_name");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                setMemberDetailsToTable("member_id");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                setMemberDetailsToTable("name");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
                setBookDetailsToTable("quantity");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
                setMemberDetailsToTable("course");
    }//GEN-LAST:event_jButton7ActionPerformed

        public static void main(String args[]) {
                try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                                if ("Nimbus".equals(info.getName())) {
                                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                        break;
                                }
                        }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                java.awt.EventQueue.invokeLater(() -> {
                        new HomePage().setVisible(true);
                });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbl_bookCount;
    private javax.swing.JLabel lbl_defaulterCount;
    private javax.swing.JLabel lbl_issedBookCount;
    private javax.swing.JLabel lbl_memberCount;
    // End of variables declaration//GEN-END:variables
}
