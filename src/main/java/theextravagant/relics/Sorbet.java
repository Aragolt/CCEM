package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class Sorbet extends CustomRelic {

    public static final String ID = makeID("Sorbet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Sorbet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Sorbet.png"));

    public Sorbet() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
