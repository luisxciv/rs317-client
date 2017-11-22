package com.runescape.client;

import com.runescape.client.io.Stream;

final class ObjectManager {

    private static int anInt123 = (int) (Math.random() * 17D) - 8;
    static int anInt131;
    private static int anInt133 = (int) (Math.random() * 33D) - 16;
    private static final int[] anIntArray137 = {
        1, 0, -1, 0
    };
    private static final int[] orientations2 = {
        16, 32, 64, 128
    };
    private static final int[] anIntArray144 = {
        0, -1, 0, 1
    };
    static int anInt145 = 99;
    static boolean lowMem = true;
    private static final int[] orientations = {
        1, 2, 4, 8
    };

    private static int method170(int i, int j) {
        int k = i + j * 57;
        k = k << 13 ^ k;
        int l = k * (k * k * 15731 + 0xc0ae5) + 0x5208dd0d & 0x7fffffff;
        return l >> 19 & 0xff;
    }

    private static int method172(int i, int j) {
        int k = (method176(i + 45365, j + 0x16713, 4) - 128) + (method176(i + 10294, j + 37821, 2) - 128 >> 1) + (method176(i, j, 1) - 128 >> 2);
        k = (int) ((double) k * 0.29999999999999999D) + 35;
        if (k < 10) {
            k = 10;
        } else if (k > 60) {
            k = 60;
        }
        return k;
    }

    public static void method173(Stream stream, OnDemandFetcher class42_sub1) {
        label0:
        {
            int i = -1;
            do {
                int j = stream.readSmarts();
                if (j == 0) {
                    break label0;
                }
                i += j;
                ObjectDef class46 = ObjectDef.forID(i);
                class46.method574(class42_sub1);
                do {
                    int k = stream.readSmarts();
                    if (k == 0) {
                        break;
                    }
                    stream.readUByte();
                } while (true);
            } while (true);
        }
    }

    private static int method176(int i, int j, int k) {
        int l = i / k;
        int i1 = i & k - 1;
        int j1 = j / k;
        int k1 = j & k - 1;
        int l1 = method186(l, j1);
        int i2 = method186(l + 1, j1);
        int j2 = method186(l, j1 + 1);
        int k2 = method186(l + 1, j1 + 1);
        int l2 = method184(l1, i2, i1, k);
        int i3 = method184(j2, k2, i1, k);
        return method184(l2, i3, k1, k);
    }

    public static boolean method178(int id, int j) {
        ObjectDef def = ObjectDef.forID(id);

        if (j == 11) {
            j = 10;
        }

        if (j >= 5 && j <= 8) {
            j = 4;
        }
        return def.method577(j);
    }

    private static int method184(int i, int j, int k, int l) {
        int i1 = 0x10000 - Texture.anIntArray1471[(k * 1024) / l] >> 1;
        return (i * (0x10000 - i1) >> 16) + (j * i1 >> 16);
    }

    private static int method186(int i, int j) {
        int k = method170(i - 1, j - 1) + method170(i + 1, j - 1) + method170(i - 1, j + 1) + method170(i + 1, j + 1);
        int l = method170(i - 1, j) + method170(i + 1, j) + method170(i, j - 1) + method170(i, j + 1);
        int i1 = method170(i, j);
        return k / 16 + l / 8 + i1 / 4;
    }

    private static int method187(int i, int j) {
        if (i == -1) {
            return 0xbc614e;
        }
        j = (j * (i & 0x7f)) / 128;

        if (j < 2) {
            j = 2;
        } else if (j > 126) {
            j = 126;
        }
        return (i & 0xff80) + j;
    }

    public static void method188(WorldController controller, int primaryOrientationId, int y, int k, int l, ClipMap clipMap, int ai[][][], int x, int objectId, int z) {
        int l1 = ai[l][x][y];
        int i2 = ai[l][x + 1][y];
        int j2 = ai[l][x + 1][y + 1];
        int k2 = ai[l][x][y + 1];
        int l2 = l1 + i2 + j2 + k2 >> 2;
        ObjectDef objDef = ObjectDef.forID(objectId);

        int uid = x + (y << 7) + (objectId << 14) + 0x40000000;

        if (!objDef.hasActions) {
            uid += 0x80000000;
        }
        byte byte1 = (byte) ((primaryOrientationId << 6) + k);

        if (k == 22) {
            Object obj;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj = objDef.method578(22, primaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj = new Animable_Sub5(objectId, primaryOrientationId, 22, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method280(z, l2, y, ((Animable) (obj)), byte1, uid, x);

            if (objDef.aBoolean767 && objDef.hasActions) {
                clipMap.setValue3(y, x);
            }
            return;
        }

        if (k == 10 || k == 11) {
            Object obj1;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj1 = objDef.method578(10, primaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj1 = new Animable_Sub5(objectId, primaryOrientationId, 10, i2, j2, l1, k2, objDef.anInt781, true);
            }

            if (obj1 != null) {
                int j5 = 0;

                if (k == 11) {
                    j5 += 256;
                }
                int k4;
                int i5;

                if (primaryOrientationId == 1 || primaryOrientationId == 3) {
                    k4 = objDef.anInt761;
                    i5 = objDef.anInt744;
                } else {
                    k4 = objDef.anInt744;
                    i5 = objDef.anInt761;
                }
                controller.method284(uid, byte1, l2, i5, ((Animable) (obj1)), k4, z, j5, y, x);
            }

            if (objDef.aBoolean767) {
                clipMap.method212(objDef.aBoolean757, objDef.anInt744, objDef.anInt761, x, y, primaryOrientationId);
            }
            return;
        }

        if (k >= 12) {
            Object obj2;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj2 = objDef.method578(k, primaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj2 = new Animable_Sub5(objectId, primaryOrientationId, k, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method284(uid, byte1, l2, 1, ((Animable) (obj2)), 1, z, 0, y, x);

            if (objDef.aBoolean767) {
                clipMap.method212(objDef.aBoolean757, objDef.anInt744, objDef.anInt761, x, y, primaryOrientationId);
            }
            return;
        }

        if (k == 0) {
            Object obj3;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj3 = objDef.method578(0, primaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj3 = new Animable_Sub5(objectId, primaryOrientationId, 0, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method282(orientations[primaryOrientationId], ((Animable) (obj3)), uid, y, byte1, x, null, l2, 0, z);

            if (objDef.aBoolean767) {
                clipMap.method211(y, primaryOrientationId, x, k, objDef.aBoolean757);
            }
            return;
        }

        if (k == 1) {
            Object obj4;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj4 = objDef.method578(1, primaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj4 = new Animable_Sub5(objectId, primaryOrientationId, 1, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method282(orientations2[primaryOrientationId], ((Animable) (obj4)), uid, y, byte1, x, null, l2, 0, z);

            if (objDef.aBoolean767) {
                clipMap.method211(y, primaryOrientationId, x, k, objDef.aBoolean757);
            }
            return;
        }

        if (k == 2) {
            int secondaryOrientationId = primaryOrientationId + 1 & 3;
            Object obj11;
            Object obj12;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj11 = objDef.method578(2, 4 + primaryOrientationId, l1, i2, j2, k2, -1);
                obj12 = objDef.method578(2, secondaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj11 = new Animable_Sub5(objectId, 4 + primaryOrientationId, 2, i2, j2, l1, k2, objDef.anInt781, true);
                obj12 = new Animable_Sub5(objectId, secondaryOrientationId, 2, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method282(orientations[primaryOrientationId], ((Animable) (obj11)), uid, y, byte1, x, ((Animable) (obj12)), l2, orientations[secondaryOrientationId], z);

            if (objDef.aBoolean767) {
                clipMap.method211(y, primaryOrientationId, x, k, objDef.aBoolean757);
            }
            return;
        }

        if (k == 3) {
            Object obj5;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj5 = objDef.method578(3, primaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj5 = new Animable_Sub5(objectId, primaryOrientationId, 3, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method282(orientations2[primaryOrientationId], ((Animable) (obj5)), uid, y, byte1, x, null, l2, 0, z);

            if (objDef.aBoolean767) {
                clipMap.method211(y, primaryOrientationId, x, k, objDef.aBoolean757);
            }
            return;
        }

        if (k == 9) {
            Object obj6;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj6 = objDef.method578(k, primaryOrientationId, l1, i2, j2, k2, -1);
            } else {
                obj6 = new Animable_Sub5(objectId, primaryOrientationId, k, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method284(uid, byte1, l2, 1, ((Animable) (obj6)), 1, z, 0, y, x);

            if (objDef.aBoolean767) {
                clipMap.method212(objDef.aBoolean757, objDef.anInt744, objDef.anInt761, x, y, primaryOrientationId);
            }
            return;
        }
        if (objDef.aBoolean762) {
            if (primaryOrientationId == 1) {
                int k3 = k2;
                k2 = j2;
                j2 = i2;
                i2 = l1;
                l1 = k3;
            } else if (primaryOrientationId == 2) {
                int l3 = k2;
                k2 = i2;
                i2 = l3;
                l3 = j2;
                j2 = l1;
                l1 = l3;
            } else if (primaryOrientationId == 3) {
                int i4 = k2;
                k2 = l1;
                l1 = i2;
                i2 = j2;
                j2 = i4;
            }
        }
        if (k == 4) {
            Object obj7;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj7 = objDef.method578(4, 0, l1, i2, j2, k2, -1);
            } else {
                obj7 = new Animable_Sub5(objectId, 0, 4, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method283(uid, y, primaryOrientationId * 512, z, 0, l2, ((Animable) (obj7)), x, byte1, 0, orientations[primaryOrientationId]);
            return;
        }
        if (k == 5) {
            int j4 = 16;
            int l4 = controller.getObject1Uid(z, x, y);
            if (l4 > 0) {
                j4 = ObjectDef.forID(l4 >> 14 & 0x7fff).anInt775;
            }
            Object obj13;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj13 = objDef.method578(4, 0, l1, i2, j2, k2, -1);
            } else {
                obj13 = new Animable_Sub5(objectId, 0, 4, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method283(uid, y, primaryOrientationId * 512, z, anIntArray137[primaryOrientationId] * j4, l2, ((Animable) (obj13)), x, byte1, anIntArray144[primaryOrientationId] * j4, orientations[primaryOrientationId]);
            return;
        }
        if (k == 6) {
            Object obj8;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj8 = objDef.method578(4, 0, l1, i2, j2, k2, -1);
            } else {
                obj8 = new Animable_Sub5(objectId, 0, 4, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method283(uid, y, primaryOrientationId, z, 0, l2, ((Animable) (obj8)), x, byte1, 0, 256);
            return;
        }
        if (k == 7) {
            Object obj9;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj9 = objDef.method578(4, 0, l1, i2, j2, k2, -1);
            } else {
                obj9 = new Animable_Sub5(objectId, 0, 4, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method283(uid, y, primaryOrientationId, z, 0, l2, ((Animable) (obj9)), x, byte1, 0, 512);
            return;
        }
        if (k == 8) {
            Object obj10;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj10 = objDef.method578(4, 0, l1, i2, j2, k2, -1);
            } else {
                obj10 = new Animable_Sub5(objectId, 0, 4, i2, j2, l1, k2, objDef.anInt781, true);
            }
            controller.method283(uid, y, primaryOrientationId, z, 0, l2, ((Animable) (obj10)), x, byte1, 0, 768);
        }
    }

    public static boolean method189(int i, byte[] is, int i_250_) // XXX: bad method, decompiled with JODE
    {
        boolean bool = true;
        Stream stream = new Stream(is);
        int i_252_ = -1;
        for (;;) {
            int i_253_ = stream.readSmarts();
            if (i_253_ == 0) {
                break;
            }
            i_252_ += i_253_;
            int i_254_ = 0;
            boolean bool_255_ = false;
            for (;;) {
                if (bool_255_) {
                    int i_256_ = stream.readSmarts();
                    if (i_256_ == 0) {
                        break;
                    }
                    stream.readUByte();
                } else {
                    int i_257_ = stream.readSmarts();
                    if (i_257_ == 0) {
                        break;
                    }
                    i_254_ += i_257_ - 1;
                    int i_258_ = i_254_ & 0x3f;
                    int i_259_ = i_254_ >> 6 & 0x3f;
                    int i_260_ = stream.readUByte() >> 2;
                    int i_261_ = i_259_ + i;
                    int i_262_ = i_258_ + i_250_;
                    if (i_261_ > 0 && i_262_ > 0 && i_261_ < 103 && i_262_ < 103) {
                        ObjectDef class46 = ObjectDef.forID(i_252_);
                        if (i_260_ != 22 || !lowMem || class46.hasActions
                                || class46.aBoolean736) {
                            bool &= class46.method579();
                            bool_255_ = true;
                        }
                    }
                }
            }
        }
        return bool;
    }

    private final int[] anIntArray124;
    private final int[] anIntArray125;
    private final int[] anIntArray126;
    private final int[] anIntArray127;
    private final int[] anIntArray128;
    private final int[][][] anIntArrayArrayArray129;
    private final byte[][][] aByteArrayArrayArray130;
    private final byte[][][] aByteArrayArrayArray134;
    private final int[][][] anIntArrayArrayArray135;
    private final byte[][][] aByteArrayArrayArray136;
    private final int[][] anIntArrayArray139;
    private final byte[][][] aByteArrayArrayArray142;
    private final int regionsX;
    private final int regionsY;
    private final byte[][][] aByteArrayArrayArray148;
    private final byte[][][] aByteArrayArrayArray149;

    public ObjectManager(byte abyte0[][][], int ai[][][]) {
        anInt145 = 99;
        regionsX = 104;
        regionsY = 104;
        anIntArrayArrayArray129 = ai;
        aByteArrayArrayArray149 = abyte0;
        aByteArrayArrayArray142 = new byte[4][regionsX][regionsY];
        aByteArrayArrayArray130 = new byte[4][regionsX][regionsY];
        aByteArrayArrayArray136 = new byte[4][regionsX][regionsY];
        aByteArrayArrayArray148 = new byte[4][regionsX][regionsY];
        anIntArrayArrayArray135 = new int[4][regionsX + 1][regionsY + 1];
        aByteArrayArrayArray134 = new byte[4][regionsX + 1][regionsY + 1];
        anIntArrayArray139 = new int[regionsX + 1][regionsY + 1];
        anIntArray124 = new int[regionsY];
        anIntArray125 = new int[regionsY];
        anIntArray126 = new int[regionsY];
        anIntArray127 = new int[regionsY];
        anIntArray128 = new int[regionsY];
    }

    public final void method171(ClipMap clipMap[], WorldController controller) {
        for (int z1 = 0; z1 < 4; z1++) {
            for (int x1 = 0; x1 < 104; x1++) {
                for (int y1 = 0; y1 < 104; y1++) {
                    if ((aByteArrayArrayArray149[z1][x1][y1] & 1) == 1) {
                        int tmpZ = z1;

                        if ((aByteArrayArrayArray149[1][x1][y1] & 2) == 2) {
                            tmpZ--;
                        }

                        if (tmpZ >= 0) {
                            clipMap[tmpZ].setValue3(y1, x1);
                        }
                    }
                }
            }
        }
        anInt123 += (int) (Math.random() * 5D) - 2;

        if (anInt123 < -8) {
            anInt123 = -8;
        }

        if (anInt123 > 8) {
            anInt123 = 8;
        }
        anInt133 += (int) (Math.random() * 5D) - 2;

        if (anInt133 < -16) {
            anInt133 = -16;
        }

        if (anInt133 > 16) {
            anInt133 = 16;
        }

        for (int z = 0; z < 4; z++) {
            byte abyte0[][] = aByteArrayArrayArray134[z];
            byte byte0 = 96;
            char c = '\u0300';
            byte byte1 = -50;
            byte byte2 = -10;
            byte byte3 = -50;
            int j3 = (int) Math.sqrt(byte1 * byte1 + byte2 * byte2 + byte3 * byte3);
            int l3 = c * j3 >> 8;

            for (int j4 = 1; j4 < regionsY - 1; j4++) {
                for (int j5 = 1; j5 < regionsX - 1; j5++) {
                    int k6 = anIntArrayArrayArray129[z][j5 + 1][j4] - anIntArrayArrayArray129[z][j5 - 1][j4];
                    int l7 = anIntArrayArrayArray129[z][j5][j4 + 1] - anIntArrayArrayArray129[z][j5][j4 - 1];
                    int j9 = (int) Math.sqrt(k6 * k6 + 0x10000 + l7 * l7);
                    int k12 = (k6 << 8) / j9;
                    int l13 = 0x10000 / j9;
                    int j15 = (l7 << 8) / j9;
                    int j16 = byte0 + (byte1 * k12 + byte2 * l13 + byte3 * j15) / l3;
                    int j17 = (abyte0[j5 - 1][j4] >> 2) + (abyte0[j5 + 1][j4] >> 3) + (abyte0[j5][j4 - 1] >> 2) + (abyte0[j5][j4 + 1] >> 3) + (abyte0[j5][j4] >> 1);
                    anIntArrayArray139[j5][j4] = j16 - j17;
                }
            }

            for (int k5 = 0; k5 < regionsY; k5++) {
                anIntArray124[k5] = 0;
                anIntArray125[k5] = 0;
                anIntArray126[k5] = 0;
                anIntArray127[k5] = 0;
                anIntArray128[k5] = 0;
            }

            for (int x = -5; x < regionsX + 5; x++) {
                for (int i8 = 0; i8 < regionsY; i8++) {
                    int k9 = x + 5;

                    if (k9 >= 0 && k9 < regionsX) {
                        int l12 = aByteArrayArrayArray142[z][k9][i8] & 0xff;

                        if (l12 > 0) {
                            Flo flo = Flo.cache[l12 - 1];
                            anIntArray124[i8] += flo.anInt397;
                            anIntArray125[i8] += flo.anInt395;
                            anIntArray126[i8] += flo.anInt396;
                            anIntArray127[i8] += flo.anInt398;
                            anIntArray128[i8]++;
                        }
                    }
                    int i13 = x - 5;

                    if (i13 >= 0 && i13 < regionsX) {
                        int i14 = aByteArrayArrayArray142[z][i13][i8] & 0xff;
                        if (i14 > 0) {
                            Flo flo_1 = Flo.cache[i14 - 1];
                            anIntArray124[i8] -= flo_1.anInt397;
                            anIntArray125[i8] -= flo_1.anInt395;
                            anIntArray126[i8] -= flo_1.anInt396;
                            anIntArray127[i8] -= flo_1.anInt398;
                            anIntArray128[i8]--;
                        }
                    }
                }

                if (x >= 1 && x < regionsX - 1) {
                    int l9 = 0;
                    int j13 = 0;
                    int j14 = 0;
                    int k15 = 0;
                    int k16 = 0;

                    for (int y = -5; y < regionsY + 5; y++) {
                        int j18 = y + 5;

                        if (j18 >= 0 && j18 < regionsY) {
                            l9 += anIntArray124[j18];
                            j13 += anIntArray125[j18];
                            j14 += anIntArray126[j18];
                            k15 += anIntArray127[j18];
                            k16 += anIntArray128[j18];
                        }
                        int k18 = y - 5;

                        if (k18 >= 0 && k18 < regionsY) {
                            l9 -= anIntArray124[k18];
                            j13 -= anIntArray125[k18];
                            j14 -= anIntArray126[k18];
                            k15 -= anIntArray127[k18];
                            k16 -= anIntArray128[k18];
                        }
                        if (y >= 1 && y < regionsY - 1 && (!lowMem || (aByteArrayArrayArray149[0][x][y] & 2) != 0 || (aByteArrayArrayArray149[z][x][y] & 0x10) == 0 && method182(y, z, x) == anInt131)) {
                            if (z < anInt145) {
                                anInt145 = z;
                            }
                            int l18 = aByteArrayArrayArray142[z][x][y] & 0xff;
                            int i19 = aByteArrayArrayArray130[z][x][y] & 0xff;

                            if (l18 > 0 || i19 > 0) {
                                int j19 = anIntArrayArrayArray129[z][x][y];
                                int k19 = anIntArrayArrayArray129[z][x + 1][y];
                                int l19 = anIntArrayArrayArray129[z][x + 1][y + 1];
                                int i20 = anIntArrayArrayArray129[z][x][y + 1];
                                int j20 = anIntArrayArray139[x][y];
                                int k20 = anIntArrayArray139[x + 1][y];
                                int l20 = anIntArrayArray139[x + 1][y + 1];
                                int i21 = anIntArrayArray139[x][y + 1];
                                int j21 = -1;
                                int k21 = -1;

                                if (l18 > 0) {
                                    int l21 = (l9 * 256) / k15;
                                    int j22 = j13 / k16;
                                    int l22 = j14 / k16;
                                    j21 = method177(l21, j22, l22);
                                    l21 = l21 + anInt123 & 0xff;
                                    l22 += anInt133;

                                    if (l22 < 0) {
                                        l22 = 0;
                                    } else if (l22 > 255) {
                                        l22 = 255;
                                    }
                                    k21 = method177(l21, j22, l22);
                                }

                                if (z > 0) {
                                    boolean flag = true;

                                    if (l18 == 0 && aByteArrayArrayArray136[z][x][y] != 0) {
                                        flag = false;
                                    }

                                    if (i19 > 0 && !Flo.cache[i19 - 1].aBoolean393) {
                                        flag = false;
                                    }

                                    if (flag && j19 == k19 && j19 == l19 && j19 == i20) {
                                        anIntArrayArrayArray135[z][x][y] |= 0x924;
                                    }
                                }
                                int i22 = 0;

                                if (j21 != -1) {
                                    i22 = Texture.anIntArray1482[method187(k21, 96)];
                                }

                                if (i19 == 0) {
                                    controller.method279(z, x, y, 0, 0, -1, j19, k19, l19, i20, method187(j21, j20), method187(j21, k20), method187(j21, l20), method187(j21, i21), 0, 0, 0, 0, i22, 0);
                                } else {
                                    int k22 = aByteArrayArrayArray136[z][x][y] + 1;
                                    byte byte4 = aByteArrayArrayArray148[z][x][y];
                                    Flo flo_2 = Flo.cache[i19 - 1];
                                    int i23 = flo_2.anInt391;
                                    int j23;
                                    int k23;
                                    if (i23 >= 0) {
                                        k23 = Texture.method369(i23);
                                        j23 = -1;
                                    } else if (flo_2.anInt390 == 0xff00ff) {
                                        k23 = 0;
                                        j23 = -2;
                                        i23 = -1;
                                    } else {
                                        j23 = method177(flo_2.anInt394, flo_2.anInt395, flo_2.anInt396);
                                        k23 = Texture.anIntArray1482[method185(flo_2.anInt399, 96)];
                                    }
                                    controller.method279(z, x, y, k22, byte4, i23, j19, k19, l19, i20, method187(j21, j20), method187(j21, k20), method187(j21, l20), method187(j21, i21), method185(j23, j20), method185(j23, k20), method185(j23, l20), method185(j23, i21), i22, k23);
                                }
                            }
                        }
                    }

                }
            }

            for (int j8 = 1; j8 < regionsY - 1; j8++) {
                for (int i10 = 1; i10 < regionsX - 1; i10++) {
                    controller.method278(z, i10, j8, method182(j8, z, i10));
                }

            }

        }

        controller.method305(-10, -50, -50);
        for (int j1 = 0; j1 < regionsX; j1++) {
            for (int l1 = 0; l1 < regionsY; l1++) {
                if ((aByteArrayArrayArray149[1][j1][l1] & 2) == 2) {
                    controller.method276(l1, j1);
                }
            }

        }

        int i2 = 1;
        int j2 = 2;
        int k2 = 4;
        for (int l2 = 0; l2 < 4; l2++) {
            if (l2 > 0) {
                i2 <<= 3;
                j2 <<= 3;
                k2 <<= 3;
            }
            for (int i3 = 0; i3 <= l2; i3++) {
                for (int k3 = 0; k3 <= regionsY; k3++) {
                    for (int i4 = 0; i4 <= regionsX; i4++) {
                        if ((anIntArrayArrayArray135[i3][i4][k3] & i2) != 0) {
                            int k4 = k3;
                            int l5 = k3;
                            int i7 = i3;
                            int k8 = i3;
                            for (; k4 > 0 && (anIntArrayArrayArray135[i3][i4][k4 - 1] & i2) != 0; k4--);
                            for (; l5 < regionsY && (anIntArrayArrayArray135[i3][i4][l5 + 1] & i2) != 0; l5++);
                            label0:
                            for (; i7 > 0; i7--) {
                                for (int j10 = k4; j10 <= l5; j10++) {
                                    if ((anIntArrayArrayArray135[i7 - 1][i4][j10] & i2) == 0) {
                                        break label0;
                                    }
                                }

                            }

                            label1:
                            for (; k8 < l2; k8++) {
                                for (int k10 = k4; k10 <= l5; k10++) {
                                    if ((anIntArrayArrayArray135[k8 + 1][i4][k10] & i2) == 0) {
                                        break label1;
                                    }
                                }

                            }

                            int l10 = ((k8 + 1) - i7) * ((l5 - k4) + 1);
                            if (l10 >= 8) {
                                char c1 = '\360';
                                int k14 = anIntArrayArrayArray129[k8][i4][k4] - c1;
                                int l15 = anIntArrayArrayArray129[i7][i4][k4];
                                WorldController.method277(l2, i4 * 128, l15, i4 * 128, l5 * 128 + 128, k14, k4 * 128, 1);
                                for (int l16 = i7; l16 <= k8; l16++) {
                                    for (int l17 = k4; l17 <= l5; l17++) {
                                        anIntArrayArrayArray135[l16][i4][l17] &= ~i2;
                                    }

                                }

                            }
                        }
                        if ((anIntArrayArrayArray135[i3][i4][k3] & j2) != 0) {
                            int l4 = i4;
                            int i6 = i4;
                            int j7 = i3;
                            int l8 = i3;
                            for (; l4 > 0 && (anIntArrayArrayArray135[i3][l4 - 1][k3] & j2) != 0; l4--);
                            for (; i6 < regionsX && (anIntArrayArrayArray135[i3][i6 + 1][k3] & j2) != 0; i6++);
                            label2:
                            for (; j7 > 0; j7--) {
                                for (int i11 = l4; i11 <= i6; i11++) {
                                    if ((anIntArrayArrayArray135[j7 - 1][i11][k3] & j2) == 0) {
                                        break label2;
                                    }
                                }

                            }

                            label3:
                            for (; l8 < l2; l8++) {
                                for (int j11 = l4; j11 <= i6; j11++) {
                                    if ((anIntArrayArrayArray135[l8 + 1][j11][k3] & j2) == 0) {
                                        break label3;
                                    }
                                }

                            }

                            int k11 = ((l8 + 1) - j7) * ((i6 - l4) + 1);
                            if (k11 >= 8) {
                                char c2 = '\360';
                                int l14 = anIntArrayArrayArray129[l8][l4][k3] - c2;
                                int i16 = anIntArrayArrayArray129[j7][l4][k3];
                                WorldController.method277(l2, l4 * 128, i16, i6 * 128 + 128, k3 * 128, l14, k3 * 128, 2);
                                for (int i17 = j7; i17 <= l8; i17++) {
                                    for (int i18 = l4; i18 <= i6; i18++) {
                                        anIntArrayArrayArray135[i17][i18][k3] &= ~j2;
                                    }

                                }

                            }
                        }
                        if ((anIntArrayArrayArray135[i3][i4][k3] & k2) != 0) {
                            int i5 = i4;
                            int j6 = i4;
                            int k7 = k3;
                            int i9 = k3;
                            for (; k7 > 0 && (anIntArrayArrayArray135[i3][i4][k7 - 1] & k2) != 0; k7--);
                            for (; i9 < regionsY && (anIntArrayArrayArray135[i3][i4][i9 + 1] & k2) != 0; i9++);
                            label4:
                            for (; i5 > 0; i5--) {
                                for (int l11 = k7; l11 <= i9; l11++) {
                                    if ((anIntArrayArrayArray135[i3][i5 - 1][l11] & k2) == 0) {
                                        break label4;
                                    }
                                }

                            }

                            label5:
                            for (; j6 < regionsX; j6++) {
                                for (int i12 = k7; i12 <= i9; i12++) {
                                    if ((anIntArrayArrayArray135[i3][j6 + 1][i12] & k2) == 0) {
                                        break label5;
                                    }
                                }

                            }

                            if (((j6 - i5) + 1) * ((i9 - k7) + 1) >= 4) {
                                int j12 = anIntArrayArrayArray129[i3][i5][k7];
                                WorldController.method277(l2, i5 * 128, j12, j6 * 128 + 128, i9 * 128 + 128, j12, k7 * 128, 4);
                                for (int k13 = i5; k13 <= j6; k13++) {
                                    for (int i15 = k7; i15 <= i9; i15++) {
                                        anIntArrayArrayArray135[i3][k13][i15] &= ~k2;
                                    }

                                }

                            }
                        }
                    }

                }

            }

        }
    }

    public final void method174(int i, int j, int l, int i1) {
        for (int j1 = i; j1 <= i + j; j1++) {
            for (int k1 = i1; k1 <= i1 + l; k1++) {
                if (k1 >= 0 && k1 < regionsX && j1 >= 0 && j1 < regionsY) {
                    aByteArrayArrayArray134[0][k1][j1] = 127;
                    if (k1 == i1 && k1 > 0) {
                        anIntArrayArrayArray129[0][k1][j1] = anIntArrayArrayArray129[0][k1 - 1][j1];
                    }
                    if (k1 == i1 + l && k1 < regionsX - 1) {
                        anIntArrayArrayArray129[0][k1][j1] = anIntArrayArrayArray129[0][k1 + 1][j1];
                    }
                    if (j1 == i && j1 > 0) {
                        anIntArrayArrayArray129[0][k1][j1] = anIntArrayArrayArray129[0][k1][j1 - 1];
                    }
                    if (j1 == i + j && j1 < regionsY - 1) {
                        anIntArrayArrayArray129[0][k1][j1] = anIntArrayArrayArray129[0][k1][j1 + 1];
                    }
                }
            }

        }
    }

    private void method175(int y, WorldController controller, ClipMap clipMap, int j, int z, int x, int objectId, int j1) {
        if (lowMem && (aByteArrayArrayArray149[0][x][y] & 2) == 0) {
            if ((aByteArrayArrayArray149[z][x][y] & 0x10) != 0) {
                return;
            }
            if (method182(y, z, x) != anInt131) {
                return;
            }
        }

        if (z < anInt145) {
            anInt145 = z;
        }
        int k1 = anIntArrayArrayArray129[z][x][y];
        int l1 = anIntArrayArrayArray129[z][x + 1][y];
        int i2 = anIntArrayArrayArray129[z][x + 1][y + 1];
        int j2 = anIntArrayArrayArray129[z][x][y + 1];
        int k2 = k1 + l1 + i2 + j2 >> 2;
        ObjectDef objDef = ObjectDef.forID(objectId);
        int uid = x + (y << 7) + (objectId << 14) + 0x40000000;

        if (!objDef.hasActions) {
            uid += 0x80000000;
        }
        byte byte0 = (byte) ((j1 << 6) + j);

        if (j == 22) {
            if (lowMem && !objDef.hasActions && !objDef.aBoolean736) {
                return;
            }
            Object obj;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj = objDef.method578(22, j1, k1, l1, i2, j2, -1);
            } else {
                obj = new Animable_Sub5(objectId, j1, 22, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method280(z, k2, y, ((Animable) (obj)), byte0, uid, x);

            if (objDef.aBoolean767 && objDef.hasActions && clipMap != null) {
                clipMap.setValue3(y, x);
            }
            return;
        }
        if (j == 10 || j == 11) {
            Object obj1;

            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj1 = objDef.method578(10, j1, k1, l1, i2, j2, -1);
            } else {
                obj1 = new Animable_Sub5(objectId, j1, 10, l1, i2, k1, j2, objDef.anInt781, true);
            }

            if (obj1 != null) {
                int i5 = 0;

                if (j == 11) {
                    i5 += 256;
                }
                int j4;
                int l4;

                if (j1 == 1 || j1 == 3) {
                    j4 = objDef.anInt761;
                    l4 = objDef.anInt744;
                } else {
                    j4 = objDef.anInt744;
                    l4 = objDef.anInt761;
                }

                if (controller.method284(uid, byte0, k2, l4, ((Animable) (obj1)), j4, z, i5, y, x) && objDef.aBoolean779) {
                    Model model;

                    if (obj1 instanceof Model) {
                        model = (Model) obj1;
                    } else {
                        model = objDef.method578(10, j1, k1, l1, i2, j2, -1);
                    }

                    if (model != null) {
                        for (int offsetX = 0; offsetX <= j4; offsetX++) {
                            for (int offsetY = 0; offsetY <= l4; offsetY++) {
                                int l5 = model.anInt1650 / 4;

                                if (l5 > 30) {
                                    l5 = 30;
                                }

                                if (l5 > aByteArrayArrayArray134[z][x + offsetX][y + offsetY]) {
                                    aByteArrayArrayArray134[z][x + offsetX][y + offsetY] = (byte) l5;
                                }
                            }
                        }
                    }
                }
            }

            if (objDef.aBoolean767 && clipMap != null) {
                clipMap.method212(objDef.aBoolean757, objDef.anInt744, objDef.anInt761, x, y, j1);
            }
            return;
        }

        if (j >= 12) {
            Object obj2;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj2 = objDef.method578(j, j1, k1, l1, i2, j2, -1);
            } else {
                obj2 = new Animable_Sub5(objectId, j1, j, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method284(uid, byte0, k2, 1, ((Animable) (obj2)), 1, z, 0, y, x);
            if (j >= 12 && j <= 17 && j != 13 && z > 0) {
                anIntArrayArrayArray135[z][x][y] |= 0x924;
            }
            if (objDef.aBoolean767 && clipMap != null) {
                clipMap.method212(objDef.aBoolean757, objDef.anInt744, objDef.anInt761, x, y, j1);
            }
            return;
        }
        if (j == 0) {
            Object obj3;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj3 = objDef.method578(0, j1, k1, l1, i2, j2, -1);
            } else {
                obj3 = new Animable_Sub5(objectId, j1, 0, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method282(orientations[j1], ((Animable) (obj3)), uid, y, byte0, x, null, k2, 0, z);
            if (j1 == 0) {
                if (objDef.aBoolean779) {
                    aByteArrayArrayArray134[z][x][y] = 50;
                    aByteArrayArrayArray134[z][x][y + 1] = 50;
                }
                if (objDef.aBoolean764) {
                    anIntArrayArrayArray135[z][x][y] |= 0x249;
                }
            } else if (j1 == 1) {
                if (objDef.aBoolean779) {
                    aByteArrayArrayArray134[z][x][y + 1] = 50;
                    aByteArrayArrayArray134[z][x + 1][y + 1] = 50;
                }
                if (objDef.aBoolean764) {
                    anIntArrayArrayArray135[z][x][y + 1] |= 0x492;
                }
            } else if (j1 == 2) {
                if (objDef.aBoolean779) {
                    aByteArrayArrayArray134[z][x + 1][y] = 50;
                    aByteArrayArrayArray134[z][x + 1][y + 1] = 50;
                }
                if (objDef.aBoolean764) {
                    anIntArrayArrayArray135[z][x + 1][y] |= 0x249;
                }
            } else if (j1 == 3) {
                if (objDef.aBoolean779) {
                    aByteArrayArrayArray134[z][x][y] = 50;
                    aByteArrayArrayArray134[z][x + 1][y] = 50;
                }
                if (objDef.aBoolean764) {
                    anIntArrayArrayArray135[z][x][y] |= 0x492;
                }
            }
            if (objDef.aBoolean767 && clipMap != null) {
                clipMap.method211(y, j1, x, j, objDef.aBoolean757);
            }
            if (objDef.anInt775 != 16) {
                controller.method290(y, objDef.anInt775, x, z);
            }
            return;
        }
        if (j == 1) {
            Object obj4;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj4 = objDef.method578(1, j1, k1, l1, i2, j2, -1);
            } else {
                obj4 = new Animable_Sub5(objectId, j1, 1, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method282(orientations2[j1], ((Animable) (obj4)), uid, y, byte0, x, null, k2, 0, z);
            if (objDef.aBoolean779) {
                if (j1 == 0) {
                    aByteArrayArrayArray134[z][x][y + 1] = 50;
                } else if (j1 == 1) {
                    aByteArrayArrayArray134[z][x + 1][y + 1] = 50;
                } else if (j1 == 2) {
                    aByteArrayArrayArray134[z][x + 1][y] = 50;
                } else if (j1 == 3) {
                    aByteArrayArrayArray134[z][x][y] = 50;
                }
            }
            if (objDef.aBoolean767 && clipMap != null) {
                clipMap.method211(y, j1, x, j, objDef.aBoolean757);
            }
            return;
        }
        if (j == 2) {
            int i3 = j1 + 1 & 3;
            Object obj11;
            Object obj12;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj11 = objDef.method578(2, 4 + j1, k1, l1, i2, j2, -1);
                obj12 = objDef.method578(2, i3, k1, l1, i2, j2, -1);
            } else {
                obj11 = new Animable_Sub5(objectId, 4 + j1, 2, l1, i2, k1, j2, objDef.anInt781, true);
                obj12 = new Animable_Sub5(objectId, i3, 2, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method282(orientations[j1], ((Animable) (obj11)), uid, y, byte0, x, ((Animable) (obj12)), k2, orientations[i3], z);
            if (objDef.aBoolean764) {
                if (j1 == 0) {
                    anIntArrayArrayArray135[z][x][y] |= 0x249;
                    anIntArrayArrayArray135[z][x][y + 1] |= 0x492;
                } else if (j1 == 1) {
                    anIntArrayArrayArray135[z][x][y + 1] |= 0x492;
                    anIntArrayArrayArray135[z][x + 1][y] |= 0x249;
                } else if (j1 == 2) {
                    anIntArrayArrayArray135[z][x + 1][y] |= 0x249;
                    anIntArrayArrayArray135[z][x][y] |= 0x492;
                } else if (j1 == 3) {
                    anIntArrayArrayArray135[z][x][y] |= 0x492;
                    anIntArrayArrayArray135[z][x][y] |= 0x249;
                }
            }
            if (objDef.aBoolean767 && clipMap != null) {
                clipMap.method211(y, j1, x, j, objDef.aBoolean757);
            }
            if (objDef.anInt775 != 16) {
                controller.method290(y, objDef.anInt775, x, z);
            }
            return;
        }
        if (j == 3) {
            Object obj5;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj5 = objDef.method578(3, j1, k1, l1, i2, j2, -1);
            } else {
                obj5 = new Animable_Sub5(objectId, j1, 3, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method282(orientations2[j1], ((Animable) (obj5)), uid, y, byte0, x, null, k2, 0, z);
            if (objDef.aBoolean779) {
                if (j1 == 0) {
                    aByteArrayArrayArray134[z][x][y + 1] = 50;
                } else if (j1 == 1) {
                    aByteArrayArrayArray134[z][x + 1][y + 1] = 50;
                } else if (j1 == 2) {
                    aByteArrayArrayArray134[z][x + 1][y] = 50;
                } else if (j1 == 3) {
                    aByteArrayArrayArray134[z][x][y] = 50;
                }
            }
            if (objDef.aBoolean767 && clipMap != null) {
                clipMap.method211(y, j1, x, j, objDef.aBoolean757);
            }
            return;
        }
        if (j == 9) {
            Object obj6;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj6 = objDef.method578(j, j1, k1, l1, i2, j2, -1);
            } else {
                obj6 = new Animable_Sub5(objectId, j1, j, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method284(uid, byte0, k2, 1, ((Animable) (obj6)), 1, z, 0, y, x);
            if (objDef.aBoolean767 && clipMap != null) {
                clipMap.method212(objDef.aBoolean757, objDef.anInt744, objDef.anInt761, x, y, j1);
            }
            return;
        }
        if (objDef.aBoolean762) {
            if (j1 == 1) {
                int j3 = j2;
                j2 = i2;
                i2 = l1;
                l1 = k1;
                k1 = j3;
            } else if (j1 == 2) {
                int k3 = j2;
                j2 = l1;
                l1 = k3;
                k3 = i2;
                i2 = k1;
                k1 = k3;
            } else if (j1 == 3) {
                int l3 = j2;
                j2 = k1;
                k1 = l1;
                l1 = i2;
                i2 = l3;
            }
        }
        if (j == 4) {
            Object obj7;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj7 = objDef.method578(4, 0, k1, l1, i2, j2, -1);
            } else {
                obj7 = new Animable_Sub5(objectId, 0, 4, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method283(uid, y, j1 * 512, z, 0, k2, ((Animable) (obj7)), x, byte0, 0, orientations[j1]);
            return;
        }
        if (j == 5) {
            int i4 = 16;
            int k4 = controller.getObject1Uid(z, x, y);
            if (k4 > 0) {
                i4 = ObjectDef.forID(k4 >> 14 & 0x7fff).anInt775;
            }
            Object obj13;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj13 = objDef.method578(4, 0, k1, l1, i2, j2, -1);
            } else {
                obj13 = new Animable_Sub5(objectId, 0, 4, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method283(uid, y, j1 * 512, z, anIntArray137[j1] * i4, k2, ((Animable) (obj13)), x, byte0, anIntArray144[j1] * i4, orientations[j1]);
            return;
        }
        if (j == 6) {
            Object obj8;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj8 = objDef.method578(4, 0, k1, l1, i2, j2, -1);
            } else {
                obj8 = new Animable_Sub5(objectId, 0, 4, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method283(uid, y, j1, z, 0, k2, ((Animable) (obj8)), x, byte0, 0, 256);
            return;
        }
        if (j == 7) {
            Object obj9;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj9 = objDef.method578(4, 0, k1, l1, i2, j2, -1);
            } else {
                obj9 = new Animable_Sub5(objectId, 0, 4, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method283(uid, y, j1, z, 0, k2, ((Animable) (obj9)), x, byte0, 0, 512);
            return;
        }
        if (j == 8) {
            Object obj10;
            if (objDef.anInt781 == -1 && objDef.childrenIDs == null) {
                obj10 = objDef.method578(4, 0, k1, l1, i2, j2, -1);
            } else {
                obj10 = new Animable_Sub5(objectId, 0, 4, l1, i2, k1, j2, objDef.anInt781, true);
            }
            controller.method283(uid, y, j1, z, 0, k2, ((Animable) (obj10)), x, byte0, 0, 768);
        }
    }

    private int method177(int i, int j, int k) {
        if (k > 179) {
            j /= 2;
        }
        if (k > 192) {
            j /= 2;
        }
        if (k > 217) {
            j /= 2;
        }
        if (k > 243) {
            j /= 2;
        }
        return (i / 4 << 10) + (j / 32 << 7) + k / 2;
    }

    public final void method179(int i, int j, ClipMap clipMap[], int x, int i1, byte buf[], int j1, int z, int y) {
        for (int amtX = 0; amtX < 8; amtX++) {
            for (int amtY = 0; amtY < 8; amtY++) {
                if (x + amtX > 0 && x + amtX < 103 && y + amtY > 0 && y + amtY < 103) {
                    clipMap[z].mapFlags[x + amtX][y + amtY] &= 0xfeffffff;
                }
            }
        }
        Stream stream = new Stream(buf);

        for (int l2 = 0; l2 < 4; l2++) {
            for (int i3 = 0; i3 < 64; i3++) {
                for (int j3 = 0; j3 < 64; j3++) {
                    if (l2 == i && i3 >= i1 && i3 < i1 + 8 && j3 >= j1 && j3 < j1 + 8) {
                        method181(y + Class4.method156(j3 & 7, j, i3 & 7), 0, stream, x + Class4.method155(j, j3 & 7, i3 & 7), z, j, 0);
                    } else {
                        method181(-1, 0, stream, -1, 0, 0, 0);
                    }
                }
            }
        }
    }

    public final void method180(byte buf[], int offsetY, int offsetX, int k, int l, ClipMap clipMap[]) {
        for (int z = 0; z < 4; z++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    if (offsetX + x > 0 && offsetX + x < 103 && offsetY + y > 0 && offsetY + y < 103) {
                        clipMap[z].mapFlags[offsetX + x][offsetY + y] &= 0xfeffffff;
                    }
                }
            }
        }
        Stream stream = new Stream(buf);

        for (int z = 0; z < 4; z++) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    method181(y + offsetY, l, stream, x + offsetX, z, 0, k);
                }
            }
        }
    }

    private void method181(int y, int j, Stream stream, int x, int z, int i1, int k1) {
        if (x >= 0 && x < 104 && y >= 0 && y < 104) {
            aByteArrayArrayArray149[z][x][y] = 0;

            do {
                int l1 = stream.readUByte();

                if (l1 == 0) {
                    if (z == 0) {
                        anIntArrayArrayArray129[0][x][y] = -method172(0xe3b7b + x + k1, 0x87cce + y + j) * 8;
                        return;
                    } else {
                        anIntArrayArrayArray129[z][x][y] = anIntArrayArrayArray129[z - 1][x][y] - 240;
                        return;
                    }
                }

                if (l1 == 1) {
                    int j2 = stream.readUByte();

                    if (j2 == 1) {
                        j2 = 0;
                    }

                    if (z == 0) {
                        anIntArrayArrayArray129[0][x][y] = -j2 * 8;
                        return;
                    } else {
                        anIntArrayArrayArray129[z][x][y] = anIntArrayArrayArray129[z - 1][x][y] - j2 * 8;
                        return;
                    }
                }

                if (l1 <= 49) {
                    aByteArrayArrayArray130[z][x][y] = stream.readByte();
                    aByteArrayArrayArray136[z][x][y] = (byte) ((l1 - 2) / 4);
                    aByteArrayArrayArray148[z][x][y] = (byte) ((l1 - 2) + i1 & 3);
                } else if (l1 <= 81) {
                    aByteArrayArrayArray149[z][x][y] = (byte) (l1 - 49);
                } else {
                    aByteArrayArrayArray142[z][x][y] = (byte) (l1 - 81);
                }
            } while (true);
        }

        do {
            int i2 = stream.readUByte();

            if (i2 == 0) {
                break;
            }

            if (i2 == 1) {
                stream.readUByte();
                return;
            }

            if (i2 <= 49) {
                stream.readUByte();
            }
        } while (true);
    }

    private int method182(int i, int j, int k) {
        if ((aByteArrayArrayArray149[j][k][i] & 8) != 0) {
            return 0;
        }
        if (j > 0 && (aByteArrayArrayArray149[1][k][i] & 2) != 0) {
            return j - 1;
        } else {
            return j;
        }
    }

    public final void method183(ClipMap clipMap[], WorldController controller, int i, int j, int k, int l, byte buf[], int i1, int j1, int k1) {
        label0:
        {
            Stream stream = new Stream(buf);
            int objectId = -1;

            do {
                int i2 = stream.readSmarts();

                if (i2 == 0) {
                    break label0;
                }
                objectId += i2;
                int j2 = 0;

                do {
                    int k2 = stream.readSmarts();

                    if (k2 == 0) {
                        break;
                    }
                    j2 += k2 - 1;
                    int l2 = j2 & 0x3f;
                    int i3 = j2 >> 6 & 0x3f;
                    int j3 = j2 >> 12;
                    int k3 = stream.readUByte();
                    int l3 = k3 >> 2;
                    int i4 = k3 & 3;

                    if (j3 == i && i3 >= i1 && i3 < i1 + 8 && l2 >= k && l2 < k + 8) {
                        ObjectDef objDef = ObjectDef.forID(objectId);
                        int x = j + Class4.method157(j1, objDef.anInt761, i3 & 7, l2 & 7, objDef.anInt744);
                        int y = k1 + Class4.method158(l2 & 7, objDef.anInt761, j1, objDef.anInt744, i3 & 7);

                        if (x > 0 && y > 0 && x < 103 && y < 103) {
                            int l4 = j3;

                            if ((aByteArrayArrayArray149[1][x][y] & 2) == 2) {
                                l4--;
                            }
                            ClipMap tmpMap = null;

                            if (l4 >= 0) {
                                tmpMap = clipMap[l4];
                            }
                            method175(y, controller, tmpMap, l3, l, x, objectId, i4 + j1 & 3);
                        }
                    }
                } while (true);
            } while (true);
        }
    }

    private int method185(int i, int j) {
        if (i == -2) {
            return 0xbc614e;
        }

        if (i == -1) {
            if (j < 0) {
                j = 0;
            } else if (j > 127) {
                j = 127;
            }
            j = 127 - j;
            return j;
        }
        j = (j * (i & 0x7f)) / 128;

        if (j < 2) {
            j = 2;
        } else if (j > 126) {
            j = 126;
        }
        return (i & 0xff80) + j;
    }

    public final void method190(int i, ClipMap clipMap[], int j,
            WorldController controller, byte buf[]) {
        label0:
        {
            Stream stream = new Stream(buf);
            int objectId = -1;
            
            do {
                int i1 = stream.readSmarts();
                
                if (i1 == 0) {
                    break label0;
                }
                objectId += i1;
                int j1 = 0;
                
                do {
                    int k1 = stream.readSmarts();
                    if (k1 == 0) {
                        break;
                    }
                    j1 += k1 - 1;
                    int l1 = j1 & 0x3f;
                    int i2 = j1 >> 6 & 0x3f;
                    int z = j1 >> 12;
                    int k2 = stream.readUByte();
                    int l2 = k2 >> 2;
                    int i3 = k2 & 3;
                    int x = i2 + i;
                    int y = l1 + j;

                    if (x > 0 && y > 0 && x < 103 && y < 103) {
                        int z1 = z;

                        if ((aByteArrayArrayArray149[1][x][y] & 2) == 2) {
                            z1--;
                        }
                        ClipMap tmpMap = null;

                        if (z1 >= 0) {
                            tmpMap = clipMap[z1];
                        }
                        method175(y, controller, tmpMap, l2, z, x, objectId, i3);
                    }
                } while (true);
            } while (true);
        }
    }
}