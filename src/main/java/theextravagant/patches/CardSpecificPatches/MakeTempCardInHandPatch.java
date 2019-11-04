package theextravagant.patches.CardSpecificPatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.cards.TwilightTone;
import theextravagant.powers.ModifyDamagePower;

@SpirePatch(clz = MakeTempCardInHandAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {
        AbstractCard.class,
        int.class
})
public class MakeTempCardInHandPatch {
    @SpirePostfixPatch
    public static void Patch(MakeTempCardInHandAction __instance, AbstractCard c, int amount) {
        if (c instanceof TwilightTone) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ModifyDamagePower()));
        }
    }
}
