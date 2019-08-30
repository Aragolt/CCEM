package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PluckAction extends AbstractGameAction {
    private int number;
    private AbstractPlayer p;

    public PluckAction(int number, AbstractPlayer p) {
        this.number = number;
        this.actionType = ActionType.BLOCK;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.p = p;
    }

    @Override
    public void update() {
        for (int i = 0; i < number; i++) {
            if (p.drawPile.getNCardFromTop(i).cost > 0) {
                p.drawPile.getNCardFromTop(i).costForTurn = 0;
            }
            p.drawPile.getNCardFromTop(i).exhaust = true;
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, number));
        isDone = true;
    }
}
