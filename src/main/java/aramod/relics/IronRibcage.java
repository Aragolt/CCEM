package aramod.relics;

import aramod.utils.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class IronRibcage extends CustomRelic {
    public static final String ID = "AraMod:IronRibcage";
    public static final int DEX_AMT = 3;
    private boolean isActive;

    public IronRibcage(){
        super(ID, TextureLoader.getTexture("aramod/img/relics/ironribcage.png"), TextureLoader.getTexture("aramod/img/relics/ironribcage-outline.png"), RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DEX_AMT + DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        this.isActive = false;
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                if (!aramod.relics.IronRibcage.this.isActive && AbstractDungeon.player.isBloodied) {
                    aramod.relics.IronRibcage.this.flash();
                    aramod.relics.IronRibcage.this.pulse = true;
                    AbstractDungeon.player.addPower(new DexterityPower(AbstractDungeon.player, DEX_AMT));
                    AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, aramod.relics.IronRibcage.this));
                    aramod.relics.IronRibcage.this.isActive = true;
                    AbstractDungeon.onModifyPower();
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void onBloodied() {
        this.flash();
        this.pulse = true;
        if (!this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            final AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, DEX_AMT), DEX_AMT));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.isActive = true;
            AbstractDungeon.player.hand.applyPowers();
        }
    }

    @Override
    public void onNotBloodied() {
        if (this.isActive && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            final AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, -DEX_AMT), -DEX_AMT));
        }
        this.stopPulse();
        this.isActive = false;
        AbstractDungeon.player.hand.applyPowers();
    }

    @Override
    public void onVictory() {
        this.pulse = false;
        this.isActive = false;
    }
}
