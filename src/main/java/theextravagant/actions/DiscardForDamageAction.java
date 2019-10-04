package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;

public class DiscardForDamageAction extends AbstractGameAction {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;
    private static final float DURATION;
    public static int numDiscarded;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");
        TEXT = uiStrings.TEXT;
        DURATION = Settings.ACTION_DUR_XFAST;
    }

    private AbstractPlayer p;
    private boolean isRandom;
    private boolean endTurn;
    private AbstractMonster AttackTarget;
    private AbstractCard card;

    public DiscardForDamageAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, AbstractCard c, AbstractMonster monster) {
        this(target, source, amount, isRandom, false, c, monster);
    }

    public DiscardForDamageAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn, AbstractCard c, AbstractMonster monster) {
        this.p = (AbstractPlayer) target;
        this.isRandom = isRandom;
        this.setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.endTurn = endTurn;
        this.duration = DURATION;
        this.AttackTarget = monster;
        this.card = c;
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
                        if (d.costForTurn > 0 && !d.freeToPlayOnce) {
                            for (int j = 0; j < d.costForTurn; j++) {
                                card.calculateCardDamage(AttackTarget);
                                AbstractDungeon.actionManager.addToTop(new FireworkEffectAction(AttackTarget));
                                AbstractDungeon.actionManager.addToTop(new DamageAction(this.AttackTarget, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.FIRE));
                            }
                        } else if (d.costForTurn == -1) {
                            for (int j = 0; j < EnergyPanel.getCurrentEnergy(); j++) {
                                card.calculateCardDamage(AttackTarget);
                                AbstractDungeon.actionManager.addToTop(new FireworkEffectAction(AttackTarget));
                                AbstractDungeon.actionManager.addToTop(new DamageAction(this.AttackTarget, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.FIRE));
                            }
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
                if (c.costForTurn > 0 && !c.freeToPlayOnce) {
                    card.calculateCardDamage(AttackTarget);
                    for (int j = 0; j < c.costForTurn; j++) {
                        card.calculateCardDamage(AttackTarget);
                        AbstractDungeon.actionManager.addToTop(new FireworkEffectAction(AttackTarget));
                        AbstractDungeon.actionManager.addToTop(new DamageAction(this.AttackTarget, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.FIRE));
                    }
                } else if (c.costForTurn == -1) {
                    for (int j = 0; j < EnergyPanel.getCurrentEnergy(); j++) {
                        card.calculateCardDamage(AttackTarget);
                        AbstractDungeon.actionManager.addToTop(new FireworkEffectAction(AttackTarget));
                        AbstractDungeon.actionManager.addToTop(new DamageAction(this.AttackTarget, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.FIRE));
                    }
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
                if (c.costForTurn > 0 && !c.freeToPlayOnce) {
                    card.calculateCardDamage(AttackTarget);
                    for (int i = 0; i < c.costForTurn; i++) {
                        card.calculateCardDamage(AttackTarget);
                        AbstractDungeon.actionManager.addToTop(new FireworkEffectAction(AttackTarget));
                        AbstractDungeon.actionManager.addToTop(new DamageAction(this.AttackTarget, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.FIRE));
                    }
                } else if (c.costForTurn == -1) {
                    for (int j = 0; j < EnergyPanel.getCurrentEnergy(); j++) {
                        card.calculateCardDamage(AttackTarget);
                        AbstractDungeon.actionManager.addToTop(new FireworkEffectAction(AttackTarget));
                        AbstractDungeon.actionManager.addToTop(new DamageAction(this.AttackTarget, new DamageInfo(AbstractDungeon.player, this.card.damage, this.card.damageTypeForTurn), AttackEffect.FIRE));
                    }
                }
                GameActionManager.incrementDiscard(this.endTurn);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        this.tickDuration();
    }
}

