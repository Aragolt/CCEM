package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.Iterator;

public class DualMirrorCutAction extends AbstractGameAction {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;
    private static final float DURATION;
    public static int numDiscarded;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }

    int attacks;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean endTurn;

    public DualMirrorCutAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, int attacks) {
        this(target, source, amount, isRandom, false, attacks);
    }

    public DualMirrorCutAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn, int attacks) {
        this.p = (AbstractPlayer) target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.endTurn = endTurn;
        this.duration = DURATION;
        this.attacks = attacks;
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

                for (int j = 0; i < i; ++i) {
                    AbstractCard cc = this.p.hand.getTopCard();
                    cc.applyPowers();
                    if (cc.type == AbstractCard.CardType.ATTACK) {
                        for (int k = 0; k < attacks; k++) {
                            AbstractDungeon.actionManager.addToBottom(new AttackDamageRandomEnemyAction(cc, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                        }

                    }
                    this.p.hand.moveToDiscardPile(cc);
                    if (!this.endTurn) {
                        cc.triggerOnManualDiscard();
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
                for (int k = 0; k < attacks; k++) {
                    if (this.p.hand.size() > this.amount) {
                        AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
                    }
                }

                AbstractDungeon.player.hand.applyPowers();
                this.tickDuration();
                return;
            }

            for (i = 0; i < this.amount; ++i) {
                c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                c.applyPowers();
                for (int k = 0; k < attacks; k++) {
                    if (c.type == AbstractCard.CardType.ATTACK) {
                        AbstractDungeon.actionManager.addToBottom(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    }
                }
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(this.endTurn);
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while (var4.hasNext()) {
                c = (AbstractCard) var4.next();
                c.applyPowers();
                for (int k = 0; k < attacks; k++) {
                    if (c.type == AbstractCard.CardType.ATTACK) {
                        AbstractDungeon.actionManager.addToBottom(new AttackDamageRandomEnemyAction(c, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    }
                }
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(this.endTurn);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }
}