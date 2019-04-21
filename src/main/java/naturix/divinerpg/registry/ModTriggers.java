package naturix.divinerpg.registry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import naturix.divinerpg.events.advancementtriggers.AdvancementDivineBlock;
import naturix.divinerpg.events.advancementtriggers.AdvancementDivineEye;
import naturix.divinerpg.events.advancementtriggers.AdvancementDivineItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ModTriggers

{
public static final AdvancementDivineItem DIVINERPG_ITEM = new AdvancementDivineItem("divine_item");
public static final AdvancementDivineBlock DIVINERPG_BLOCK = new AdvancementDivineBlock("divine_block");
public static final AdvancementDivineEye DIVINERPG_EYE = new AdvancementDivineEye("divine_eye");

public static final AdvancementDivineItem[] TRIGGER_ARRAY = new AdvancementDivineItem[] { DIVINERPG_ITEM };
public static final AdvancementDivineBlock[] TRIGGER_ARRAY1 = new AdvancementDivineBlock[] { DIVINERPG_BLOCK };
public static final AdvancementDivineEye[] TRIGGER_ARRAY2 = new AdvancementDivineEye[] { DIVINERPG_EYE };

public static void registerTriggers() {
	System.out.println("Registering custom triggers");

	Method method;

	method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);

	method.setAccessible(true);

	for (int i = 0; i < TRIGGER_ARRAY.length; i++) {
		try {
			method.invoke(null, TRIGGER_ARRAY[i]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	for (int i = 0; i < TRIGGER_ARRAY1.length; i++) {
		try {
			method.invoke(null, TRIGGER_ARRAY1[i]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	for (int i = 0; i < TRIGGER_ARRAY2.length; i++) {
		try {
			method.invoke(null, TRIGGER_ARRAY2[i]);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
}