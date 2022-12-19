package umaru.tomonova.tomonova.core.task.bleachUHCTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import umaru.tomonova.tomonova.core.TomoNova;

import java.util.HashMap;
import java.util.UUID;

public class ReturnDamageTask extends BukkitRunnable {
    private static int returnTime;
    private static HashMap<UUID,Double> hashMapReturnDamage;
    private TomoNova tomoNova;

    public ReturnDamageTask(TomoNova tomoNova) {
        this.tomoNova = tomoNova;
    }

    @Override
    public void run() {

        returnTime--;
        if (returnTime == 0) {
            this.cancel();
            hashMapReturnDamage.keySet().forEach(p -> ((LivingEntity) Bukkit.getEntity(p)).damage(hashMapReturnDamage.get(p)));
            //hashMapReturnDamage.keySet().forEach(p -> Bukkit.getPlayer(p).sendMessage(p + " a reçu " + hashMapReturnDamage.get(p).toString() + " damages"));
        }
    }

    public static void setReturnTime(int returnTime) {
        ReturnDamageTask.returnTime = returnTime;
    }

    public static void setHashMapReturnDamage(HashMap<UUID, Double> hashMapReturnDamage) {
        ReturnDamageTask.hashMapReturnDamage = hashMapReturnDamage;
    }
    public static void addDamageToPlayer(Double damage, UUID entityUUID){

        Double oldDamage = ReturnDamageTask.hashMapReturnDamage.get(entityUUID);
        ReturnDamageTask.hashMapReturnDamage.put(entityUUID, oldDamage + damage);
    }
}
