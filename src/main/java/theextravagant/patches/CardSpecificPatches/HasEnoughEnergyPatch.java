package theextravagant.patches.CardSpecificPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.cards.TwilightTone;

public class HasEnoughEnergyPatch {
    @SpirePatch(clz = AbstractCard.class, method = "hasEnoughEnergy")
    public static class TwilightTonePatch {
        @SpirePrefixPatch
        public static SpireReturn Patch(AbstractCard __instance) {
            if (!(__instance.type == AbstractCard.CardType.ATTACK) && AbstractDungeon.player.discardPile.findCardById(TwilightTone.ID) != null) {
                return SpireReturn.Return(false);
            }
            return SpireReturn.Continue();
        }
    }
}
