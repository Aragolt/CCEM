package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class RedPhonoblaster extends CustomRelic {

    public static final String ID = makeID("RedPhonoblaster");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RedPhonoblaster.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RedPhonoblaster.png"));

    public RedPhonoblaster() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}