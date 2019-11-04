package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlayCardTwiceAction extends AbstractGameAction {
    private AbstractCard card;
    private UseCardAction action;
    private boolean hasbeenplayedalready;

    public PlayCardTwiceAction(AbstractCard c, UseCardAction action) {
        this.setValues(null, source, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.card = c;
        this.action = action;

    }

    @Override
    public void update() {
        AbstractMonster m = null;
        if (action.target != null) {
            m = (AbstractMonster) action.target;
        }

        AbstractCard tmp = card.makeSameInstanceOf();
        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y;
        tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = (float) Settings.HEIGHT / 2.0F;
        if (tmp.cost > 0) {
            tmp.freeToPlayOnce = true;
        }

        if (m != null) {
            tmp.calculateCardDamage(m);
        }

        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse, true, true));
        //AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse, true));
        AbstractDungeon.actionManager.addToBottom(new PlayCardAction(tmp, new UseCardAction(tmp, m)));
        isDone = true;
    }
}