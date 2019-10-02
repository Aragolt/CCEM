package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.vfx.Sixteentonseffect;

public class SixteenTonsEffectAction extends AbstractGameAction {
    private AbstractCreature creature;

    public SixteenTonsEffectAction(AbstractCreature creature) {
        this.creature = creature;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop(new VFXAction(new Sixteentonseffect(creature.drawX, creature.drawY)));
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.3f));
        isDone = true;
    }
}
