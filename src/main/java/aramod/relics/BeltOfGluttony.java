package aramod.relics;
import aramod.powers.LoseFocusPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class BeltOfGluttony extends CustomRelic {
    public static final String ID = "AraMod:BeltOfGluttony";

    public BeltOfGluttony(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/beltofgluttony.png"), TextureLoader.getTexture("aramod/img/relics/beltofgluttony-outline.png"), RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.decreaseMaxHealth((int)(AbstractDungeon.player.maxHealth * 0.3));
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (!m.hasPower(MinionPower.POWER_ID)){
            AbstractDungeon.player.increaseMaxHp(1, true);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}