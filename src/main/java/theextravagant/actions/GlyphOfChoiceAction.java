package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

public class GlyphOfChoiceAction extends AbstractGameAction {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("drawPileToTopOfDeckAction");
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;
    private boolean z;
    private int amt;

    public GlyphOfChoiceAction(AbstractCreature source, int amount, boolean shouldcostzero) {
        this.p = AbstractDungeon.player;
        this.setValues(null, source, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        amt = amount;
        z = shouldcostzero;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().isBattleEnding()) {
            this.isDone = true;
        } else {
            if (this.duration == Settings.ACTION_DUR_FASTER) {
                if (this.p.drawPile.isEmpty()) {
                    this.isDone = true;
                    return;
                }

                if (this.p.drawPile.size() == 1) {
                    AbstractCard tmp = this.p.drawPile.getTopCard();
                    if (z) {
                        tmp.freeToPlayOnce = true;
                    }
                    this.p.drawPile.removeCard(tmp);
                    this.p.drawPile.moveToDeck(tmp, false);
                }

                if (this.p.drawPile.group.size() > amt) {
                    CardGroup C = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    Iterator var3 = AbstractDungeon.player.drawPile.group.iterator();

                    while (var3.hasNext()) {
                        AbstractCard c = (AbstractCard) var3.next();
                        C.addToTop(c);
                    }
                    AbstractDungeon.gridSelectScreen.open(C, amt, TEXT[0], false, false, false, false);
                    this.tickDuration();
                    return;
                } else {
                    CardGroup C = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    Iterator var3 = AbstractDungeon.player.drawPile.group.iterator();

                    while (var3.hasNext()) {
                        AbstractCard c = (AbstractCard) var3.next();
                        C.addToTop(c);
                    }
                    for (AbstractCard c : C.group) {
                        this.p.drawPile.removeCard(c);
                        if (z) {
                            c.freeToPlayOnce = true;
                        }
                        this.p.hand.moveToDeck(c, false);
                    }
                }
            }

            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                Iterator var3 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while (var3.hasNext()) {
                    AbstractCard c = (AbstractCard) var3.next();
                    if (z) {
                        c.freeToPlayOnce = true;
                    }
                    this.p.drawPile.removeCard(c);
                    this.p.hand.moveToDeck(c, false);
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
}
