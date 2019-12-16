package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class PaperClip extends CustomRelic {
    public static final String ID = makeID("PaperClip");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PaperClip.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PaperClip.png"));

    public PaperClip() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
