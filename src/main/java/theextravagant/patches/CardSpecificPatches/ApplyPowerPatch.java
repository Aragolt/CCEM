package theextravagant.patches.CardSpecificPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.powers.LifeCharmPower;

public class ApplyPowerPatch {
    @SpirePatch(clz = ApplyPowerAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {
            AbstractCreature.class,
            AbstractCreature.class,
            AbstractPower.class,
            int.class,
            boolean.class,
            AbstractGameAction.AttackEffect.class
    })
    public static class ConstructorPatch {
        @SpirePostfixPatch
        public static void Patch(ApplyPowerAction __instance, AbstractCreature target, AbstractCreature source,
                                 AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
            if (!(target == null) && target.isPlayer) {
                if (target.hasPower(LifeCharmPower.POWER_ID) && powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                    __instance.isDone = true;
                }
            }
        }
    }
}