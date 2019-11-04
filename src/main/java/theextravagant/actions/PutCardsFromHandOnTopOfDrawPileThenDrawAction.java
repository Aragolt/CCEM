package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PutCardsFromHandOnTopOfDrawPileThenDrawAction extends AbstractGameAction {
    private int draw;

    public PutCardsFromHandOnTopOfDrawPileThenDrawAction(int draw) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.draw = draw;
    }

    @Override
    public void update() {
        CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            temp.addToTop(c);
        }
        for (AbstractCard c : temp.group) {
            AbstractDungeon.player.hand.moveToDeck(c, false);
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, draw));
        isDone = true;
    }
}
