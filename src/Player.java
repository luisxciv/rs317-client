package com.runescape.client;

import com.runescape.client.io.Stream;

public final class Player extends Entity {

    static MRUNodes mruNodes = new MRUNodes(260);

    public int privilege;

    private long aLong1697;
    public EntityDef def;
    boolean aBoolean1699;
    final int[] anIntArray1700;
    public int team;
    private int anInt1702;
    public String name;
    public int combatLevel;
    public int headIcon;
    public int anInt1707;
    int anInt1708;
    int anInt1709;
    boolean visible;
    int anInt1711;
    int anInt1712;
    int anInt1713;
    Model aModel_1714;
    private int anInt1715;
    public final int[] equipment;
    private long aLong1718;
    int anInt1719;
    int anInt1720;
    int anInt1721;
    int anInt1722;
    int skill;

    Player() {
        aLong1697 = -1L;
        aBoolean1699 = false;
        anIntArray1700 = new int[5];
        visible = false;
        anInt1715 = 9;
        equipment = new int[12];
    }

    public Model getRotatedModel() {
        if (!visible) {
            return null;
        }
        Model model = method452();

        if (model == null) {
            return null;
        }
        super.height = model.modelHeight;
        model.aBoolean1659 = true;

        if (aBoolean1699) {
            return model;
        }
        if (super.currentGraphicsId != -1 && super.anInt1521 != -1) {
            StaticAnimation staticAnimation = StaticAnimation.cache[super.currentGraphicsId];
            Model model_2 = staticAnimation.getModel();

            if (model_2 != null) {
                Model model_3 = new Model(true, Class36.method532(super.anInt1521), false, model_2);
                model_3.method475(0, -super.currentGraphicsDelaySegment, 0);
                model_3.method469();
                model_3.method470(staticAnimation.anim.anIntArray353[super.anInt1521]);
                model_3.anIntArrayArray1658 = null;
                model_3.anIntArrayArray1657 = null;

                if (staticAnimation.anInt410 != 128 || staticAnimation.anInt411 != 128) {
                    model_3.method478(staticAnimation.anInt410, staticAnimation.anInt410, staticAnimation.anInt411);
                }
                model_3.method479(64 + staticAnimation.anInt413, 850 + staticAnimation.anInt414, -30, -50, -30, true);
                Model models[] = {
                    model, model_3
                };
                model = new Model(models);
            }
        }

        if (aModel_1714 != null) {
            if (Client.loopCycle >= anInt1708) {
                aModel_1714 = null;
            }

            if (Client.loopCycle >= anInt1707 && Client.loopCycle < anInt1708) {
                Model model_1 = aModel_1714;
                model_1.method475(anInt1711 - super.x, anInt1712 - anInt1709, anInt1713 - super.y);

                if (super.turnDirection == 512) {
                    model_1.method473();
                    model_1.method473();
                    model_1.method473();
                } else if (super.turnDirection == 1024) {
                    model_1.method473();
                    model_1.method473();
                } else if (super.turnDirection == 1536) {
                    model_1.method473();
                }
                Model models[] = {
                    model, model_1
                };
                model = new Model(models);
                if (super.turnDirection == 512) {
                    model_1.method473();
                } else if (super.turnDirection == 1024) {
                    model_1.method473();
                    model_1.method473();
                } else if (super.turnDirection == 1536) {
                    model_1.method473();
                    model_1.method473();
                    model_1.method473();
                }
                model_1.method475(super.x - anInt1711, anInt1709 - anInt1712, super.y - anInt1713);
            }
        }
        model.aBoolean1659 = true;
        return model;
    }

    public void updatePlayer(Stream stream) {
        stream.currentOffset = 0;
        anInt1702 = stream.readUByte();
        headIcon = stream.readUByte();
        def = null;
        team = 0;
        for (int j = 0; j < 12; j++) {
            int k = stream.readUByte();
            if (k == 0) {
                equipment[j] = 0;
                continue;
            }
            int i1 = stream.readUByte();
            equipment[j] = (k << 8) + i1;
            if (j == 0 && equipment[0] == 65535) {
                def = EntityDef.forID(stream.readUShort());
                break;
            }
            if (equipment[j] >= 512 && equipment[j] - 512 < ItemDef.totalItems) {
                int l1 = ItemDef.forID(equipment[j] - 512).team;
                if (l1 != 0) {
                    team = l1;
                }
            }
        }

        for (int l = 0; l < 5; l++) {
            int j1 = stream.readUByte();
            if (j1 < 0 || j1 >= Client.anIntArrayArray1003[l].length) {
                j1 = 0;
            }
            anIntArray1700[l] = j1;
        }

        super.anInt1511 = stream.readUShort();
        if (super.anInt1511 == 65535) {
            super.anInt1511 = -1;
        }
        super.anInt1512 = stream.readUShort();
        if (super.anInt1512 == 65535) {
            super.anInt1512 = -1;
        }
        super.anInt1554 = stream.readUShort();
        if (super.anInt1554 == 65535) {
            super.anInt1554 = -1;
        }
        super.anInt1555 = stream.readUShort();
        if (super.anInt1555 == 65535) {
            super.anInt1555 = -1;
        }
        super.anInt1556 = stream.readUShort();
        if (super.anInt1556 == 65535) {
            super.anInt1556 = -1;
        }
        super.anInt1557 = stream.readUShort();
        if (super.anInt1557 == 65535) {
            super.anInt1557 = -1;
        }
        super.anInt1505 = stream.readUShort();
        if (super.anInt1505 == 65535) {
            super.anInt1505 = -1;
        }
        name = StringHelper.fixName(StringHelper.nameForLong(stream.readULong()));
        combatLevel = stream.readUByte();
        skill = stream.readUShort();
        visible = true;
        aLong1718 = 0L;
        for (int k1 = 0; k1 < 12; k1++) {
            aLong1718 <<= 4;
            if (equipment[k1] >= 256) {
                aLong1718 += equipment[k1] - 256;
            }
        }

        if (equipment[0] >= 256) {
            aLong1718 += equipment[0] - 256 >> 4;
        }
        if (equipment[1] >= 256) {
            aLong1718 += equipment[1] - 256 >> 8;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            aLong1718 <<= 3;
            aLong1718 += anIntArray1700[i2];
        }
        aLong1718 <<= 1;
        aLong1718 += anInt1702;
    }

    private Model method452() {
        if (def != null) {
            int j = -1;
            if (super.currentAnimationId >= 0 && super.anInt1529 == 0) {
                j = Animation.anims[super.currentAnimationId].anIntArray353[super.anInt1527];
            } else if (super.anInt1517 >= 0) {
                j = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }
            Model model = def.method164(-1, j, null);
            return model;
        }
        long l = aLong1718;
        int k = -1;
        int i1 = -1;
        int j1 = -1;
        int k1 = -1;
        if (super.currentAnimationId >= 0 && super.anInt1529 == 0) {
            Animation animation = Animation.anims[super.currentAnimationId];
            k = animation.anIntArray353[super.anInt1527];
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                i1 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }
            if (animation.anInt360 >= 0) {
                j1 = animation.anInt360;
                l += j1 - equipment[5] << 40;
            }
            if (animation.anInt361 >= 0) {
                k1 = animation.anInt361;
                l += k1 - equipment[3] << 48;
            }
        } else if (super.anInt1517 >= 0) {
            k = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
        }
        Model model_1 = (Model) mruNodes.insertFromCache(l);
        if (model_1 == null) {
            boolean flag = false;
            for (int i2 = 0; i2 < 12; i2++) {
                int k2 = equipment[i2];
                if (k1 >= 0 && i2 == 3) {
                    k2 = k1;
                }
                if (j1 >= 0 && i2 == 5) {
                    k2 = j1;
                }
                if (k2 >= 256 && k2 < 512 && !IDK.cache[k2 - 256].method537()) {
                    flag = true;
                }
                if (k2 >= 512 && !ItemDef.forID(k2 - 512).method195(anInt1702)) {
                    flag = true;
                }
            }

            if (flag) {
                if (aLong1697 != -1L) {
                    model_1 = (Model) mruNodes.insertFromCache(aLong1697);
                }
                if (model_1 == null) {
                    return null;
                }
            }
        }
        if (model_1 == null) {
            Model models[] = new Model[12];
            int amount = 0;

            for (int l2 = 0; l2 < 12; l2++) {
                int i3 = equipment[l2];
                if (k1 >= 0 && l2 == 3) {
                    i3 = k1;
                }
                if (j1 >= 0 && l2 == 5) {
                    i3 = j1;
                }
                if (i3 >= 256 && i3 < 512) {
                    Model model_3 = IDK.cache[i3 - 256].method538();
                    if (model_3 != null) {
                        models[amount++] = model_3;
                    }
                }
                if (i3 >= 512) {
                    Model model_4 = ItemDef.forID(i3 - 512).method196(anInt1702);
                    if (model_4 != null) {
                        models[amount++] = model_4;
                    }
                }
            }
            model_1 = new Model(amount, models);

            for (int j3 = 0; j3 < 5; j3++) {
                if (anIntArray1700[j3] != 0) {
                    model_1.method476(Client.anIntArrayArray1003[j3][0], Client.anIntArrayArray1003[j3][anIntArray1700[j3]]);
                    if (j3 == 1) {
                        model_1.method476(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j3]]);
                    }
                }
            }
            model_1.method469();
            model_1.method479(64, 850, -30, -50, -30, true);
            mruNodes.removeFromCache(model_1, l);
            aLong1697 = l;
        }
        if (aBoolean1699) {
            return model_1;
        }
        Model model_2 = Model.aModel_1621;
        model_2.method464(model_1, Class36.method532(k) & Class36.method532(i1));
        if (k != -1 && i1 != -1) {
            model_2.method471(Animation.anims[super.currentAnimationId].anIntArray357, i1, k);
        } else if (k != -1) {
            model_2.method470(k);
        }
        model_2.method466();
        model_2.anIntArrayArray1658 = null;
        model_2.anIntArrayArray1657 = null;
        return model_2;
    }

    public boolean isVisible() {
        return visible;
    }

    public Model method453() {
        if (!visible) {
            return null;
        }
        
        if (def != null) {
            return def.method160();
        }
        boolean flag = false;
        
        for (int i = 0; i < 12; i++) {
            int id = equipment[i];
            
            if (id >= 256 && id < 512 && !IDK.cache[id - 256].method539()) {
                flag = true;
            }
            
            if (id >= 512 && !ItemDef.forID(id - 512).method192(anInt1702)) {
                flag = true;
            }
        }

        if (flag) {
            return null;
        }
        Model models[] = new Model[12];
        int amount = 0;
        
        for (int l = 0; l < 12; l++) {
            int id = equipment[l];
            
            if (id >= 256 && id < 512) {
                Model model_1 = IDK.cache[id - 256].method540();
                
                if (model_1 != null) {
                    models[amount++] = model_1;
                }
            }
            
            if (id >= 512) {
                Model model_2 = ItemDef.forID(id - 512).method194(anInt1702);
                
                if (model_2 != null) {
                    models[amount++] = model_2;
                }
            }
        }
        Model model = new Model(amount, models);
        
        for (int j1 = 0; j1 < 5; j1++) {
            if (anIntArray1700[j1] != 0) {
                model.method476(Client.anIntArrayArray1003[j1][0], Client.anIntArrayArray1003[j1][anIntArray1700[j1]]);
                
                if (j1 == 1) {
                    model.method476(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j1]]);
                }
            }
        }
        return model;
    }
}
