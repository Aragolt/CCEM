package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.Iterator;

public class RecursiveTrickeryAction extends AbstractGameAction {
    int amount;

    public RecursiveTrickeryAction(int amount) {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.amount = amount;
    }

    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            AbstractDungeon.effectsQueue.add(new ShowCardBrieflyEffect(c.makeStatEquivalentCopy(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
            AbstractDungeon.player.hand.moveToBottomOfDeck(c);
            amount--;
            if (amount > 0) {
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1, new RecursiveTrickeryAction(amount)));
            }
        }
        this.isDone = true;
    }
}

