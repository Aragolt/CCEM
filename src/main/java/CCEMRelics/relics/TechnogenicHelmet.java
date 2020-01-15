package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theextravagant.powers.BetterVigorPower;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.*;

public class TechnogenicHelmet extends CustomRelic {
    public static final String ID = makeID("TechnogenicHelmet");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TechnogenicHelmet.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("TechnogenicHelmet.png"));

    public TechnogenicHelmet() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount < 1) {
            this.flash();
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BetterVigorPower(AbstractDungeon.player, 2)));
            return 0;
        } else {
            return damageAmount;
        }
    }
}
