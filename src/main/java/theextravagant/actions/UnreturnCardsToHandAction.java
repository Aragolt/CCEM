package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static theextravagant.powers.VintagePower.cardsthatshouldnotreturn;

public class UnreturnCardsToHandAction extends AbstractGameAction {
    public UnreturnCardsToHandAction() {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    public void update() {
        for (AbstractCard c : cardsthatshouldnotreturn) {
            c.returnToHand = false;
        }
        this.isDone = true;
    }
}
