package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class PorcelainBowl extends CustomRelic {
    public static final String ID = CCEMRelics.makeID("PorcelainBowl");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PorcelainBowl.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PorcelainBowl.png"));

    public PorcelainBowl() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, AbstractRelic.LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractDungeon.player.gameHandSize++;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount >= 1 && !grayscale) {
            grayscale = true;
            AbstractDungeon.player.gameHandSize--;
        }
        return damageAmount;
    }

    public void onVictory() {
        this.grayscale = false;
    }
}
