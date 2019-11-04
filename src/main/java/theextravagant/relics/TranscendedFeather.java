package theextravagant.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
        this.counter = 0;
    }

    @Override
    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public void onShuffle() {
        AbstractDungeon.player.gameHandSize += 1;
        counter += 1;
    }

    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(TranslucentFeather.ID);
    }

    @Override
    public void obtain() {
        this.instantObtain(AbstractDungeon.player, AbstractDungeon.player.relics.indexOf(AbstractDungeon.player.getRelic(TranslucentFeather.ID)), true);
    }

    @Override
    public void onEquip() {
        BaseMod.MAX_HAND_SIZE += 2;
    }

    @Override
    public void onUnequip() {
        BaseMod.MAX_HAND_SIZE -= 2;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}