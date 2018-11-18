package coffee_management.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import coffee_management.dto.Sale;
import coffee_management.jdbc.LogUtil;

public class RankListPanel extends JPanel {
	private JTable table;
	// 리스트 만들기
	private List<Sale> list;

	public void setList(List<Sale> list) {
		this.list = list;
	}

	public RankListPanel() {

		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	private void setAlignWidth() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableCellAlignment(SwingConstants.RIGHT, 3, 4, 5, 6, 7, 8, 9);
		tableSetWidth(100, 150, 200, 150, 150, 200, 150, 200, 100, 150);

	}

	private void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	private void tableSetWidth(int... width) {
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	public void loadDatas() {
		table.setModel(new DefaultTableModel(getDatas(), getColumnNames()));
		setAlignWidth();
	}

	private Object[][] getDatas() {
		Object[][] datas = new Object[list.size() + 1][];
		for (int i = 0; i < list.size(); i++) {
			datas[i] = getSaleRow(list.get(i));
		}
		datas[list.size()] = getTotal();
		return datas;
	}

	private Object[] getSaleRow(Sale sale) {

		return new Object[] { sale.getDetail().getRank(), sale.getProduct().getCode(), sale.getProduct().getName(),
				String.format("%,d", sale.getPrice()), String.format("%,d", sale.getSaleCnt()),
				String.format("%,d", sale.getDetail().getSalePrice()),
				String.format("%,d", sale.getDetail().getAddTax()),
				String.format("%,d", sale.getDetail().getSalePrice()), sale.getMarginRate() + "%",
				String.format("%,d", sale.getDetail().getMarginPrice()) };
	}

	private String[] getColumnNames() {
		return new String[] { "순위", "제품코드", "제품명", "제품단가", "판매수량", "공급가액", "부가세액", "판매금액", "마진율", "마진액" };
	}

	// 공급가액
	public Object[] getTotal() {
		LogUtil.prnLog("getTotal()");
		int totalSupplyPrice = 0;
		int totalAddTax = 0;
		int totalSalePrice = 0;
		int totalMarginPrice = 0;
		for (Sale s : list) {
			totalSupplyPrice += s.getDetail().getSalePrice();
			totalAddTax += s.getDetail().getAddTax();
			totalSalePrice += s.getDetail().getSalePrice();
			totalMarginPrice += s.getDetail().getMarginPrice();
//			LogUtil.prnLog(s.toString());
		}
		LogUtil.prnLog(totalSupplyPrice + "");
		return new Object[] { "합계", "", "", "", "", String.format("%,d", totalSupplyPrice),
				String.format("%,d", totalAddTax), String.format("%,d", totalSalePrice), "",
				String.format("%,d", totalMarginPrice) };
	}

}
