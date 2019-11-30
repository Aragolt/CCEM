package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.cards.Accumulation;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class PocketDynamo extends CustomRelic {

    public static final String ID = makeID("PocketDynamo");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PocketDynamo.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PocketDynamo.png"));

    public PocketDynamo() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void onPlayerEndTurn() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Accumulation()));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
