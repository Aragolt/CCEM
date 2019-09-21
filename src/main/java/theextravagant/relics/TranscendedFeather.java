package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.cards.Invocation;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class TranscendedFeather extends CustomRelic {

    public static final String ID = makeID("TranscendedFeather");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TranscendedFeather.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TranslucentFeather.png"));

    public TranscendedFeather() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        flash();
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Invocation(), 2));
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(TranslucentFeather.ID);
    }

    @Override
    public void obtain() {
        this.instantObtain(AbstractDungeon.player, AbstractDungeon.player.relics.indexOf(AbstractDungeon.player.getRelic(TranslucentFeather.ID)), false);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}