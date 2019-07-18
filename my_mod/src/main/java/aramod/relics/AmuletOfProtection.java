package aramod.relics;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MalleablePower;

public class AmuletOfProtection extends CustomRelic {
    public static final String ID = "AraMod:AmuletOfProtection";

    public AmuletOfProtection(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/amuletofprotection.png"), TextureLoader.getTexture("aramod/img/relics/amuletofprotection-outline.png"), RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new MalleablePower(AbstractDungeon.player, 2), 2));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}