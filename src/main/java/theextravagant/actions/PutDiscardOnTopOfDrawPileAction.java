package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PutDiscardOnTopOfDrawPileAction extends AbstractGameAction {
    private int draw;

    public PutDiscardOnTopOfDrawPileAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            temp.addToBottom(c);
        }
        for (AbstractCard c : temp.group) {
            AbstractDungeon.player.discardPile.moveToDeck(c, false);
        }
        isDone = true;
    }
}
