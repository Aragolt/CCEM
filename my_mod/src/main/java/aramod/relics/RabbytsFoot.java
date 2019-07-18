package aramod.relics;
import aramod.powers.FastPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RabbytsFoot extends CustomRelic {
    public static final String ID = "AraMod:RabbytsFoot";

    public RabbytsFoot(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/rabbytsfoot.png"), TextureLoader.getTexture("aramod/img/relics/rabbytsfoot-outline.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart(){
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FastPower(p, 0)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}