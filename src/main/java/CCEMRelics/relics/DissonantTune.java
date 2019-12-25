package CCEMRelics.relics;

import CCEMRelics.CCEMRelics;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theextravagant.util.TextureLoader;

import java.util.Iterator;

import static CCEMRelics.CCEMRelics.makeRelicOutlinePath;
import static CCEMRelics.CCEMRelics.makeRelicPath;

public class DissonantTune extends CustomRelic {
    public static final String ID = CCEMRelics.makeID("DissonantTune");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("DissonantTuneNew.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("DissonantTuneNew.png"));
    private static final RelicTier TIER = RelicTier.UNCOMMON;

    public DissonantTune() {
        super(ID, IMG, OUTLINE, TIER, LandingSound.MAGICAL);
    }

    public void onEquip() {
        this.counter = 0;
    }

    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 4) {
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();
            while (var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster) var3.next();
                if (!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, null, new StrengthPower(monster, -1)));
                }
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
