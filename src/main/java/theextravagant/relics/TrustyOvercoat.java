package theextravagant.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static theextravagant.theextravagant.*;

public class TrustyOvercoat extends CustomRelic {

    public static final String ID = makeID("TrustyOvercoat");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TrustyOvercoat.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TrustyOvercoat.png"));

    public TrustyOvercoat() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
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
    public void onCardDraw(AbstractCard drawnCard) {
        counter++;
        if (counter == 15) {
            counter = 0;
            AbstractDungeon.player.energy.use(1);
        }
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

