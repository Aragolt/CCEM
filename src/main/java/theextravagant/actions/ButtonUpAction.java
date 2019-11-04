package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ButtonUpAction extends AbstractGameAction {
    private int block;
    private AbstractCard c;

    public ButtonUpAction(int block, AbstractCard c) {
        this.setValues(null, source, this.amount);
        this.actionType = ActionType.BLOCK;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.block = block;
        this.c = c;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.drawPile.group.contains(c)) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.block));
        }
        this.isDone = true;
    }
}
