package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class FryingPan extends CustomRelic {
    public static final String ID = CCEMRelics.makeID("FryingPan");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FryingPan.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FryingPan.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public FryingPan() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount >= 30) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            return damageAmount + 15;
        } else {
            return damageAmount;
        }
    }

}
