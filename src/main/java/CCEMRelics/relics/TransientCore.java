package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class TransientCore extends CustomRelic {
    public static final String ID = makeID("TransientCore");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TransientCore.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TransientCore.png"));

    public TransientCore() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractDungeon.player.increaseMaxHp(-1, false);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.player.increaseMaxHp(6, false);
    }
}
