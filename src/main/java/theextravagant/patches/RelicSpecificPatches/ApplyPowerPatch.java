package theextravagant.patches.RelicSpecificPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theextravagant.relics.RedPhonoblaster;

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
                if (AbstractDungeon.player.hasRelic(RedPhonoblaster.ID) && (powerToApply instanceof StrengthPower) && __instance.amount >= 0) {
                    powerToApply.amount += 1;
                    __instance.amount += 1;
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, new LoseStrengthPower(target, 1)));
                }
            }
        }
    }
}