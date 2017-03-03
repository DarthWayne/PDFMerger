package com.pdfmerger.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.border.DropShadowBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.pdfmerger.app.fillstrategy.FillStrategy;
import java.awt.Dimension;

public class ImageImport_dlg extends JDialog {
	private JPanel pnlOptions;
	private JPanel pnlButtons;
	private JPanel pnlPreview;
	private JPanel panel;
	private JLabel label;
	private JComboBox comboBox;
	private JLabel lblFillStrategy;
	private JLabel lblMargin;
	private JSpinner spnMarginTop;
	private JSpinner spnMarginBottom;
	private JSpinner spnMarginRight;
	private JSpinner spnMarginLeft;
	private JLabel lblRightBorder;
	private JLabel lblBottomBorder;
	private JLabel lblLeftBorder;
	private JButton btnOk;
	private JLabel lblOrientation;
	private JRadioButton rdbOrientationTopLeft;
	private JRadioButton rdbOrientationTopCenter;
	private JRadioButton rdbOrientationTopRight;
	private JRadioButton rdbOrientationCenterLeft;
	private JRadioButton rdbOrientationCenter;
	private JRadioButton rdbOrientationCenterRight;
	private JRadioButton rdbOrientationBottomLeft;
	private JRadioButton rdbOrientationBottomCenter;
	private JRadioButton rdbOrientationBottomRight;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnCancel;
	private JPanel pnlAnchors;
	private JPanel panel_2;

	public ImageImport_dlg() {
		initialize();
	}

	private void initialize() {
		setTitle("Import an image");
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("168dlu"),
				ColumnSpec.decode("20dlu"),
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("20dlu"),},
			new RowSpec[] {
				RowSpec.decode("20dlu"),
				RowSpec.decode("238dlu"),
				RowSpec.decode("7dlu:grow"),
				FormSpecs.DEFAULT_ROWSPEC,}));
		getContentPane().add(getPnlOptions(), "4, 2, fill, fill");
		getContentPane().add(getPnlPreview(), "2, 2, fill, fill");
		getContentPane().add(getPnlButtons(), "1, 4, 5, 1, fill, top");
	}

	private JPanel getPnlOptions() {
		if (pnlOptions == null) {
			pnlOptions = new JPanel();
			pnlOptions.setLayout(new FormLayout(
					new ColumnSpec[] { ColumnSpec.decode("right:default"), FormSpecs.UNRELATED_GAP_COLSPEC,
							ColumnSpec.decode("15dlu:grow"), FormSpecs.RELATED_GAP_COLSPEC,
							ColumnSpec.decode("15dlu:grow"), },
					new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, RowSpec.decode("50dlu:grow"),
							FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							FormSpecs.DEFAULT_ROWSPEC, }));
			pnlOptions.add(getLblFillStrategy(), "1, 1");
			pnlOptions.add(getComboBox(), "3, 1, 3, 1");
			pnlOptions.add(getLblMargin(), "1, 3");
			pnlOptions.add(getSpnMarginTop(), "3, 3, 3, 1");
			pnlOptions.add(getLblRightBorder(), "1, 5");
			pnlOptions.add(getSpnMarginRight(), "3, 5, 3, 1");
			pnlOptions.add(getLblBottomBorder(), "1, 7");
			pnlOptions.add(getSpnMarginBottom(), "3, 7, 3, 1");
			pnlOptions.add(getLblLeftBorder(), "1, 9");
			pnlOptions.add(getSpnMarginLeft(), "3, 9, 3, 1");
			pnlOptions.add(getLblOrientation(), "1, 11");
			pnlOptions.add(getPanel_2(), "3, 11, fill, fill");
		}
		return pnlOptions;
	}

	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			pnlButtons.add(getBtnOk());
			pnlButtons.add(getButton_1());
		}
		return pnlButtons;
	}

	private JPanel getPnlPreview() {
		if (pnlPreview == null) {
			pnlPreview = new JPanel();
			DropShadowBorder border = new DropShadowBorder();
			border.setShowBottomShadow(true);
			border.setShowLeftShadow(true);
			border.setShowRightShadow(true);
			border.setShowTopShadow(false);
			pnlPreview.setBorder(border);
			pnlPreview.setLayout(new BorderLayout(0, 0));
			pnlPreview.add(getPanel(), BorderLayout.CENTER);
		}
		return pnlPreview;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new OverlayLayout(panel));
			panel.add(getPnlAnchors());
			panel.add(getLabel());
		}
		return panel;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\application_icon.png"));
		}
		return label;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(FillStrategy.values()));
		}
		return comboBox;
	}

	private JLabel getLblFillStrategy() {
		if (lblFillStrategy == null) {
			lblFillStrategy = new JLabel("Fill Strategy");
		}
		return lblFillStrategy;
	}

	private JLabel getLblMargin() {
		if (lblMargin == null) {
			lblMargin = new JLabel("Top Margin");
		}
		return lblMargin;
	}

	private JSpinner getSpnMarginTop() {
		if (spnMarginTop == null) {
			spnMarginTop = new JSpinner();
		}
		return spnMarginTop;
	}

	private JSpinner getSpnMarginBottom() {
		if (spnMarginBottom == null) {
			spnMarginBottom = new JSpinner();
		}
		return spnMarginBottom;
	}

	private JSpinner getSpnMarginRight() {
		if (spnMarginRight == null) {
			spnMarginRight = new JSpinner();
		}
		return spnMarginRight;
	}

	private JSpinner getSpnMarginLeft() {
		if (spnMarginLeft == null) {
			spnMarginLeft = new JSpinner();
		}
		return spnMarginLeft;
	}

	private JLabel getLblRightBorder() {
		if (lblRightBorder == null) {
			lblRightBorder = new JLabel("Right Margin");
		}
		return lblRightBorder;
	}

	private JLabel getLblBottomBorder() {
		if (lblBottomBorder == null) {
			lblBottomBorder = new JLabel("Bottom Margin");
		}
		return lblBottomBorder;
	}

	private JLabel getLblLeftBorder() {
		if (lblLeftBorder == null) {
			lblLeftBorder = new JLabel("Left Margin");
		}
		return lblLeftBorder;
	}

	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
		}
		return btnOk;
	}

	private JLabel getLblOrientation() {
		if (lblOrientation == null) {
			lblOrientation = new JLabel("Orientation");
		}
		return lblOrientation;
	}

	private JRadioButton getRdbOrientationTopLeft() {
		if (rdbOrientationTopLeft == null) {
			rdbOrientationTopLeft = new JRadioButton("");
			rdbOrientationTopLeft.setIcon(null);
			rdbOrientationTopLeft.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationTopLeft.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationTopLeft.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationTopLeft.setOpaque(false);
			buttonGroup.add(rdbOrientationTopLeft);
		}
		return rdbOrientationTopLeft;
	}

	private JRadioButton getRdbOrientationTopCenter() {
		if (rdbOrientationTopCenter == null) {
			rdbOrientationTopCenter = new JRadioButton("");
			rdbOrientationTopCenter.setIcon(null);
			rdbOrientationTopCenter.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationTopCenter.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationTopCenter.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationTopCenter.setOpaque(false);
			buttonGroup.add(rdbOrientationTopCenter);
		}
		return rdbOrientationTopCenter;
	}

	private JRadioButton getRdbOrientationTopRight() {
		if (rdbOrientationTopRight == null) {
			rdbOrientationTopRight = new JRadioButton("");
			rdbOrientationTopRight.setIcon(null);
			rdbOrientationTopRight.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationTopRight.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationTopRight.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationTopRight.setOpaque(false);
			buttonGroup.add(rdbOrientationTopRight);
		}
		return rdbOrientationTopRight;
	}

	private JRadioButton getRdbOrientationCenterLeft() {
		if (rdbOrientationCenterLeft == null) {
			rdbOrientationCenterLeft = new JRadioButton("");
			rdbOrientationCenterLeft.setIcon(null);
			rdbOrientationCenterLeft.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationCenterLeft.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationCenterLeft.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationCenterLeft.setOpaque(false);
			buttonGroup.add(rdbOrientationCenterLeft);
		}
		return rdbOrientationCenterLeft;
	}

	private JRadioButton getRdbOrientationCenter() {
		if (rdbOrientationCenter == null) {
			rdbOrientationCenter = new JRadioButton("");
			rdbOrientationCenter.setIcon(null);
			rdbOrientationCenter.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationCenter.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationCenter.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationCenter.setOpaque(false);
			buttonGroup.add(rdbOrientationCenter);
			rdbOrientationCenter.setSelected(true);
		}
		return rdbOrientationCenter;
	}

	private JRadioButton getRdbOrientationCenterRight() {
		if (rdbOrientationCenterRight == null) {
			rdbOrientationCenterRight = new JRadioButton("");
			rdbOrientationCenterRight.setIcon(null);
			rdbOrientationCenterRight.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationCenterRight.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationCenterRight.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationCenterRight.setOpaque(false);
			buttonGroup.add(rdbOrientationCenterRight);
		}
		return rdbOrientationCenterRight;
	}

	private JRadioButton getRdbOrientationBottomLeft() {
		if (rdbOrientationBottomLeft == null) {
			rdbOrientationBottomLeft = new JRadioButton("");
			rdbOrientationBottomLeft.setIcon(null);
			rdbOrientationBottomLeft.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationBottomLeft.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationBottomLeft.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationBottomLeft.setOpaque(false);
			buttonGroup.add(rdbOrientationBottomLeft);
		}
		return rdbOrientationBottomLeft;
	}

	private JRadioButton getRdbOrientationBottomCenter() {
		if (rdbOrientationBottomCenter == null) {
			rdbOrientationBottomCenter = new JRadioButton("");
			rdbOrientationBottomCenter.setIcon(null);
			rdbOrientationBottomCenter.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationBottomCenter.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationBottomCenter.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationBottomCenter.setOpaque(false);
			buttonGroup.add(rdbOrientationBottomCenter);
		}
		return rdbOrientationBottomCenter;
	}

	private JRadioButton getRdbOrientationBottomRight() {
		if (rdbOrientationBottomRight == null) {
			rdbOrientationBottomRight = new JRadioButton("");
			rdbOrientationBottomRight.setIcon(null);
			rdbOrientationBottomRight.setSelectedIcon(new ImageIcon("C:\\Users\\Malte\\Desktop\\anchor.png"));
			rdbOrientationBottomRight.setHorizontalTextPosition(SwingConstants.CENTER);
			rdbOrientationBottomRight.setHorizontalAlignment(SwingConstants.CENTER);
			rdbOrientationBottomRight.setOpaque(false);
			buttonGroup.add(rdbOrientationBottomRight);
		}
		return rdbOrientationBottomRight;
	}

	private JButton getButton_1() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
		}
		return btnCancel;
	}

	private JPanel getPnlAnchors() {
		if (pnlAnchors == null) {
			pnlAnchors = new JPanel();
			pnlAnchors.setOpaque(false);
			pnlAnchors.setBackground(Color.WHITE);
			pnlAnchors.setLayout(new FormLayout(
					new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("40dlu"),
							ColumnSpec.decode("default:grow"), ColumnSpec.decode("40dlu"),
							FormSpecs.RELATED_GAP_COLSPEC, },
					new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("40dlu"),
							RowSpec.decode("default:grow"), RowSpec.decode("40dlu"), FormSpecs.RELATED_GAP_ROWSPEC, }));
			pnlAnchors.add(getRdbOrientationTopLeft(), "2, 2, fill, fill");
			pnlAnchors.add(getRdbOrientationTopCenter(), "3, 2, fill, fill");
			pnlAnchors.add(getRdbOrientationTopRight(), "4, 2, fill, fill");
			pnlAnchors.add(getRdbOrientationCenterLeft(), "2, 3, fill, fill");
			pnlAnchors.add(getRdbOrientationCenter(), "3, 3, fill, fill");
			pnlAnchors.add(getRdbOrientationCenterRight(), "4, 3, fill, fill");
			pnlAnchors.add(getRdbOrientationBottomLeft(), "2, 4, fill, fill");
			pnlAnchors.add(getRdbOrientationBottomCenter(), "3, 4, fill, fill");
			pnlAnchors.add(getRdbOrientationBottomRight(), "4, 4, fill, fill");
		}
		return pnlAnchors;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
		}
		return panel_2;
	}
}
