package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DienThoaiDAO;
import model.Dienthoai;

public class DienThoaiUI extends JFrame  {
	private JTable table  ;
	private DefaultTableModel tableModel ;
	private JTextField txtMa , txtTen , txtHang , txtGia ,txtSoLuong ;
	private JButton btnThem ;
	private JButton btnXoa;
	
	
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
		
		//Form nhập 
		JPanel inputPanel = new JPanel(new GridLayout(2,5,5,5)); // 2 hàng 5 cột 5pixel ngang và 5 pixel khoảng cách dọc 
		inputPanel.setBorder(new TitledBorder("Nhập thông tin"));
		
		txtMa = new JTextField();		
		txtTen = new JTextField();	
		txtHang = new JTextField();	
		txtGia = new JTextField();	
		txtSoLuong= new JTextField();	
		
		inputPanel.add(new JLabel("Mã:"));
		inputPanel.add(new JLabel("Tên:"));
		inputPanel.add(new JLabel("Hãng:"));
		inputPanel.add(new JLabel("Giá:"));
		inputPanel.add(new JLabel("Số Lượng:"));
		
		inputPanel.add(txtMa);
		inputPanel.add(txtTen);
		inputPanel.add(txtHang);
		inputPanel.add(txtGia);
		inputPanel.add(txtSoLuong);
		
		// Nút Thêm 
		btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				themDienThoai();
				
			}

			
		});
		
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(btnThem);
		
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(inputPanel, BorderLayout.CENTER);
		southPanel.add(btnPanel, BorderLayout.SOUTH);

		panel.add(southPanel, BorderLayout.SOUTH);
		
		
		//////////////////////////////////////////
		
		// nút Xóa 
	btnXoa = new JButton("Xóa");
	btnXoa.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			xoaDienThoai();
			
		}
	});
	
		btnPanel.add(btnXoa) ; // thêm nút xóa vào giao diện  
		
		
		
		
		
		
		this.add(panel); // thêm panel vào frame
		
	}
	
	

	
	
	private void loadDataToTable() {
		
		DienThoaiDAO dao = new dao.DienThoaiDAO();
		List<Dienthoai> list = dao.getAll();
		
		tableModel.setRowCount(0);
		
		for (Dienthoai dt : list) {
			tableModel.addRow(new Object[] {
					 dt.getMa(),
		             dt.getTen(),
		             dt.getHang(),
		             dt.getGia(),
		             dt.getSoluong()
			});
				
			}
		}
	
	
	private void themDienThoai() {
		String ma = txtMa.getText().trim();
		String ten = txtTen.getText().trim();
		String hang = txtHang.getText().trim();
		double gia = 0 ;
		int soluong = 0 ;
		
		try {
			gia = Double.parseDouble(txtGia.getText().trim());
			soluong = Integer.parseInt(txtSoLuong.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this , "Giá và số lượng phải là số !");
			return ;
		}
		
		if(ma.isEmpty() || ten.isEmpty() ||  hang.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
			return ;
		}
		
		Dienthoai dt = new Dienthoai(ma , ten , hang , gia , soluong);
		DienThoaiDAO dao = new DienThoaiDAO();
		boolean ok = dao.insert(dt);
		
		if(ok) {
			JOptionPane.showMessageDialog(this, "Thêm thành công !");
			loadDataToTable();
			clearFrom();
		} else {
			JOptionPane.showMessageDialog(this, "Thêm thất bịa ! Mã đã tồn tại =))");
		}
		
	}

	private void clearFrom(){	
		txtMa.setText("");
		txtTen.setText("");
		txtHang.setText("");
		txtGia.setText("");
		txtSoLuong.setText("");
	}
	
	
	private void xoaDienThoai() {
		int selectedRow = table.getSelectedRow();
		
		if(selectedRow == -1) {
			JOptionPane.show
		}
		
	}
	
	

}
