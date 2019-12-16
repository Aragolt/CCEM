package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnChannelRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.powers.ThornsPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class Icicle extends CustomRelic implements OnChannelRelic {
    public static final String ID = makeID("Icicle");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Icicle.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Icicle.png"));

    public Icicle() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (orb instanceof Frost) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new ThornsPower(AbstractDungeon.player, 1), 1));
        }
    }

    public void onRemoveOrEvokeOrb(AbstractOrb orb) {
        if (orb instanceof Frost) {
            if (AbstractDungeon.player.hasPower(ThornsPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, ThornsPower.POWER_ID, 1));
            }
        }
    }
}
