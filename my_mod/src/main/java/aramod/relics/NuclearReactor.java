package aramod.relics;

import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NuclearReactor extends CustomRelic {
    public static final String ID = "AraMod:NuclearReactor";

    public NuclearReactor(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/nuclearreactor.png"), TextureLoader.getTexture("aramod/img/relics/nuclearreactor-outline.png"), RelicTier.BOSS, LandingSound.SOLID);
        this.counter = 0;
    }

    @Override
    public void onEquip() {
        EnergyManager energy = AbstractDungeon.player.energy;
        energy.energyMaster += 2;
    }

    @Override
    public void onUnequip() {
        EnergyManager energy = AbstractDungeon.player.energy;
        energy.energyMaster -= 2;
    }

    @Override
    public void atBattleStart() {
        counter = 0;
    }

    @Override
    public void onPlayerEndTurn() {
        counter++;
        if (counter >= 12){
            AbstractPlayer p = AbstractDungeon.player;
            p.powers.removeIf(power -> "hubris:Potato".equals(power.ID));
            p.currentHealth = 0;
            p.healthBarUpdatedEvent();
            p.damage(new DamageInfo(p, 1, DamageInfo.DamageType.HP_LOSS));
            isDone = true;

        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
