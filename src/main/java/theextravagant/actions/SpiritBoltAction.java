package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import theextravagant.patches.EVCardPatches.AbstractCardPatches;

import java.util.ArrayList;
import java.util.Iterator;

public class SpiritBoltAction extends AbstractGameAction {
    public static final String[] TEXT;
    private static final UIStrings uiStrings;
    private static final float DURATION_PER_CARD = 0.25F;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("Enchantment");
        TEXT = uiStrings.TEXT;
    }

    private AbstractPlayer p;
    private int dupeAmount = 1;
    private ArrayList<AbstractCard> cannotDuplicate = new ArrayList();

    public SpiritBoltAction(AbstractCreature source, int amount) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.DRAW;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
        this.dupeAmount = amount;
    }

    public void update() {
        Iterator var1;
        AbstractCard c;
        int i;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            var1 = this.p.hand.group.iterator();

            while (var1.hasNext()) {
                c = (AbstractCard) var1.next();
                if (!this.isDualWieldable(c)) {
                    this.cannotDuplicate.add(c);
                }
            }

            if (this.cannotDuplicate.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.cannotDuplicate.size() == 1) {
                var1 = this.p.hand.group.iterator();

                while (var1.hasNext()) {
                    c = (AbstractCard) var1.next();
                    if (this.isDualWieldable(c)) {
                        for (i = 0; i < this.dupeAmount; ++i) {
                            AbstractCardPatches.EnchantmentField.EnchantmentField.set(c, true);
                        }

                        this.isDone = true;
                        return;
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotDuplicate);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                this.tickDuration();
                return;
            }

            if (this.p.hand.group.size() == 1) {
                for (int j = 0; j < this.dupeAmount; ++j) {
                    AbstractCardPatches.EnchantmentField.EnchantmentField.set(this.p.hand.getTopCard(), true);
                }

                this.returnCards();
                this.isDone = true;
            }
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

            while (var1.hasNext()) {
                c = (AbstractCard) var1.next();
                AbstractCardPatches.EnchantmentField.EnchantmentField.set(c, true);
                this.p.hand.addToTop(c);
            }
            this.returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        this.tickDuration();
    }

    private void returnCards() {
        Iterator var1 = this.cannotDuplicate.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    private boolean isDualWieldable(AbstractCard card) {
        return card.costForTurn == 0 || card.freeToPlayOnce;
    }
}
