package theextravagant.patches.EVCardPatches;

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
}
