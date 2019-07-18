package aramod.relics;
import aramod.powers.BetterRitualPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DefectiveTurbocharger extends CustomRelic {
    public static final String ID = "AraMod:DefectiveTurbocharger";

    public DefectiveTurbocharger(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/defectiveturbocharger.png"), TextureLoader.getTexture("aramod/img/relics/defectiveturbocharger-outline.png"), RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, -30), -30));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BetterRitualPower(AbstractDungeon.player, 5), 5));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}