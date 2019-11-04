package theextravagant.patches.SecondEnergyOrbPatches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theextravagant.theextravagant;

import java.util.ArrayList;

@SpirePatch(clz = AbstractDungeon.class, method = "render")
public class RenderOrbPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void patch(AbstractDungeon __instance, SpriteBatch sb) {
        theextravagant.SecondEnergyOrb.render(sb);
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractRoom.class, "render");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }

}