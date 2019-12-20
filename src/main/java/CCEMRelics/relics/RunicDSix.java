package CCEMRelics.relics;

import CCEMRelics.rewards.RerollRewards;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class RunicDSix extends CustomRelic {
    public static final String ID = makeID("D6");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("D6.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("D6.png"));

    public RunicDSix() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.HEAVY);
        counter = 8;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        if (counter > 0) {
            AbstractDungeon.getCurrRoom().rewards.add(new RerollRewards(MathUtils.floor(counter)));
        }
    }

    @Override
    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 48;
    }
}
