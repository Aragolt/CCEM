package aramod.relics;
import aramod.powers.LoseFocusPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

public class ShatteredShield extends CustomRelic {
    public static final String ID = "AraMod:ShatteredShield";

    public ShatteredShield(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/shatteredshield.png"), TextureLoader.getTexture("aramod/img/relics/shatteredshield-outline.png"), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart(){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}