package theextravagant.patches.EVCardPatches;

public class SingleCardViewPopupPatches {
    /*
    private static TextureAtlas.AtlasRegion SecondLargeEnergyOrbOnCard = theextravagant.UIAtlas.findRegion("LargeOtherEnergyCard");

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderCost")
    public static class RenderSecondCostPatch {
        private static void renderHelper(SpriteBatch sb, float x, float y, TextureAtlas.AtlasRegion img) {
            if (img != null) {
                sb.draw(img, x + img.offsetX - (float) img.originalWidth / 2.0F, y + img.offsetY - (float) img.originalHeight / 2.0F, (float) img.originalWidth / 2.0F - img.offsetX, (float) img.originalHeight / 2.0F - img.offsetY, (float) img.packedWidth, (float) img.packedHeight, Settings.scale, Settings.scale, 0.0F);
            }
        }

        @SpirePostfixPatch
        public static void patch(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard C = (AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (C instanceof AbstractEVCard) {
                renderHelper(sb, (float) Settings.WIDTH / 2.0F + 270.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F + 380.0F * Settings.scale, SecondLargeEnergyOrbOnCard);
                FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, Integer.toString(((AbstractEVCard) C).Secondcostforturn), 1214.0F * Settings.scale, (float) Settings.HEIGHT / 2.0F + 404.0F * Settings.scale, Color.WHITE);
            }
        }
    }
    */
}
