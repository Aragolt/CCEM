package theextravagant.patches.EVCardPatches;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;

@SpirePatch(clz = CardCrawlGame.class, method = "update")
public class CardcrawlgameTimedelayPatch {
    public static float elapsedtimewithPiresest;

    @SpirePostfixPatch
    public static void update(CardCrawlGame __instance) {
        elapsedtimewithPiresest += Gdx.graphics.getRawDeltaTime() * 2;
        if (elapsedtimewithPiresest >= 2 * Math.PI) {
            elapsedtimewithPiresest = 0;
        }
    }
}
