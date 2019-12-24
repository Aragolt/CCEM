package CCEMRelics.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class EvokeSpecificOrbTwiceAction extends AbstractGameAction {
    private AbstractOrb orb;

    public EvokeSpecificOrbTwiceAction(AbstractOrb orb) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.orb = orb;
        this.actionType = ActionType.DAMAGE;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST && this.orb != null) {
            AbstractDungeon.player.orbs.remove(this.orb);
            AbstractDungeon.player.orbs.add(0, this.orb);
            AbstractDungeon.player.evokeWithoutLosingOrb();
            AbstractDungeon.player.evokeOrb();
        }

        this.tickDuration();
    }
}

