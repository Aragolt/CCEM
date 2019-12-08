package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.util.Iterator;

public class PluckAction extends AbstractGameAction {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("theextravagant:ExhaustingCard");


    public PluckAction() {
        this.actionType = ActionType.BLOCK;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            c.freeToPlayOnce = true;
            c.exhaust = true;
            c.rawDescription += cardStrings.DESCRIPTION;
            c.initializeDescription();
        }
        this.isDone = true;
    }
}
