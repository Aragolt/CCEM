package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class RecursiveTossAction extends AbstractGameAction {
    int amount;

    public RecursiveTossAction(int amount) {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.amount = amount;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            AbstractDungeon.actionManager.addToBottom(new DiscardSpecificCardAction(c));
            amount--;
            if (amount > 0) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1, new RecursiveTossAction(amount)));
            }
        }
        this.isDone = true;
    }
}

