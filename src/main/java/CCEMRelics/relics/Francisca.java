package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theextravagant.util.TextureLoader;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class Francisca extends CustomRelic {
    public static final String ID = CCEMRelics.makeID("Francisca");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Francisca.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Francisca.png"));
    private static final AbstractRelic.RelicTier TIER = RelicTier.RARE;

    public Francisca() {
        super(ID, IMG, OUTLINE, TIER, AbstractRelic.LandingSound.MAGICAL);
    }

    public void onEquip() {
        this.counter = 10;
    }

    @Override
    public void atBattleStart() {
        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(null, counter, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void onVictory() {
        if (AbstractDungeon.getCurrRoom().eliteTrigger) {
            counter += 5;
        }
    }

    public boolean canSpawn() {
        return Settings.isEndless || AbstractDungeon.floorNum <= 32;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
