package com.poonam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ParentTree extends JFrame {

	public static final String PROP_SEP = ",";

	private JTree tree;
	private JLabel selectedLabel;
	private Properties appProperties = new Properties();

	private DefaultMutableTreeNode addTreeNodeForNodename(DefaultMutableTreeNode parentNode, String nodeName)
			throws Exception {
		String rootChildrens = (String) appProperties.get(nodeName);
		if (rootChildrens == null) {
			// Reached leaf node, no tree formation needed further
			DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(nodeName);
			if (parentNode != null) {
				parentNode.add(currentNode);
			}
			return currentNode;
		}
		String[] split = rootChildrens.split(PROP_SEP);
		if (split.length < 1) {
			throw new Exception("Invalid value presented for nodename " + nodeName);
		} else {
			String nodeDisplayName = split[0];
			DefaultMutableTreeNode currentNode = new DefaultMutableTreeNode(nodeDisplayName);
			if (parentNode != null) {
				parentNode.add(currentNode);
			}
			if (split.length > 1) {
				// Non leaf node in the tree, continue processing
				for (int index = 1; index < split.length; index++) {
					addTreeNodeForNodename(currentNode, split[index].trim());
				}
			}

			return currentNode;
		}

	}

	public ParentTree() throws Exception {
		appProperties
				.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));

		String nodeName = "root";
		if (!appProperties.containsKey(nodeName)) {
			throw new Exception("No data presented for root");
		}

		DefaultMutableTreeNode rootTreeNode = addTreeNodeForNodename(null, "root");

		// create the tree by passing in the root node
		tree = new JTree(rootTreeNode);
		ImageIcon imageIcon = new ImageIcon(ParentTree.class.getResource("/server.jpg"));
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		renderer.setLeafIcon(imageIcon);

		tree.setCellRenderer(renderer);
		tree.setShowsRootHandles(true);
		tree.setRootVisible(false);
		tree.setExpandsSelectedPaths(true);

		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBackground(Color.BLACK);
		add(scrollPane, BorderLayout.WEST);

//		JPanel p = new JPanel();
//		p.add(new JLabel("Test Label"));
//		add(p);

//		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				System.out.println("Selected node " + selectedNode.getUserObject().toString());
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTree Example");
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - 800) / 2;
		final int y = (screenSize.height - 600) / 2;
		setLocation(x, y);
		this.setSize(800, 600);
		this.setVisible(true);

		LookAndFeel.setLookAndFeel(this, 2);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new ParentTree();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}