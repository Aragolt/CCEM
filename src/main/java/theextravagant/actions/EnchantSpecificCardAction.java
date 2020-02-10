package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.patches.EVCardPatches.AbstractCardPatches;

public class EnchantSpecificCardAction extends AbstractGameAction {
    private AbstractCard C;

    public EnchantSpecificCardAction(AbstractCard C) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.C = C;
    }

    @Override
    public void update() {
        C.flash();
        AbstractCardPatches.EnchantmentField.EnchantmentField.set(C, true);
        isDone = true;
    }
}
