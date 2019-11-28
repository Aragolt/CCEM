package theextravagant.patches.EVCardPatches;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theextravagant.actions.PlayCardAction;
import theextravagant.powers.AuroraFormPower;

@SpirePatch(clz = CardQueueItem.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractMonster.class, int.class, boolean.class, boolean.class})
public class PlayCardActionPatch {
    @SpirePostfixPatch
    public static void Patch(CardQueueItem __instance, AbstractCard abstractCard, AbstractMonster target, int setEnergyOnUse, boolean ignoreEnergyTotal, boolean autoplayCard) {
        if (/*!abstractCard.isInAutoplay && */ AbstractCardPatches.EnchantmentField.EnchantmentField.get(abstractCard)) {
            AbstractDungeon.actionManager.addToBottom(new PlayCardAction(abstractCard, new UseCardAction(abstractCard, target)));
            if (AbstractDungeon.player.hasPower(AuroraFormPower.POWER_ID)) {
                for (int i = 0; i < AbstractDungeon.player.getPower(AuroraFormPower.POWER_ID).amount; i++) {
                    AbstractDungeon.actionManager.addToBottom(new PlayCardAction(abstractCard, new UseCardAction(abstractCard, target)));
                }
            }
        }
    }
}
