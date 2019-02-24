package gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AdminView extends JFrame {
	JPanel adminPanel;
	JButton editStore;
	JButton removeItemReview;
	JButton addItem;
	JLabel editS;

	public AdminView(){
		adminPanel = new JPanel();
		adminPanel.setLayout(new GridLayoutManager (4, 1, new Insets(0, 0, 0,
				0), -1, -1));

		add(adminPanel);
		setLocationRelativeTo(super.rootPane);
		setResizable(false);
		setSize(300, 300);
		editS = new JLabel("Query Examples:");
		editStore = new JButton();
		removeItemReview = new JButton();
		addItem = new JButton();

		editStore.setText("Edit a Store");
		removeItemReview.setText("Remove an Item Review");
		addItem.setText("Add an Item");
		editStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editStoreQuery();
			}
		});

		removeItemReview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeItemReviewQuery();
			}
		});

		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addItemQuery();
			}
		});
		//ugly intellij GUI maker stuff to match rest of GUI
		adminPanel.add(editS, new GridConstraints(0, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
						.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, new Dimension(75, 20), null, 0, false));
		adminPanel.add(editStore, new GridConstraints(1, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
						.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, new Dimension(125, 50), null, 0, false));
		adminPanel.add(removeItemReview, new GridConstraints(2, 0, 1, 1,
				GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
						.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, new Dimension(125, 50), null, 0, false));
		adminPanel.add(addItem, new GridConstraints(3, 0, 1, 1, GridConstraints
				.ANCHOR_CENTER, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints
						.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, new Dimension(125, 50), null, 0, false));

		adminPanel.setVisible(true);
	}
	//Example queries
	private void editStoreQuery() {
		Connection conn = null;
		Statement stmt = null;
		try {
			System.out.println("Edit Item");
			Class.forName(MainScreen.driver);
			conn = DriverManager.getConnection(MainScreen.url, MainScreen.user,
					MainScreen.password);
			stmt = conn.createStatement();
			String q = "UPDATE store SET store.phone = 4805551234 " +
					"WHERE store.storeID = 12023;";
			System.out.println("Running query: " + q);
			stmt.executeUpdate(q);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void removeItemReviewQuery() {
		Connection conn = null;
		Statement stmt = null;
		try {
			System.out.println("Removing Item Review");
			Class.forName(MainScreen.driver);
			conn = DriverManager.getConnection(MainScreen.url, MainScreen.user,
					MainScreen.password);
			stmt = conn.createStatement();
			String q1 = "DELETE FROM itemreview " +
					"WHERE reviewID = 17727674;";
			String q2 = "DELETE FROM review " +
					"WHERE reviewID = 17727674;";
			System.out.println("Running query: " + q1);
			stmt.executeUpdate(q1);
			System.out.println("Running query: " + q2);
			stmt.executeUpdate(q2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void addItemQuery() {
		Connection conn = null;
		Statement stmt = null;
		try {
			System.out.println("Adding Item");
			Class.forName(MainScreen.driver);
			conn = DriverManager.getConnection(MainScreen.url, MainScreen.user,
					MainScreen.password);
			stmt = conn.createStatement();
			String q = "INSERT into item VALUES (99067192, 'new', 42, " +
					"'France', 'Pancakes');";
			System.out.println("Running query: " + q);
			stmt.executeUpdate(q);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
