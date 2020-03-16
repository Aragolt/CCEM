package CCEMRelics.actions;

import CCEMRelics.patches.ChainField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

public class PutAtBottomOfDrawPileAndApplyChainAction extends AbstractGameAction {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;
    private static final CardStrings cardStringsPurge = CardCrawlGame.languagePack.getCardStrings("chaincard");
    public static final String DESCRIPTIONC = cardStringsPurge.DESCRIPTION;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ForethoughtAction");
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;
    private boolean chooseAny;
    private AbstractCard.CardType typeToCheck;

    public PutAtBottomOfDrawPileAndApplyChainAction(int amount) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, this.p, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            Iterator var5 = this.p.drawPile.group.iterator();

            AbstractCard card;
            while (var5.hasNext()) {
                card = (AbstractCard) var5.next();
                tmp.addToRandomSpot(card);
            }

            if (tmp.size() == 0) {
                this.isDone = true;
            } else if (tmp.size() == 1) {
                card = tmp.getTopCard();
                if (!ChainField.chain.get(card)) {
                    ChainField.chain.set(card, true);
                    card.rawDescription += DESCRIPTIONC;
                    card.initializeDescription();
                }
                this.p.drawPile.moveToBottomOfDeck(card);

                this.isDone = true;
            } else if (tmp.size() <= this.amount) {
                for (int i = 0; i < tmp.size(); ++i) {
                    card = tmp.getNCardFromTop(i);
                    if (!ChainField.chain.get(card)) {
                        ChainField.chain.set(card, true);
                        card.rawDescription += DESCRIPTIONC;
                        card.initializeDescription();
                    }
                    this.p.drawPile.moveToBottomOfDeck(card);
                }

                this.isDone = true;
            } else {
                if (this.amount == 1) {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                } else {
                    AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
                }

                this.tickDuration();
            }
        } else {
            AbstractCard card;
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while (var1.hasNext()) {
                    card = (AbstractCard) var1.next();
                    card.unhover();
                    if (!ChainField.chain.get(card)) {
                        ChainField.chain.set(card, true);
                        card.rawDescription += DESCRIPTIONC;
                        card.initializeDescription();
                    }
                    this.p.drawPile.moveToBottomOfDeck(card);

                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
}
