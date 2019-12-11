package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class DrawAndTossAction extends AbstractGameAction {
    public DrawAndTossAction() {
    }

    public void update() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1, new TossAction()));
        this.isDone = true;
    }
}

