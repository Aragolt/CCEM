package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.vfx.PunchEffect;

public class PunchEffectAction extends AbstractGameAction {
    private AbstractCreature creature;

    public PunchEffectAction(AbstractCreature creature) {
        this.creature = creature;
    }

    @Override
    public void update() {
        if (creature != null && !creature.isDeadOrEscaped()) {
            AbstractDungeon.actionManager.addToTop(new VFXAction(new PunchEffect(creature.drawX - creature.hb.width / 2, creature.drawY + creature.hb.height / 3)));
            AbstractDungeon.actionManager.addToTop(new WaitAction(0.2f));
            AbstractDungeon.actionManager.addToTop(new BetterSFXAction(-0.2f, "BLUNT_HEAVY"));
        }
        isDone = true;
    }
}