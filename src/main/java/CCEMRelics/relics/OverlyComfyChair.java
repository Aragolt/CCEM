package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class OverlyComfyChair extends CustomRelic {
    public static final String ID = makeID("OverlyComfyChair");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("OverlyComfyChair.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("OverlyComfyChair.png"));

    public OverlyComfyChair() {
        super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        if (!this.grayscale) {
            ++this.counter;
        }

        if (this.counter == 2) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 4));
        }

        if (this.counter == 3) {
            this.addToBot(new HealAction(AbstractDungeon.player, null, MathUtils.floor(AbstractDungeon.player.currentBlock / 4)));
            this.counter = -1;
            this.grayscale = true;
        }
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }
}