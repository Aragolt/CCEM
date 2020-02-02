package theextravagant.patches.SecondEnergyOrbPatches;

/*
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

}*/