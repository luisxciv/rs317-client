package com.runescape.client;

import com.runescape.client.io.Stream;
import com.runescape.client.io.StreamLoader;
import java.awt.*;
import java.awt.image.PixelGrabber;

public final class Sprite extends DrawingArea {

    public int[] spritePixels;
    public int spriteWidth;
    public int spriteHeight;
    private int anInt1442;
    private int anInt1443;
    public int anInt1444;
    public int anInt1445;

    public Sprite(int width, int height) {
        spritePixels = new int[width * height];
        spriteWidth = anInt1444 = width;
        spriteHeight = anInt1445 = height;
        anInt1442 = anInt1443 = 0;
    }

    public Sprite(byte imageBuf[], Component component) {
        try {
            // Image image = Toolkit.getDefaultToolkit().getImage(signlink.findcachedir()+"mopar.jpg");
            Image image = Toolkit.getDefaultToolkit().createImage(imageBuf);
            MediaTracker mediatracker = new MediaTracker(component);
            mediatracker.addImage(image, 0);
            mediatracker.waitForAll();
            spriteWidth = image.getWidth(component);
            spriteHeight = image.getHeight(component);
            anInt1444 = spriteWidth;
            anInt1445 = spriteHeight;
            anInt1442 = 0;
            anInt1443 = 0;
            spritePixels = new int[spriteWidth * spriteHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, spriteWidth, spriteHeight, spritePixels, 0, spriteWidth);
            pixelgrabber.grabPixels();
        } catch (Exception _ex) {
            System.out.println("Error converting jpg");
        }
    }

    public Sprite(StreamLoader streamLoader, String s, int i) {
        Stream stream = new Stream(streamLoader.getDataForName(s + ".dat"));
        Stream stream_1 = new Stream(streamLoader.getDataForName("index.dat"));
        stream_1.currentOffset = stream.readUShort();
        anInt1444 = stream_1.readUShort();
        anInt1445 = stream_1.readUShort();
        int j = stream_1.readUByte();
        int ai[] = new int[j];
        for (int k = 0; k < j - 1; k++) {
            ai[k + 1] = stream_1.readUTriByte();
            if (ai[k + 1] == 0) {
                ai[k + 1] = 1;
            }
        }

        for (int l = 0; l < i; l++) {
            stream_1.currentOffset += 2;
            stream.currentOffset += stream_1.readUShort() * stream_1.readUShort();
            stream_1.currentOffset++;
        }

        anInt1442 = stream_1.readUByte();
        anInt1443 = stream_1.readUByte();
        spriteWidth = stream_1.readUShort();
        spriteHeight = stream_1.readUShort();
        int i1 = stream_1.readUByte();
        int pixelCount = spriteWidth * spriteHeight;
        spritePixels = new int[pixelCount];

        if (i1 == 0) {
            for (int k1 = 0; k1 < pixelCount; k1++) {
                spritePixels[k1] = ai[stream.readUByte()];
            }
            return;
        }
        if (i1 == 1) {
            for (int l1 = 0; l1 < spriteWidth; l1++) {
                for (int i2 = 0; i2 < spriteHeight; i2++) {
                    spritePixels[l1 + i2 * spriteWidth] = ai[stream.readUByte()];
                }
            }
        }
    }

    public void initDrawingAreaForSprite() {
        DrawingArea.initDrawingArea(spriteHeight, spriteWidth, spritePixels);
    }

    public void method344(int i, int j, int k) {
        for (int i1 = 0; i1 < spritePixels.length; i1++) {
            int j1 = spritePixels[i1];
            if (j1 != 0) {
                int k1 = j1 >> 16 & 0xff;
                k1 += i;
                if (k1 < 1) {
                    k1 = 1;
                } else if (k1 > 255) {
                    k1 = 255;
                }
                int l1 = j1 >> 8 & 0xff;
                l1 += j;
                if (l1 < 1) {
                    l1 = 1;
                } else if (l1 > 255) {
                    l1 = 255;
                }
                int i2 = j1 & 0xff;
                i2 += k;
                if (i2 < 1) {
                    i2 = 1;
                } else if (i2 > 255) {
                    i2 = 255;
                }
                spritePixels[i1] = (k1 << 16) + (l1 << 8) + i2;
            }
        }
    }

    public void method345() {
        int ai[] = new int[anInt1444 * anInt1445];

        for (int j = 0; j < spriteHeight; j++) {
            System.arraycopy(spritePixels, j * spriteWidth, ai, j + anInt1443 * anInt1444 + anInt1442, spriteWidth);
        }
        spritePixels = ai;
        spriteWidth = anInt1444;
        spriteHeight = anInt1445;
        anInt1442 = 0;
        anInt1443 = 0;
    }

    public void method346(int i, int j) {
        i += anInt1442;
        j += anInt1443;
        int l = i + j * DrawingArea.width;
        int i1 = 0;
        int j1 = spriteHeight;
        int k1 = spriteWidth;
        int l1 = DrawingArea.width - k1;
        int i2 = 0;
        if (j < DrawingArea.topY) {
            int j2 = DrawingArea.topY - j;
            j1 -= j2;
            j = DrawingArea.topY;
            i1 += j2 * k1;
            l += j2 * DrawingArea.width;
        }
        if (j + j1 > DrawingArea.bottomY) {
            j1 -= (j + j1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int k2 = DrawingArea.topX - i;
            k1 -= k2;
            i = DrawingArea.topX;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (i + k1 > DrawingArea.bottomX) {
            int l2 = (i + k1) - DrawingArea.bottomX;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (k1 <= 0 || j1 <= 0) {
        } else {
            method347(l, k1, j1, i2, i1, l1, spritePixels, DrawingArea.pixels);
        }
    }

    private void method347(int i, int j, int k, int l, int i1, int k1, int ai[], int ai1[]) {
        int l1 = -(j >> 2);
        j = -(j & 3);
        for (int i2 = -k; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
            }

            for (int k2 = j; k2 < 0; k2++) {
                ai1[i++] = ai[i1++];
            }

            i += k1;
            i1 += l;
        }
    }

    public void drawSprite1(int i, int j) {
        int k = 128;//was parameter
        i += anInt1442;
        j += anInt1443;
        int i1 = i + j * DrawingArea.width;
        int j1 = 0;
        int k1 = spriteHeight;
        int l1 = spriteWidth;
        int i2 = DrawingArea.width - l1;
        int j2 = 0;
        if (j < DrawingArea.topY) {
            int k2 = DrawingArea.topY - j;
            k1 -= k2;
            j = DrawingArea.topY;
            j1 += k2 * l1;
            i1 += k2 * DrawingArea.width;
        }
        if (j + k1 > DrawingArea.bottomY) {
            k1 -= (j + k1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int l2 = DrawingArea.topX - i;
            l1 -= l2;
            i = DrawingArea.topX;
            j1 += l2;
            i1 += l2;
            j2 += l2;
            i2 += l2;
        }
        if (i + l1 > DrawingArea.bottomX) {
            int i3 = (i + l1) - DrawingArea.bottomX;
            l1 -= i3;
            j2 += i3;
            i2 += i3;
        }
        if (!(l1 <= 0 || k1 <= 0)) {
            method351(j1, l1, DrawingArea.pixels, spritePixels, j2, k1, i2, k, i1);
        }
    }

    public void drawSprite(int i, int k) {
        i += anInt1442;
        k += anInt1443;
        int l = i + k * DrawingArea.width;
        int i1 = 0;
        int j1 = spriteHeight;
        int k1 = spriteWidth;
        int l1 = DrawingArea.width - k1;
        int i2 = 0;
        if (k < DrawingArea.topY) {
            int j2 = DrawingArea.topY - k;
            j1 -= j2;
            k = DrawingArea.topY;
            i1 += j2 * k1;
            l += j2 * DrawingArea.width;
        }
        if (k + j1 > DrawingArea.bottomY) {
            j1 -= (k + j1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int k2 = DrawingArea.topX - i;
            k1 -= k2;
            i = DrawingArea.topX;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (i + k1 > DrawingArea.bottomX) {
            int l2 = (i + k1) - DrawingArea.bottomX;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (!(k1 <= 0 || j1 <= 0)) {
            method349(DrawingArea.pixels, spritePixels, i1, l, k1, j1, l1, i2);
        }
    }

    private void method349(int ai[], int ai1[], int j, int k, int l, int i1, int j1, int k1) {
        int i;//was parameter
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -i1; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }

            for (int k2 = l; k2 < 0; k2++) {
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }

            k += j1;
            j += k1;
        }
    }

    private void method351(int i, int j, int ai[], int ai1[], int l, int i1, int j1, int k1, int l1) {
        int k;//was parameter
        int j2 = 256 - k1;
        for (int k2 = -i1; k2 < 0; k2++) {
            for (int l2 = -j; l2 < 0; l2++) {
                k = ai1[i++];
                if (k != 0) {
                    int i3 = ai[l1];
                    ai[l1++] = ((k & 0xff00ff) * k1 + (i3 & 0xff00ff) * j2 & 0xff00ff00) + ((k & 0xff00) * k1 + (i3 & 0xff00) * j2 & 0xff0000) >> 8;
                } else {
                    l1++;
                }
            }

            l1 += j1;
            i += l;
        }
    }

    public void method352(int i, int j, int ai[], int k, int ai1[], int i1, int j1, int k1, int l1, int i2) {
        try {
            int j2 = -l1 / 2;
            int k2 = -i / 2;
            int l2 = (int) (Math.sin((double) j / 326.11000000000001D) * 65536D);
            int i3 = (int) (Math.cos((double) j / 326.11000000000001D) * 65536D);
            l2 = l2 * k >> 8;
            i3 = i3 * k >> 8;
            int j3 = (i2 << 16) + (k2 * l2 + j2 * i3);
            int k3 = (i1 << 16) + (k2 * i3 - j2 * l2);
            int l3 = k1 + j1 * DrawingArea.width;
            for (j1 = 0; j1 < i; j1++) {
                int i4 = ai1[j1];
                int j4 = l3 + i4;
                int k4 = j3 + i3 * i4;
                int l4 = k3 - l2 * i4;
                for (k1 = -ai[j1]; k1 < 0; k1++) {
                    DrawingArea.pixels[j4++] = spritePixels[(k4 >> 16) + (l4 >> 16) * spriteWidth];
                    k4 += i3;
                    l4 -= l2;
                }

                j3 += l2;
                k3 += i3;
                l3 += DrawingArea.width;
            }

        } catch (Exception _ex) {
        }
    }

    public void method353(int i, double d, int l1) {
        //all of the following were parameters
        int j = 15;
        int k = 20;
        int l = 15;
        int j1 = 256;
        int k1 = 20;
        //all of the previous were parameters
        try {
            int i2 = -k / 2;
            int j2 = -k1 / 2;
            int k2 = (int) (Math.sin(d) * 65536D);
            int l2 = (int) (Math.cos(d) * 65536D);
            k2 = k2 * j1 >> 8;
            l2 = l2 * j1 >> 8;
            int i3 = (l << 16) + (j2 * k2 + i2 * l2);
            int j3 = (j << 16) + (j2 * l2 - i2 * k2);
            int k3 = l1 + i * DrawingArea.width;
            for (i = 0; i < k1; i++) {
                int l3 = k3;
                int i4 = i3;
                int j4 = j3;
                for (l1 = -k; l1 < 0; l1++) {
                    int k4 = spritePixels[(i4 >> 16) + (j4 >> 16) * spriteWidth];
                    if (k4 != 0) {
                        DrawingArea.pixels[l3++] = k4;
                    } else {
                        l3++;
                    }
                    i4 += l2;
                    j4 -= k2;
                }

                i3 += k2;
                j3 += l2;
                k3 += DrawingArea.width;
            }

        } catch (Exception _ex) {
        }
    }

    public void method354(Background background, int i, int j) {
        j += anInt1442;
        i += anInt1443;
        int k = j + i * DrawingArea.width;
        int l = 0;
        int i1 = spriteHeight;
        int j1 = spriteWidth;
        int k1 = DrawingArea.width - j1;
        int l1 = 0;
        if (i < DrawingArea.topY) {
            int i2 = DrawingArea.topY - i;
            i1 -= i2;
            i = DrawingArea.topY;
            l += i2 * j1;
            k += i2 * DrawingArea.width;
        }
        if (i + i1 > DrawingArea.bottomY) {
            i1 -= (i + i1) - DrawingArea.bottomY;
        }
        if (j < DrawingArea.topX) {
            int j2 = DrawingArea.topX - j;
            j1 -= j2;
            j = DrawingArea.topX;
            l += j2;
            k += j2;
            l1 += j2;
            k1 += j2;
        }
        if (j + j1 > DrawingArea.bottomX) {
            int k2 = (j + j1) - DrawingArea.bottomX;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }
        if (!(j1 <= 0 || i1 <= 0)) {
            method355(spritePixels, j1, background.aByteArray1450, i1, DrawingArea.pixels, 0, k1, k, l1, l);
        }
    }

    private void method355(int ai[], int i, byte abyte0[], int j, int ai1[], int k, int l, int i1, int j1, int k1) {
        int l1 = -(i >> 2);
        i = -(i & 3);
        for (int j2 = -j; j2 < 0; j2++) {
            for (int k2 = l1; k2 < 0; k2++) {
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
            }

            for (int l2 = i; l2 < 0; l2++) {
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
            }

            i1 += l;
            k1 += j1;
        }
    }
}
