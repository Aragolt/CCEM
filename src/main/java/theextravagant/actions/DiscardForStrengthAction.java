package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Iterator;

public class DiscardForStrengthAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean endTurn;
    public static int numDiscarded;
    private static final float DURATION;

    public DiscardForStrengthAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(target, source, amount, isRandom, false);
    }

    public DiscardForStrengthAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn) {
        this.p = (AbstractPlayer) target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.endTurn = endTurn;
        this.duration = DURATION;
    }

    public void update() {
        AbstractCard c;
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            int i;
            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                i = this.p.hand.size();

                for (int a = 0; a < i; ++a) {
                    AbstractCard d = this.p.hand.getTopCard();
                    this.p.hand.moveToDiscardPile(d);
                    if (!this.endTurn) {
                         d.triggerOnManualDiscard();
                        if(d.type == AbstractCard.CardType.ATTACK && d.cost > 0)
                        {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, d.cost), d.cost));
                        }
                    }

                    GameActionManager.incrementDiscard(this.endTurn);
                }

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            if (!this.isRandom) {
                if (this.amount < 0) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
                    AbstractDungeon.player.hand.applyPowers();
                    this.tickDuration();
                    return;
                }

                numDiscarded = this.amount;
                if (this.p.hand.size() > this.amount) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
                }

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            for (i = 0; i < this.amount; ++i) {
                c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                if(c.type == AbstractCard.CardType.ATTACK && c.cost > 0)
                {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, c.cost), c.cost));
                }
                GameActionManager.incrementDiscard(this.endTurn);
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while (var4.hasNext()) {
                c = (AbstractCard) var4.next();
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                if(c.type == AbstractCard.CardType.ATTACK && c.cost > 0)
                {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, c.cost), c.cost));
                }
                GameActionManager.incrementDiscard(this.endTurn);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }


    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }
}