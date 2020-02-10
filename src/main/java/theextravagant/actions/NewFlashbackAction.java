package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.cards.Flashback;

import java.util.Iterator;

public class NewFlashbackAction extends AbstractGameAction {
    private AbstractPlayer p;

    public NewFlashbackAction() {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.p.discardPile.size() > 0) {
            Iterator var1 = this.p.discardPile.group.iterator();

            label21:
            while (true) {
                AbstractCard card;
                do {
                    if (!var1.hasNext()) {
                        break label21;
                    }

                    card = (AbstractCard) var1.next();
                } while (!(card instanceof Flashback));

                this.addToBot(new DiscardToHandAction(card));
            }
        }

        this.tickDuration();
        this.isDone = true;
    }
}
