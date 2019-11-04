package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SparkAction extends AbstractGameAction {
    private int amount2;

    public SparkAction(int amount, int amount2) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.amount = amount;
        this.amount2 = amount2;
    }

    @Override
    public void update() {
        for (AbstractCard C : AbstractDungeon.player.hand.group) {
            C.flash();
            AbstractDungeon.actionManager.addToBottom(new ModifyBlockAction(C.uuid, amount));
            AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(C.uuid, amount2));
        }
        isDone = true;
    }
}
