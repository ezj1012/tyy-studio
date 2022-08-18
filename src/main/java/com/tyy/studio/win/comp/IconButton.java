package com.tyy.studio.win.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import sun.swing.SwingUtilities2;

@SuppressWarnings("restriction")
public class IconButton extends JButton {

    private static final long serialVersionUID = 1L;

    private IconImg iconImg;

    private Color hoverBackground;

    private Color defaultColor = new Color(133, 133, 133);

    private Color hoverColor = Color.WHITE;

    public IconButton(int w, int h, IconImg img) {
        this.iconImg = img;
        this.setIcon(img.getImageIcon(defaultColor));
        this.setRolloverIcon(img.getImageIcon(hoverColor));
        this.setSize(w, h);
        this.setMaximumSize(new Dimension(w, h));
        this.setMinimumSize(new Dimension(w, h));
        this.setPreferredSize(new Dimension(w, h));
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setBorderPainted(false);

        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setUI(new BtnUI());
    }

    public Color getHoverBackground() {
        return hoverBackground;
    }

    public void setHoverBackground(Color hoverBackground) {
        this.hoverBackground = hoverBackground;
    }

    public void setIconolor(Color color) {
        this.defaultColor = color;
        this.setIcon(iconImg.getImageIcon(defaultColor));
    }

    public void setIconHoverColor(Color color) {
        this.hoverColor = color;
        this.setRolloverIcon(iconImg.getImageIcon(hoverColor));
    }

    public class BtnUI extends BasicButtonUI {

        private String btnText;

        private String drawText;

        int offx;

        protected void paintIcon(Graphics g, JComponent c, Rectangle iconRect) {
            iconRect.x = 0;
            iconRect.y = 0;
            IconButton b = (IconButton) c;
            ButtonModel model = b.getModel();
            Icon icon = b.getIcon();
            Icon tmpIcon = null;

            if (icon == null) { return; }

            Icon selectedIcon = null;

            /* the fallback icon should be based on the selected state */
            if (model.isSelected()) {
                selectedIcon = b.getSelectedIcon();
                if (selectedIcon != null) {
                    icon = selectedIcon;
                }
            }

            if (!model.isEnabled()) {
                if (model.isSelected()) {
                    tmpIcon = b.getDisabledSelectedIcon();
                    if (tmpIcon == null) {
                        tmpIcon = selectedIcon;
                    }
                }

                if (tmpIcon == null) {
                    tmpIcon = b.getDisabledIcon();
                }
            } else if (model.isPressed() && model.isArmed()) {
                tmpIcon = b.getPressedIcon();
                if (tmpIcon != null) {
                    // revert back to 0 offset
                    clearTextShiftOffset();
                }
            } else if (b.isRolloverEnabled() && model.isRollover()) {
                if (model.isSelected()) {
                    tmpIcon = b.getRolloverSelectedIcon();
                    if (tmpIcon == null) {
                        tmpIcon = selectedIcon;
                    }
                }

                if (tmpIcon == null) {
                    tmpIcon = b.getRolloverIcon();
                }
                if (hoverBackground != null) {
                    g.setColor(hoverBackground);
                    g.fillRect(0, 0, b.getWidth(), b.getHeight());
                }

            }

            if (tmpIcon != null) {
                icon = tmpIcon;
            }

            if (model.isPressed() && model.isArmed()) {
                icon.paintIcon(c, g, iconRect.x + getTextShiftOffset(), iconRect.y + getTextShiftOffset());
            } else {
                icon.paintIcon(c, g, iconRect.x, iconRect.y);
            }

        }

        protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text) {

            IconButton b = (IconButton) c;
            ButtonModel model = b.getModel();
            FontMetrics fm = g.getFontMetrics();
            int mnemonicIndex = b.getDisplayedMnemonicIndex();

            int maxW = c.getWidth() - 6;
            String a = ((AbstractButton) c).getText();
            if (a == null) { return; }
            if (btnText == null || !a.equals(btnText)) {
                btnText = a;
                drawText = "";
                for (int i = 0; i < a.length(); i++) {
                    String t = drawText + a.charAt(i);
                    if (SwingUtilities2.stringWidth(c, fm, t) > maxW) {
                        break;
                    } else {
                        drawText += a.charAt(i);
                    }
                }
                offx = (c.getWidth() - SwingUtilities2.stringWidth(c, fm, text)) / 2;
            }
            text = drawText;
            textRect.x = 0;
            if (model.isEnabled()) {
                g.setColor(b.getForeground());
                if (model.isPressed() && model.isArmed()) {
                    SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemonicIndex, textRect.x + offx, textRect.y + fm.getAscent() + getTextShiftOffset() + 1);
                } else {
                    SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemonicIndex, textRect.x + offx - 1, textRect.y + fm.getAscent() + getTextShiftOffset());
                }

            } else {
                /*** paint the text disabled ***/
                g.setColor(b.getBackground().brighter());
                SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemonicIndex, textRect.x + offx, textRect.y + fm.getAscent());
                g.setColor(b.getBackground().darker());
                SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemonicIndex, textRect.x + offx - 1, textRect.y + fm.getAscent() - 1);
            }
        }

    }

}
