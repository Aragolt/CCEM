package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RavageAction extends AbstractGameAction {
    private AbstractCard card;
    private int count;

    public RavageAction(AbstractCard card, int count) {
        this.card = card;
        this.count = count;
    }

    @Override
    public void update() {
        card.applyPowers();
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, card.multiDamage, card.damageTypeForTurn, AttackEffect.NONE));
        AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(card.uuid, 1));
        if (count > 0) {
            AbstractDungeon.actionManager.addToBottom(new RavageAction(card, count - 1));
        }
        this.isDone = true;
    }
}
