package CCEMRelics.relics;

import CCEMRelics.actions.EvokeSpecificOrbTwiceAction;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnChannelRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.FocusPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class ShortCircuitBoard extends CustomRelic implements OnChannelRelic {
    public static final String ID = makeID("ShortCircuitBoard");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ShortCircuitBoard.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ShortCircuitBoard.png"));

    public ShortCircuitBoard() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        if (orb instanceof Lightning) {
            AbstractDungeon.actionManager.addToBottom(new EvokeSpecificOrbTwiceAction(orb));
        }
    }

    @Override
    public void atPreBattle() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, null, new FocusPower(AbstractDungeon.player, -2)));
    }
}
