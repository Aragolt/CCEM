package theextravagant.patches.EVCardPatches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import theextravagant.theextravagant;

import static theextravagant.patches.EVCardPatches.CardcrawlgameTimedelayPatch.elapsedtimewithPiresest;

public class AbstractCardPatches {
    /* private static TextureAtlas.AtlasRegion SecondEnergyOrbOnCard = theextravagant.UIAtlas.findRegion("OtherEnergyCard");

    @SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
    public static class SecondEnergyRenderPatch {
        @SpirePostfixPatch
        public static void patch(AbstractCard __instance, SpriteBatch sb) {
            if (__instance instanceof AbstractEVCard) {
                FontHelper.cardEnergyFont_L.getData().setScale(__instance.drawScale);
                renderHelper(sb, Color.WHITE, SecondEnergyOrbOnCard, __instance.current_x, __instance.current_y, __instance);
                FontHelper.renderRotatedText(sb, FontHelper.cardEnergyFont_L, Integer.toString(((AbstractEVCard) __instance).Secondcostforturn), __instance.current_x, __instance.current_y, 135.0F * __instance.drawScale * Settings.scale, 192.0F * __instance.drawScale * Settings.scale, __instance.angle, false, Color.WHITE);
            }
        }

        private static void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard C) {
            sb.setColor(color);
            sb.draw(img, drawX + img.offsetX - (float) img.originalWidth / 2.0F, drawY + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, C.drawScale * Settings.scale, C.drawScale * Settings.scale, C.angle);
        }
    }*/
    static int elapsedtime = 0;
    private static TextureAtlas.AtlasRegion GlitterA = theextravagant.UIAtlas.findRegion("EnchantmentGlow1");
    private static TextureAtlas.AtlasRegion GlitterB = theextravagant.UIAtlas.findRegion("EnchantmentGlow2");
    private static TextureAtlas.AtlasRegion GlitterC = theextravagant.UIAtlas.findRegion("EnchantmentGlow3");

    @SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class EnchantmentField {
        public static SpireField<Boolean> EnchantmentField = new SpireField<Boolean>(() -> false);
    }

    /*
        @SpirePatch(clz = AbstractCard.class, method = "renderHelper",  paramtypez={
                SpriteBatch.class,
                Color.class,
                TextureAtlas.AtlasRegion.class,
                float.class,
                float.class,
        })
        public static class RenderhelperPatch1
        {
            @SpirePostfixPatch
            public static void RenderhelperPatch1(AbstractCard __instance, SpriteBatch sb, Color C, TextureAtlas.AtlasRegion img, float drawX, float drawY)
            {
                Color c = new Color(Color.WHITE.cpy());
                c.a = (float) (1 + Math.sin(elapsedtimewithPiresest))/2;
                sb.setColor(c);
                if(EnchantmentField.EnchantmentField.get(__instance))
                {
                    sb.draw(GlitterA, drawX + img.offsetX - (float)img.originalWidth / 2.0F, drawY + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
                }
                c.a = (float) (1 - Math.cos(elapsedtimewithPiresest))/2;
                sb.setColor(c);
                if(EnchantmentField.EnchantmentField.get(__instance))
                {
                    sb.draw(GlitterB, drawX + img.offsetX - (float)img.originalWidth / 2.0F, drawY + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
                }
                c.a = (float) (1 - Math.sin(elapsedtimewithPiresest))/2;
                sb.setColor(c);
                if(EnchantmentField.EnchantmentField.get(__instance))
                {
                    sb.draw(GlitterC, drawX + img.offsetX - (float)img.originalWidth / 2.0F, drawY + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
                }
            }
        }
        */
    @SpirePatch(clz = AbstractCard.class, method = "renderCard")
    public static class RenderhelperPatch1 {
        @SpirePostfixPatch
        public static void RenderhelperPatch1(AbstractCard __instance, SpriteBatch sb, boolean selected, boolean hovered) {
            Color c = new Color(Color.WHITE.cpy());
            c.a = (float) (1 + Math.sin(elapsedtimewithPiresest)) / 2;
            sb.setColor(c);
            if (EnchantmentField.EnchantmentField.get(__instance)) {
                sb.draw(GlitterA, __instance.current_x + GlitterA.offsetX - (float) GlitterA.originalWidth / 2.0F, __instance.current_y + GlitterA.offsetY - (float) GlitterA.originalHeight / 2.0F, (float) GlitterA.originalWidth / 2.0F - GlitterA.offsetX, (float) GlitterA.originalHeight / 2.0F - GlitterA.offsetY, (float) GlitterA.packedWidth, (float) GlitterA.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
            }
            c.a = (float) (1 - Math.cos(elapsedtimewithPiresest)) / 2;
            sb.setColor(c);
            if (EnchantmentField.EnchantmentField.get(__instance)) {
                sb.draw(GlitterB, __instance.current_x + GlitterB.offsetX - (float) GlitterB.originalWidth / 2.0F, __instance.current_y + GlitterB.offsetY - (float) GlitterB.originalHeight / 2.0F, (float) GlitterB.originalWidth / 2.0F - GlitterB.offsetX, (float) GlitterB.originalHeight / 2.0F - GlitterB.offsetY, (float) GlitterB.packedWidth, (float) GlitterB.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
            }
            c.a = (float) (1 - Math.sin(elapsedtimewithPiresest)) / 2;
            sb.setColor(c);
            if (EnchantmentField.EnchantmentField.get(__instance)) {
                sb.draw(GlitterC, __instance.current_x + GlitterC.offsetX - (float) GlitterC.originalWidth / 2.0F, __instance.current_y + GlitterC.offsetY - (float) GlitterC.originalHeight / 2.0F, (float) GlitterC.originalWidth / 2.0F - GlitterC.offsetX, (float) GlitterC.originalHeight / 2.0F - GlitterC.offsetY, (float) GlitterC.packedWidth, (float) GlitterC.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy")
    public static class CopyPatch1 {
        @SpirePostfixPatch
        public static AbstractCard RenderhelperPatch1(AbstractCard card, AbstractCard __instance) {
            if (EnchantmentField.EnchantmentField.get(__instance)) {
                EnchantmentField.EnchantmentField.set(card, true);
            }
            return card;
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "makeSameInstanceOf")
    public static class CopyPatch2 {
        @SpirePostfixPatch
        public static AbstractCard RenderhelperPatch1(AbstractCard card, AbstractCard __instance) {
            EnchantmentField.EnchantmentField.set(card, false);
            return card;
        }
    }
}
