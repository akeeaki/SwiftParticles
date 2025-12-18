package ru.yuuki.swiftparticles.particle.scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import ru.yuuki.swiftparticles.SwiftParticles;
import ru.yuuki.swiftparticles.particle.model.ParticleModel;
import ru.yuuki.swiftparticles.vector.model.Vector3d;

@Getter @Setter
public final class ParticleScheduler extends BukkitRunnable {
    private long initialDelay, delay;
    private final ParticleModel parent;

    public ParticleScheduler(
            final long initialDelay,
            final ParticleModel parent
    ) {
        this.parent = parent;
        this.initialDelay = initialDelay;
        this.delay = this.initialDelay;
        this.runTaskTimerAsynchronously(SwiftParticles.getInstance(), 1, 1); // каждый тик
    }

    @Override
    public void run() {
        final long newDelay = delay - 1;
        if (newDelay <= 0) {
            this.parent.getWorld().spawnParticle(
                    this.parent.getParticle(),
                    Vector3d.asLocation(this.parent.getWorld(), this.parent.getPosition()),
                    this.parent.getAmount(),
                    this.parent.getOffsets().getX(),
                    this.parent.getOffsets().getY(),
                    this.parent.getOffsets().getZ(),
                    0
            );
            this.delay = this.initialDelay;
        } else this.delay = newDelay;
    }
}
