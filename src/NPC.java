package com.runescape.client;

public final class NPC extends Entity {

    public EntityDef desc;

    NPC() {
    }

    private Model method450() {
        if (super.currentAnimationId >= 0 && super.anInt1529 == 0) {
            int k = Animation.anims[super.currentAnimationId].anIntArray353[super.anInt1527];
            int i1 = -1;
            
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                i1 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }
            return desc.method164(i1, k, Animation.anims[super.currentAnimationId].anIntArray357);
        }
        int l = -1;
        
        if (super.anInt1517 >= 0) {
            l = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
        }
        return desc.method164(-1, l, null);
    }

    public Model getRotatedModel() {
        if (desc == null) {
            return null;
        }
        Model model = method450();
        
        if (model == null) {
            return null;
        }
        super.height = model.modelHeight;
        
        if (super.currentGraphicsId != -1 && super.anInt1521 != -1) {
            StaticAnimation staticAnimation = StaticAnimation.cache[super.currentGraphicsId];
            Model model_1 = staticAnimation.getModel();
            if (model_1 != null) {
                int j = staticAnimation.anim.anIntArray353[super.anInt1521];
                Model model_2 = new Model(true, Class36.method532(j), false, model_1);
                model_2.method475(0, -super.currentGraphicsDelaySegment, 0);
                model_2.method469();
                model_2.method470(j);
                model_2.anIntArrayArray1658 = null;
                model_2.anIntArrayArray1657 = null;
                if (staticAnimation.anInt410 != 128 || staticAnimation.anInt411 != 128) {
                    model_2.method478(staticAnimation.anInt410, staticAnimation.anInt410, staticAnimation.anInt411);
                }
                model_2.method479(64 + staticAnimation.anInt413, 850 + staticAnimation.anInt414, -30, -50, -30, true);
                Model aModel[] = {
                    model, model_2
                };
                model = new Model(aModel);
            }
        }
        if (desc.aByte68 == 1) {
            model.aBoolean1659 = true;
        }
        return model;
    }

    public boolean isVisible() {
        return desc != null;
    }

}
