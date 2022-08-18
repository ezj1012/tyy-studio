package com.tyy.studio.win.comp;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.tyy.studio.win.Res;

import sun.awt.image.ToolkitImage;

@SuppressWarnings("restriction")
public class ScaleImg {

    private int x = 0, y = 0, x1 = -1, y1 = -1;

    private ToolkitImage icon;

    private ImageIcon sicon;

    private boolean setPointed = false;

    public ScaleImg(ImageIcon icon) {
        this.sicon = icon;
        this.icon = (ToolkitImage) icon.getImage();
    }

    public ScaleImg(String icon) {
        this(Res.get().getImage(icon));
    }

    public ScaleImg(String icon, String point) {
        this.sicon = Res.get().getImage(icon);
        this.icon = (ToolkitImage) this.sicon.getImage();
        this.setPoint(point);
    }

    public ScaleImg(ImageIcon icon, String point) {
        this.sicon = icon;
        this.icon = (ToolkitImage) icon.getImage();
        this.setPoint(point);
    }

    public ScaleImg(ImageIcon icon, int x, int y, int x1, int y1) {
        super();
        this.sicon = icon;
        this.icon = (ToolkitImage) icon.getImage();
        this.setPoint(x, y, x1, y1);
    }

    public ScaleImg setPoint(String s) {
        String[] split = s.split(",");
        x = Integer.parseInt(split[0]);
        y = Integer.parseInt(split[1]);
        x1 = Integer.parseInt(split[2]);
        y1 = Integer.parseInt(split[3]);
        setPointed = true;
        return this;
    }

    public ScaleImg setPoint(int x, int y, int x1, int y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        setPointed = true;
        return this;
    }

    public ImageIcon getImage() {
        return sicon;
    }

    public synchronized ImageIcon getImage(int w, int h) {
        if (w == icon.getWidth() && h == icon.getHeight()) { return sicon; }

        BufferedImage img = icon.getBufferedImage();
        BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = b.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if (setPointed) {

            int offX = 0;
            int offY = 0;
            // 先画4个角
            int imgW = sicon.getIconWidth();
            int imgH = sicon.getIconHeight();
            int targetImgW = w;
            int targetImgH = h;
            int scaleNum = 1;
            int lineX1 = x;
            int lineX2 = x1;
            int lineY1 = y;
            int lineY2 = y1;
            int rw = (imgW - lineX2) * scaleNum;
            int bH = (imgH - lineY2) * scaleNum;
            // 左上
            g.drawImage(img, offX, offY, offX + lineX1 * scaleNum, offY + lineY1 * scaleNum, 0, 0, lineX1, lineY1, null);
            // 右上
            g.drawImage(img, offX + targetImgW - rw, offY, offX + targetImgW, offY + lineY1 * scaleNum, lineX2, 0, imgW, lineY1, null);
            // 左下
            g.drawImage(img, offX, offY + targetImgH - bH, offX + lineX1 * scaleNum, offY + targetImgH, 0, lineY2, lineX1, imgH, null);
            // 右下
            g.drawImage(img, offX + targetImgW - rw, offY + targetImgH - bH, offX + targetImgW, offY + targetImgH, lineX2, lineY2, imgW, imgH, null);

            // 再画四条线
            // 上
            g.drawImage(img, offX + lineX1 * scaleNum, offY, offX + targetImgW - rw, offY + lineY1 * scaleNum, lineX1, 0, lineX2, lineY1, null);
            // 下
            g.drawImage(img, offX + lineX1 * scaleNum, offY + targetImgH - bH, offX + targetImgW - rw, offY + targetImgH, lineX1, lineY2, lineX2, imgH, null);
            // 左
            g.drawImage(img, offX, offY + lineY1 * scaleNum, offX + lineX1 * scaleNum, offY + targetImgH - bH, 0, lineY1, lineX1, lineY2, null);
            // 右
            g.drawImage(img, offX + targetImgW - rw, offY + lineY1 * scaleNum, offX + targetImgW, offY + targetImgH - bH, lineX2, lineY1, imgW, lineY2, null);

            // 最后中间
            g.drawImage(img, offX + lineX1 * scaleNum, offY + lineY1 * scaleNum, offX + targetImgW - rw, offY + targetImgH - bH, lineX1, lineY1, lineX2, lineY2, null);
        } else {
            g.drawImage(img, 0, 0, w, h, null);
        }
        g.dispose();
        b.flush();
        ImageIcon imageIcon = new ImageIcon(b);
        return imageIcon;
    }
    //
    // public void getImage(Graphics g, int w, int h) {
    // BufferedImage s = icon.getBufferedImage();
    // int sw = s.getWidth();
    // int sh = s.getHeight();
    //
    // // 四个角
    // g.drawLine(0, 15, w, 15);
    // g.drawLine(x1, 18, w, 18);
    // drawImage(g, s, 0, 0, x, y, 0, 0, x, y, null);
    // drawImage(g, s, w - (sw - x1), 0, w, y, x1, 0, sw, y, null);
    // drawImage(g, s, 0, h - (sh - y1), x, h, 0, y1, x, sh, null);
    // drawImage(g, s, w - (sw - x1), h - (sh - y1), w, h, x1, y1, sw, sh, null);
    // // 上下边
    // drawImage(g, s, x, 0, w - (sw - x1), y, x, 0, x1, y, null);
    // drawImage(g, s, x, h - (sh - y1), w - (sw - x1), h, x, y1, x1, sh, null);
    // // 左右边
    // drawImage(g, s, 0, y, x, h - (sh - y1), 0, y, x, y1, null);
    // drawImage(g, s, w - (sw - x1), y, w, h - (sh - y1), x1, y, sw, sh - y1, null);
    // // 画内心
    // drawImage(g, s, x, y, w - (sw - x1), h - (sh - y1), x, y, x1, y1, null);
    // }

    // private void drawImage(Graphics g, Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
    // System.out.println(String.format("%s,%s,%s,%s", dx1, dy1, dx2, dy2));
    // System.out.println(String.format("%s,%s,%s,%s", sx1, sy1, sx2, sy2));
    // g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
    // }

}
