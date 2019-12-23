package CCEMRelics.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theextravagant.util.TextureLoader;

import java.util.Iterator;

import static CCEMRelics.CCEMRelics.*;

public class ShadowVeil extends CustomRelic {
    public static final String ID = makeID("ShadowVeil");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("ShadowVeil.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("ShadowVeil.png"));

    public ShadowVeil() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.currentBlock >= 4 && AbstractDungeon.player.currentBlock <= 12) {
            Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

            while (var3.hasNext()) {
                AbstractMonster mo = (AbstractMonster) var3.next();
                this.addToBot(new ApplyPowerAction(mo, null, new VulnerablePower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
}
