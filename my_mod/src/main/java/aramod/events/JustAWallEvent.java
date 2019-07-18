package aramod.events;

import aramod.relics.ChunkOfWall;
import aramod.relics.Lemon;
import aramod.relics.SmashedLemon;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;

public class JustAWallEvent extends AbstractImageEvent {
    public static final String ID = "AraMod:Just_A_Wall";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;
    private int screenNum = 0;

    public JustAWallEvent(){
        super(NAME, DESCRIPTIONS[0], "aramod/img/events/justawall.jpg");
        this.imageEventText.setDialogOption((OPTIONS[0]));
        if (!AbstractDungeon.player.hasRelic(Lemon.ID)) {
            this.imageEventText.setDialogOption((OPTIONS[1]), !AbstractDungeon.player.hasRelic(Lemon.ID));
        }
        else {
            this.imageEventText.setDialogOption((OPTIONS[2]), !AbstractDungeon.player.hasRelic(Lemon.ID));
        }
        this.imageEventText.setDialogOption((OPTIONS[3]));
    }

    @Override
    protected void buttonEffect(int buttonPressed){
        switch (screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        int x = AbstractDungeon.miscRng.random(99);
                        if (x < 50){
                            this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, true);
                            CardCrawlGame.sound.play("BLUNT_HEAVY");
                            AbstractDungeon.player.damage(new DamageInfo(null, 20));
                        }
                        else{
                            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
                            CardCrawlGame.sound.play("BLUNT_FAST");
                            this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        }
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, RelicLibrary.getRelic(ChunkOfWall.ID).makeCopy());
                        screenNum = 1;
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
                        CardCrawlGame.sound.play("BLOOD_SPLAT");
                        AbstractDungeon.player.loseRelic(Lemon.ID);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, RelicLibrary.getRelic(SmashedLemon.ID).makeCopy());
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        this.imageEventText.updateDialogOption(0, OPTIONS[3]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;
                        break;
                    default:
                        screenNum = 1;
                        break;
                }
                break;
            case 1:
                this.openMap();
                break;
            default:
                this.openMap();
                break;
        }
    }
}
