package CCEMRelics.patches;

import CCEMRelics.relics.JestersCrown;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class JestersCrownPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "applyPowersToBlock"
    )
    public static class applyPowersToBlock {
        @SpirePostfixPatch
        public static void Spectral(AbstractCard __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic(JestersCrown.ID) && __instance.rarity == AbstractCard.CardRarity.COMMON) {
                __instance.block += 1;
                __instance.isBlockModified = true;
            }
        }
    }
}
