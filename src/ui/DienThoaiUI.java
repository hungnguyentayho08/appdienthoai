package ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DienThoaiUI extends JFrame  {
	private JTable table  ;
	private DefaultTableModel tableModel ;
	
	public DienThoaiUI() {
		setTitle("Quản Lý Bán Điện Thoại ") ;
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initUI();
		loadDataToTable();
		setVisible(true) ;
	}
	
	private void initUI() {
		// Panel
		JPanel panel = new JPanel(new BorderLayout()) ;
		
		// tiêu đề 
		JLabel titleLabel = new JLabel ("DANH SÁCH ĐIỆN THOẠI" , JLabel.CENTER);
		titleLabel.setFont(new Font("Arial" ,Font.BOLD,20) );
		panel.add(titleLabel,BorderLayout.NORTH);
		
		// Bảng 
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(new String[] {"Mã" ,"Tên" , "Hãng" , "Giá" , "Số lượng"});
		table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane , BorderLayout.CENTER); 
		
		
	}
	

}
