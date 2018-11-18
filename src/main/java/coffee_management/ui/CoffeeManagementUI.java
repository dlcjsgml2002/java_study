package coffee_management.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import coffee_management.dto.Product;
import coffee_management.dto.Sale;
import coffee_management.service.OutputService;
import coffee_management.service.CoffeeManagementService;

public class CoffeeManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfCode;
	private JTextField tfPrice;
	private JTextField tfSaleCnt;
	private JTextField tfMarginRate;
	private JTextField tfName;

	private JButton btnInput;
	private CoffeeManagementService saleService;
	private OutputService productService;
	private JButton btnSalePrice;
	private JButton btnMarginPrice;

	public CoffeeManagementUI() {
		saleService = new CoffeeManagementService();
		productService = new OutputService();
		initComponents();
	}

	private void initComponents() {
		setTitle("판매실적 계산 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 295);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pContent = new JPanel();
		contentPane.add(pContent);
		pContent.setLayout(new GridLayout(0, 4, 10, 10));

		JLabel lblCode = new JLabel("제품코드");
		pContent.add(lblCode);
		lblCode.setHorizontalAlignment(SwingConstants.CENTER);

		tfCode = new JTextField();
		pContent.add(tfCode);
		tfCode.setColumns(10);

		JLabel lblName = new JLabel("제품명");
		pContent.add(lblName);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);

		// 제품명
		tfName = new JTextField();
		tfName.setEnabled(false);
		pContent.add(tfName);
		tfName.setColumns(10);

		JLabel lblPrice = new JLabel("제품단가");
		pContent.add(lblPrice);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);

		tfPrice = new JTextField();
		pContent.add(tfPrice);
		tfPrice.setColumns(10);

		JLabel lblblank0 = new JLabel("");
		pContent.add(lblblank0);

		JLabel lblblank1 = new JLabel("");
		pContent.add(lblblank1);

		JLabel lblSaleCnt = new JLabel("판매수량");
		pContent.add(lblSaleCnt);
		lblSaleCnt.setHorizontalAlignment(SwingConstants.CENTER);

		tfSaleCnt = new JTextField();
		pContent.add(tfSaleCnt);
		tfSaleCnt.setColumns(10);

		JLabel lblblank2 = new JLabel("");
		pContent.add(lblblank2);

		JLabel lblblank3 = new JLabel("");
		pContent.add(lblblank3);

		JLabel lblMarginRate = new JLabel("마진율");
		pContent.add(lblMarginRate);
		lblMarginRate.setHorizontalAlignment(SwingConstants.CENTER);

		tfMarginRate = new JTextField();
		pContent.add(tfMarginRate);
		tfMarginRate.setColumns(10);

		JPanel pButton = new JPanel();
		contentPane.add(pButton, BorderLayout.SOUTH);

		btnInput = new JButton("입력");
		btnInput.addActionListener(this);
		pButton.add(btnInput);

		btnSalePrice = new JButton("출력1");
		btnSalePrice.addActionListener(this);
		pButton.add(btnSalePrice);

		btnMarginPrice = new JButton("출력2");
		btnMarginPrice.addActionListener(this);
		pButton.add(btnMarginPrice);

		tfCode.getDocument().addDocumentListener(new MyDocumentListener() {

			@Override
			public void msg() {
				if (tfCode.getText().length() == 4) {

					Product pdt = new Product(tfCode.getText().trim());
					try {
						Product searchPdt = saleService.searchProduct(pdt);
						System.out.println(searchPdt);
						tfName.setText(searchPdt.getName());
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						tfName.setText("해당 제품이 없음");
					}
				}

			}

		});
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnMarginPrice) {
			do_btnMarginPrice_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnSalePrice) {
			do_btnSalePrice_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnInput) {
			do_btnInput_actionPerformed(arg0);
		}
	}

	// 입력버튼 => 입력한 데이터들이 sale 테이블로
	protected void do_btnInput_actionPerformed(ActionEvent arg0) {
		// tf 값들을 들고와서 sale로 만든다.
		Sale sale = getSale();
		// register 호출.
		int res;
		try {
			res = saleService.registerSale(sale);
			if (res == 1) {
				JOptionPane.showMessageDialog(null, "추가했습니다.");
			}
			clearTf();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 입력창 clear
	private void clearTf() {
		tfCode.setText("");
		tfPrice.setText("");
		tfName.setText("");
		tfSaleCnt.setText("");
		tfMarginRate.setText("");
	}

	private Sale getSale() {
		String code = tfCode.getText().trim();
		int price = Integer.parseInt(tfPrice.getText().trim());
		int saleCnt = Integer.parseInt(tfSaleCnt.getText().trim());
		int marginRate = Integer.parseInt(tfMarginRate.getText().trim());

		return new Sale(0, new Product(code), price, saleCnt, marginRate);
	}

	// 출력1
	protected void do_btnSalePrice_actionPerformed(ActionEvent arg0) {
		OutputUI ui = new OutputUI(true);
		ui.setVisible(true);
	}

	protected void do_btnMarginPrice_actionPerformed(ActionEvent arg0) {
		OutputUI ui = new OutputUI(false);
		ui.setVisible(true);
	}

}
