package CCEMRelics.patches;

import CCEMRelics.actions.PurgeSpecificCardAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class SpectralPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "triggerOnEndOfPlayerTurn"
    )
    public static class SpectralAction {
        @SpirePrefixPatch
        public static SpireReturn Spectral(AbstractCard __instance) {
            if (SpectralField.spectral.get(__instance)) {
                AbstractDungeon.actionManager.addToTop(new PurgeSpecificCardAction(__instance, AbstractDungeon.player.hand));
                __instance.isEthereal = true;
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}