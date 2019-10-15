/*package aramod.patches;

import aramod.relics.BrokenPillar;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction")
public class EndTurnBlockPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(GameActionManager _instance){
        if (AbstractDungeon.player.hasRelic(BrokenPillar.ID)){
            ((BrokenPillar)AbstractDungeon.player.getRelic(BrokenPillar.ID)).dmg = AbstractDungeon.player.currentBlock;
        }
    }

    private static class Locator extends SpireInsertLocator{
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException{
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(GameActionManager.class, "damageReceivedThisTurn");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}*/
