package theextravagant.patches.EVCardPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.cards.AbstractEVCard;
import theextravagant.theextravagant;

public class AbstractPlayerPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class RenderSecondCostPatch {
        @SpirePostfixPatch
        public static void patch(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if(c instanceof AbstractEVCard && ((AbstractEVCard) c).Secondcostforturn > 0)
            {
                theextravagant.SecondEnergyOrb.currentEnergy -= ((AbstractEVCard) c).Secondcostforturn;
            }
        }
    }
}
