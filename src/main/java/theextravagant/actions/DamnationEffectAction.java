package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.vfx.Damnationeffect;

public class DamnationEffectAction extends AbstractGameAction {
    public DamnationEffectAction() {
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop(new VFXAction(new Damnationeffect()));
        isDone = true;
    }
}
