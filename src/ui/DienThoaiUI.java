package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	private JButton btnSua;
	private Component frame;
	private JTextField txtTim;
	private DienThoaiDAO dao;
	
	
	public DienThoaiUI() {
		dao = new DienThoaiDAO();
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
	
	// Nút Sửa
	btnSua = new JButton("Sửa");
	btnSua.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			suaDienThoai();
		}
	});
	
	table.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent e) {
	        int row = table.getSelectedRow();
	        txtMa.setText(tableModel.getValueAt(row, 0).toString()); // đừng quên Mã
	        txtTen.setText(tableModel.getValueAt(row, 1).toString());
	        txtHang.setText(tableModel.getValueAt(row, 2).toString());
	        txtGia.setText(tableModel.getValueAt(row, 3).toString());
	        txtSoLuong.setText(tableModel.getValueAt(row, 4).toString());
	    }
	});
	
	txtTim = new JTextField(20);
	JButton btnTim = new JButton("Tìm");

	btnTim.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        timKiemDienThoai();
	    }
	});

	JPanel panelTim = new JPanel();
	panelTim.add(new JLabel("Tìm kiếm:"));
	panelTim.add(txtTim);
	panelTim.add(btnTim);
	this.add(panelTim, BorderLayout.NORTH);

	//// săp xếp 
	
	String[] sortOptions = {"---Sắp xếp theo---" ,"Tên(A-Z)" ,  "Tên(Z-A)" , "Giá(Thấp - Cao)" ,"Giá(Cao - Thấp)"};
	JComboBox<String> cbSort = new JComboBox<>(sortOptions) ;
	
	
	cbSort.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			sapXepDienThoai(cbSort.getSelectedItem().toString());
		}
	});
	panelTim.add(cbSort);
	
		btnPanel.add(btnSua);// thêm nút sửa vào giao diện  
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
	    int ma = 0;
	    String ten = txtTen.getText().trim();
	    String hang = txtHang.getText().trim();
	    double gia = 0;
	    int soluong = 0;

	    try {
	        ma = Integer.parseInt(txtMa.getText().trim());
	        gia = Double.parseDouble(txtGia.getText().trim());
	        soluong = Integer.parseInt(txtSoLuong.getText().trim());
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this , "Mã, Giá và Số lượng phải là số!");
	        return;
	    }

	    if(ten.isEmpty() ||  hang.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
	        return;
	    }

	    Dienthoai dt = new Dienthoai(ma , ten , hang , gia , soluong);
	    DienThoaiDAO dao = new DienThoaiDAO();
	    boolean ok = dao.insert(dt);

	    if(ok) {
	        JOptionPane.showMessageDialog(this, "Thêm thành công!");
	        loadDataToTable();
	        clearFrom();
	    } else {
	        JOptionPane.showMessageDialog(this, "Thêm thất bại! Mã đã tồn tại.");
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

	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn điện thoại cần xóa");
	        return;
	    }

	    int ma = Integer.parseInt(tableModel.getValueAt(selectedRow , 0).toString());

	    int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	    if (confirm == JOptionPane.YES_OPTION) {
	        DienThoaiDAO dao = new DienThoaiDAO();
	        boolean ok = dao.delete(ma);
	        if (ok) {
	            JOptionPane.showMessageDialog(this , "Xóa thành công!");
	            loadDataToTable();
	            clearFrom();
	        } else {
	            JOptionPane.showMessageDialog(this, "Xóa thất bại!");
	        }
	    }
	}

	private void suaDienThoai() {
	    int selectedRow = table.getSelectedRow();
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để sửa");
	        return;
	    }

	    try {
	        int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
	        String ten = txtTen.getText().trim();
	        String hang = txtHang.getText().trim();
	        double gia = Double.parseDouble(txtGia.getText().trim());
	        int soLuong = Integer.parseInt(txtSoLuong.getText().trim());

	        Dienthoai dt = new Dienthoai(id, ten, hang, gia, soLuong);
	        DienThoaiDAO dao = new DienThoaiDAO();
	        boolean success = dao.updateDienThoai(dt);

	        if (success) {
	            loadDataToTable();
	            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
	        } else {
	            JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ");
	    }
	}

	private void timKiemDienThoai() {
	    String keyword = txtTim.getText().trim();
	    if (keyword.isEmpty()) {
	        loadDataToTable(); // Hiện toàn bộ nếu ô trống
	        return;
	    }

	    List<Dienthoai> ketQua = dao.timKiemDienThoai(keyword);
	    tableModel.setRowCount(0); // Clear bảng
	    for (Dienthoai dt : ketQua) {
	    	   tableModel.addRow(new Object[]{
	            dt.getMa(), dt.getTen(), dt.getHang(), dt.getGia(), dt.getSoluong()
	        });
	    }
	}

	private void sapXepDienThoai(String loaiSapXep) {
		List<Dienthoai> danhSach = dao.getAll();
		
		switch (loaiSapXep) {
		case"Tên(A-Z)" :
			danhSach.sort(Comparator.comparing(Dienthoai::getTen)) ;
			break;
			
		case"Tên(Z-A)" :
			danhSach.sort(Comparator.comparing(Dienthoai::getTen).reversed()) ;
			break;
			
		case"Giá(Thấp - Cao)" :
			danhSach.sort(Comparator.comparingDouble(Dienthoai::getGia)) ;
			break;
			
		case"Giá(Cao - Thấp)" :
			danhSach.sort(Comparator.comparingDouble(Dienthoai::getGia).reversed()) ;
			break;
		default:
			return;
		
		}
		  // Cập nhật lại dữ liệu trên bảng
	    tableModel.setRowCount(0); // Xóa dữ liệu cũ
	    for (Dienthoai dt : danhSach) {
	        tableModel.addRow(new Object[]{
	            dt.getMa(), dt.getTen(), dt.getHang(), dt.getGia(), dt.getSoluong()
	        });
	    }
		
	}
	

}
