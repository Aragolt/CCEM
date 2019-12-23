package CCEMRelics.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ForkliftAction extends AbstractGameAction {
    private AbstractCard targetCard;
    private CardGroup group;
    private float startingDuration;

    public ForkliftAction(CardGroup group, boolean isFast) {
        this.targetCard = targetCard;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.SPECIAL;
        this.group = group;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        CardGroup Move = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0; i < MathUtils.floor(group.size() / 2.0f); i++) {
            Move.addToTop(group.getNCardFromTop(i));
        }
        for (AbstractCard c : Move.group) {
            group.moveToDiscardPile(c);
        }
        this.isDone = true;
    }
}

