package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theextravagant.powers.DrawReductionOncePower;

import java.util.Iterator;

public class AntiquityAction extends AbstractGameAction {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;
    private static final float DURATION;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("Antiquity");
        TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }

    private AbstractPlayer p;

    public AntiquityAction() {
        this.p = (AbstractPlayer) target;
        this.setValues(target, source, amount);
        this.actionType = ActionType.DISCARD;
        this.duration = DURATION;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for (Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); ) {
                    c = (AbstractCard) var1.next();
                    AbstractDungeon.actionManager.addToBottom(new PlayCardTwiceAction(c, new UseCardAction(c)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawReductionOncePower(AbstractDungeon.player, 1)));
                }

                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }
}

