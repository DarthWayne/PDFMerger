package com.pdfmerger.app.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.OverlayLayout;

import org.jdesktop.swingx.border.DropShadowBorder;

import com.components.Fonts;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Preview_pnl extends JPanel {
	private JLabel lblThumbnail;
	private JPopupMenu popupMenu;
	private JMenuItem mniDelete;
	private JMenuItem mniDuplicate;
	private JMenuItem mniMoveToFront;
	private JMenuItem mniMoveToBack;
	private JButton btnDelete;
	private JPanel pnlContent;
	private JPanel pnlOverlay;

	public Preview_pnl() {
		initialize();
	}

	private void initialize() {
		setOpaque(false);
		setLayout(new BorderLayout());
		add(getPnlContent());
	}

	public JPanel getPnlContent() {
		if (pnlContent == null) {
			pnlContent = new JPanel();
			pnlContent.setOpaque(false);
			pnlContent.setLayout(new OverlayLayout(pnlContent));
			pnlContent.add(getPnlOverlay());
			pnlContent.add(getLblThumbnail());
			DropShadowBorder border = new DropShadowBorder();
			border.setShowBottomShadow(true);
			border.setShowLeftShadow(true);
			border.setShowRightShadow(true);
			border.setShowTopShadow(false);
			pnlContent.setBorder(border);
			pnlContent.setComponentPopupMenu(getPopupMenu());
		}
		return pnlContent;
	}

	public JPanel getPnlOverlay() {
		if (pnlOverlay == null) {
			pnlOverlay = new JPanel();
			pnlOverlay.setVisible(false);
			pnlOverlay.setInheritsPopupMenu(true);
			pnlOverlay.setOpaque(false);
			pnlOverlay.setLayout(
					new FormLayout(new ColumnSpec[] { ColumnSpec.decode("4dlu:grow"), ColumnSpec.decode("24px"), },
							new RowSpec[] { RowSpec.decode("24px"), RowSpec.decode("4dlu:grow"), }));
			pnlOverlay.add(getBtnDelete(), "2, 1, fill, fill");
		}
		return pnlOverlay;
	}

	public JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
			btnDelete.setMargin(new Insets(2, 2, 2, 2));
			btnDelete.setText("x");
		}
		return btnDelete;
	}

	public JLabel getLblThumbnail() {
		if (lblThumbnail == null) {
			lblThumbnail = new JLabel();
			lblThumbnail.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblThumbnail.setIconTextGap(0);
			lblThumbnail.setBorder(null);
			lblThumbnail.setIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\sample.jpg"));
		}
		return lblThumbnail;
	}

	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getMniMoveToFront());
			popupMenu.add(getMniMoveToBack());
			popupMenu.add(getMniDuplicate());
			popupMenu.add(getMniDelete());
		}
		return popupMenu;
	}

	public JMenuItem getMniDelete() {
		if (mniDelete == null) {
			mniDelete = new JMenuItem("Delete");
			mniDelete.setFont(Fonts.SMALL);
		}
		return mniDelete;
	}

	public JMenuItem getMniDuplicate() {
		if (mniDuplicate == null) {
			mniDuplicate = new JMenuItem("Duplicate");
			mniDuplicate.setFont(Fonts.SMALL);
		}
		return mniDuplicate;
	}

	public JMenuItem getMniMoveToFront() {
		if (mniMoveToFront == null) {
			mniMoveToFront = new JMenuItem("Move to front");
			mniMoveToFront.setFont(Fonts.SMALL);
		}
		return mniMoveToFront;
	}

	public JMenuItem getMniMoveToBack() {
		if (mniMoveToBack == null) {
			mniMoveToBack = new JMenuItem("Move to back");
			mniMoveToBack.setFont(Fonts.SMALL);
		}
		return mniMoveToBack;
	}
}
