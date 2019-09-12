package theextravagant.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

public class PheonixEffectAction extends AbstractGameAction {
    public PheonixEffectAction() {
    }

    @Override
    public void update() {
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToTop(new VFXAction(new FlameBarrierEffect(monster.drawX, monster.drawY)));
            }
        }
        isDone = true;
    }
}
