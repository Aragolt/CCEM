package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theextravagant.powers.DrawReductionOncePower;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class Sorbet extends CustomRelic implements OnReceivePowerRelic {

    public static final String ID = makeID("Sorbet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Sorbet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Sorbet.png"));

    public Sorbet() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        return !(abstractPower instanceof DrawReductionOncePower);
    }
}
