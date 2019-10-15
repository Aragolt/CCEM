package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.SeekAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class TranslucentFeather extends CustomRelic {

    public static final String ID = makeID("TranslucentFeather");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TranslucentFeather.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TranslucentFeather.png"));
    private boolean FirstTurn = true;

    public TranslucentFeather() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    public void atBattleStart() {
        FirstTurn = true;
    }

    @Override
    public void atTurnStart() {
        if (FirstTurn) {
            FirstTurn = false;
            AbstractDungeon.actionManager.addToTop(new SeekAction(1));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

