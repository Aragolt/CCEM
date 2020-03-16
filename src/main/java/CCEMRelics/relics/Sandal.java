package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class Sandal extends CustomRelic {
    public static final String ID = makeID("Sandal");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Sandal.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Sandal.png"));

    public Sandal() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hand.getAttacks().size() == 0) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1)));
        }
    }
}
