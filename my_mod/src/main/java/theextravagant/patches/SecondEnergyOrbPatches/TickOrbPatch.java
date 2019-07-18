package theextravagant.patches.SecondEnergyOrbPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theextravagant.theextravagant;

@SpirePatch(clz = CardCrawlGame.class, method = "update")
public class TickOrbPatch {
    @SpirePostfixPatch
    public static void patch(CardCrawlGame __instance) {
        theextravagant.SecondEnergyOrb.tick();
    }
}
