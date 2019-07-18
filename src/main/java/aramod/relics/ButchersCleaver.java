package aramod.relics;
import aramod.powers.CleaverPower;
import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ButchersCleaver extends CustomRelic {
    public static final String ID = "AraMod:ButchersCleaver";

    public ButchersCleaver(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/butcherscleaver.png"), TextureLoader.getTexture("aramod/img/relics/butcherscleaver-outline.png"), RelicTier.UNCOMMON, LandingSound.HEAVY);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new CleaverPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}