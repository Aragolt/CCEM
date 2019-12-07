package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.patches.EVCardPatches.AbstractCardPatches;

import java.util.Iterator;

public class EnchantIfSkillThenDiscardAction extends AbstractGameAction {
    public EnchantIfSkillThenDiscardAction() {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if (c.type == AbstractCard.CardType.SKILL) {
                AbstractCardPatches.EnchantmentField.EnchantmentField.set(c, true);

            }
            AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(c));
        }
        this.isDone = true;
    }
}

