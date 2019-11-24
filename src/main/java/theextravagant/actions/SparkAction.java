package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.patches.EVCardPatches.AbstractCardPatches;

public class SparkAction extends AbstractGameAction {
    private AbstractCard.CardType CardType;

    public SparkAction(AbstractCard.CardType T) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
        CardType = T;
    }

    @Override
    public void update() {
        CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard C : AbstractDungeon.player.hand.group) {
            if (!AbstractCardPatches.EnchantmentField.EnchantmentField.get(C) && C.cost >= -1 && C.type == CardType) {
                temp.addToTop(C);
            }
        }

        if (!temp.isEmpty()) {
            AbstractCard c = temp.getRandomCard(true);
            c.flash();
            AbstractCardPatches.EnchantmentField.EnchantmentField.set(c, true);
        }
        isDone = true;
    }
}
