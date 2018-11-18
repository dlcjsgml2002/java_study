package coffee_management.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import coffee_management.dto.Sale;
import coffee_management.service.OutputService;

public class OutputUI extends JFrame {

	private JPanel contentPane;
	private boolean isSale;
	private OutputService service;
	private RankListPanel pRankList;

	/**
	 * Create the frame.
	 */
	public OutputUI(boolean isSale) {
		service = new OutputService();
		this.isSale = isSale;
		setTitle(isSale ? "판매금액순위" : "마진액순위");
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		List<Sale> list;
		try {
			list = service.outputOrder(isSale);
			pRankList = new RankListPanel();
			pRankList.setList(list);
			pRankList.loadDatas();
			contentPane.add(pRankList, BorderLayout.CENTER);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		JPanel pTitle = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pTitle.getLayout();
		flowLayout.setVgap(20);
		contentPane.add(pTitle, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel(isSale ? "판매금액순위" : "마진액순위");
		pTitle.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 30));
	}

}
