package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.vfx.Fireworkeffect;

public class FireworkEffectAction extends AbstractGameAction {
    private AbstractCreature creature;

    public FireworkEffectAction(AbstractCreature creature) {
        this.creature = creature;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.3f));
        AbstractDungeon.actionManager.addToTop(new VFXAction(new Fireworkeffect(creature.drawX - 25f * Settings.scale, creature.drawY - 20f * Settings.scale)));
        isDone = true;
    }
}



